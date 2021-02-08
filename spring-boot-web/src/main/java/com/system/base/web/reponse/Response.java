package com.system.base.web.reponse;

import lombok.Data;

@Data
public class Response<T> {

    Page page;

    T result;

}
