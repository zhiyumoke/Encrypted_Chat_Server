package jilei.springserverdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/1.do")
    @ResponseBody
    public String Test() {
        return "hello";
    }
}
