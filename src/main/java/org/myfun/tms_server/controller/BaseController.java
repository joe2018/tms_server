package org.myfun.tms_server.controller;

import org.myfun.tms_server.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Admin
 */
public class BaseController {
    @Autowired
    HttpServletRequest req;

    @Autowired
    public RedisUtil redisUtil;
}
