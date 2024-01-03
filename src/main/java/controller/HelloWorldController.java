package controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HelloWorldController {
    @GetMapping(path = "/hello-world")
    public String helloworld(){
        return "Hello World";
    }
}
