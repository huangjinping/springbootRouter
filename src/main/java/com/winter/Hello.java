package com.winter;

/**
 * Created by harrishuang on 2018/11/12.
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by harrishuang on 2018/11/11.
 */
@RestController
@RequestMapping("/hello")
public class Hello {

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello() {
        return "hello SpringBoot11111";
    }

}
