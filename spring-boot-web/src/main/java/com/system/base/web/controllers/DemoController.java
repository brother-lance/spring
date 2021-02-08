package com.system.base.web.controllers;

import com.system.base.web.reponse.Response;
import com.system.base.web.vo.DemoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/demo")
public class DemoController {

    @PostMapping
    public Response<DemoVO> add(@RequestBody DemoVO demoVO) {
        log.info("添加数据：{}", demoVO);
        Response<DemoVO> result = new Response<>();
        demoVO.setId(Math.random() * 1000 + "");
        result.setResult(demoVO);
        return result;
    }

    @DeleteMapping("{id}")
    public Response<Boolean> delete(@PathVariable("id") String id) {
        log.info("删除数据：{}", id);
        Response<Boolean> response = new Response<>();
        response.setResult(true);
        return response;
    }

    @PutMapping
    public Response<DemoVO> update(@RequestBody DemoVO demoVO) {
        log.info("修改数据：{}", demoVO);
        Response<DemoVO> result = new Response<>();
        result.setResult(demoVO);
        return result;
    }

    @GetMapping
    public Response<List<DemoVO>> list() {
        Response<List<DemoVO>> response = new Response<>();
        List<DemoVO> resultList = new ArrayList<>();
        DemoVO firstDemo = new DemoVO();
        firstDemo.setId("1");
        firstDemo.setName("张三");
        firstDemo.setAge(18);
        DemoVO secondDemo = new DemoVO();
        secondDemo.setId("2");
        secondDemo.setName("李四");
        secondDemo.setAge(35);
        resultList.add(firstDemo);
        resultList.add(secondDemo);
        response.setResult(resultList);
        return response;
    }

    @GetMapping("{id}")
    public Response<DemoVO> get(@PathVariable("id") String id) {
        return buildVO(id);
    }

    // 异常流程
    /**
     * 异步流程
     * 方式一：Servlet方式实现异步请求
     */
    @GetMapping(value = "AsyncContext")
    public void callableGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        AsyncContext asyncContext = request.startAsync();
        //设置监听器:可设置其开始、完成、异常、超时等事件的回调处理
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onTimeout(AsyncEvent event) {
                log.info("超时了...");
                //做一些超时后的相关操作...
            }

            @Override
            public void onStartAsync(AsyncEvent event) {
                log.info("线程开始");
            }

            @Override
            public void onError(AsyncEvent event) {
                log.info("发生错误：" + event.getThrowable());
            }

            @Override
            public void onComplete(AsyncEvent event) {
                log.info("执行完成");
                //这里可以做一些清理资源的操作...
            }
        });
        //设置超时时间
        asyncContext.setTimeout(20000);
        asyncContext.start(() -> {
            try {
                Thread.sleep(10000);
                log.info("内部线程：" + Thread.currentThread().getName());
                asyncContext.getResponse().setCharacterEncoding("utf-8");
                asyncContext.getResponse().setContentType("text/html;charset=UTF-8");
                asyncContext.getResponse().getWriter().println("这是异步的请求返回");
            } catch (Exception e) {
                log.info("异常：" + e);
            }
            //异步请求完成通知
            //此时整个请求才完成
            asyncContext.complete();
        });
        //此时之类 request的线程连接已经释放了
        log.info("主线程：" + Thread.currentThread().getName());
    }

    /**
     * 异常流程
     * 方式二：使用很简单，直接返回的参数包裹一层callable即可，可以继承WebMvcConfigurerAdapter类来设置默认线程池和超时处理
     */
    @GetMapping("callable/{id}")
    public Callable<Response<DemoVO>> callableGet(@PathVariable("id") String id) {
        log.info("外部线程：" + Thread.currentThread().getName());
        return () -> {
            Thread.sleep(10000);
            return buildVO(id);
        };
    }

    /**
     * 异步，超时管理-重试机制
     * 方式三：和方式二差不多，在Callable外包一层，给WebAsyncTask设置一个超时回调，即可实现超时处理
     */
    @GetMapping(value = "webAsyncTask{id}")
    public WebAsyncTask<Response<DemoVO>> WebAsyncTaskGet(@PathVariable("id") String id) {
        log.info("外部线程：" + Thread.currentThread().getName());
        Callable<Response<DemoVO>> result = () -> {
            log.info("内部线程开始：" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (Exception ignored) {
            }
            log.info("副线程返回");
            log.info("内部线程返回：" + Thread.currentThread().getName());
            return buildVO(id);
        };
        WebAsyncTask<Response<DemoVO>> wat = new WebAsyncTask<>(3000L, result);
        wat.onTimeout(() -> {
            log.error("超时");
            return buildVO(id);
        });
        return wat;
    }

    @GetMapping(value = "deferred/{id}")
    @ResponseBody
    public DeferredResult<Response<DemoVO>> deferredResultReq(@PathVariable("id") String id) {
        log.info("外部线程：" + Thread.currentThread().getName());
        //设置超时时间
        DeferredResult<Response<DemoVO>> result = new DeferredResult<>(60 * 1000L);
        //处理超时事件 采用委托机制
        result.onTimeout(() -> {
            log.info("DeferredResult超时");
            result.setResult(buildVO(id));
        });
        result.onCompletion(() -> {
            //完成后
            log.info("调用完成");
        });
        LinkedBlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>(10);
        new ThreadPoolExecutor(1, 1, 60, TimeUnit.MINUTES, workQueue).execute(() -> {
            //处理业务逻辑
            log.info("内部线程：" + Thread.currentThread().getName());
            //返回结果
            result.setResult(buildVO(id));
        });
        return result;
    }

    // 构建返回对象
    private Response<DemoVO> buildVO(String id) {
        Response<DemoVO> response = new Response<>();
        DemoVO firstDemo = new DemoVO();
        firstDemo.setId(id);
        firstDemo.setName("张三");
        firstDemo.setAge(18);
        response.setResult(firstDemo);
        return response;
    }

}
