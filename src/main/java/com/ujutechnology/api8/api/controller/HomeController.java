package com.ujutechnology.api8.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kei
 * @since 2022-08-29
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = { "", "/", "/api" })
    public String hello() {
        return "hello";
    }
}
