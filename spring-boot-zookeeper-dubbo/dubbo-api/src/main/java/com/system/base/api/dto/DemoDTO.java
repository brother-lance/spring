package com.system.base.api.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DemoDTO implements java.io.Serializable {

    private long id;

    private String name;

    private String message;
}
