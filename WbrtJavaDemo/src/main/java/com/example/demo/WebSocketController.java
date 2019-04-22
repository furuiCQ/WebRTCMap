package com.example.demo;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {


    @RestController
    @RequestMapping("websocket")
    public class TestController {

        @RequestMapping("test")
        public String test() {
            return "Hello World";
        }
    }
}