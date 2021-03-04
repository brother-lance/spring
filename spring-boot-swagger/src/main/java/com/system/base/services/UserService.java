package com.system.base.services;

import com.system.base.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author wushengbin
 * @Date 2021/3/4 18:58
 **/
@Service
public class UserService {


    public User add(User user) {
        return user;
    }

    public boolean delete(long id) {
        return true;
    }

    public User update(User user) {
        return user;
    }

    public User get(User user) {
        return user;
    }

    public List<User> list(User user) {
        List<User> list = new ArrayList<>();
        return list;
    }

}
