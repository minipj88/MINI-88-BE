package com.ujutechnology.api8.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kei
 * @since 2022-08-24
 */
@RestController
public class RestMemberController {
    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = { "", "/" })
    public String hello() {
        return "hello";
    }
}
