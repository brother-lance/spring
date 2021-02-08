package com.system.base.web.reponse;

import lombok.Data;

@Data
public class Page {

    int limit;

    int offset;

    int total;
}
