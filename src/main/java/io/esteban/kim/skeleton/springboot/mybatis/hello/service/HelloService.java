package io.esteban.kim.skeleton.springboot.mybatis.hello.service;

import io.esteban.kim.skeleton.springboot.mybatis.dao.readerdb.HelloReaderMapper;
import io.esteban.kim.skeleton.springboot.mybatis.dao.writerdb.HelloWriterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @Autowired
    HelloWriterMapper writerMapper;

    @Autowired
    HelloReaderMapper readerMapper;

    public String getWriterDb() throws Exception {
        return writerMapper.getWriterDB();
    }

    public String getReaderDb() throws Exception {
        return readerMapper.getReaderDB();
    }
}