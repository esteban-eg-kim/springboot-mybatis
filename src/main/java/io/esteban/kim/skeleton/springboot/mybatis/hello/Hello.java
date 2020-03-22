package io.esteban.kim.skeleton.springboot.mybatis.hello;

import io.esteban.kim.skeleton.springboot.mybatis.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @Autowired
    HelloService helloService;

    @RequestMapping("/")
    public String root() {
        return "hello";
    }

    @RequestMapping("/writer")
    public String writer() throws Exception {
        return helloService.getWriterDb();
    }

    @RequestMapping("/reader")
    public String reader() throws Exception {
        return helloService.getReaderDb();
    }
}