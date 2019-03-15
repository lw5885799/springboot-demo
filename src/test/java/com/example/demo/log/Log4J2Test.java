package com.example.demo.log;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class Log4J2Test {

    // private static final Logger logger = LoggerFactory.getLogger(Log4J2Test.class);

    @Test
    public void Log4J2SimpleTest(){

        log.debug("debug test ");
        log.info("info test ");
        log.error("error test ");
        log.warn("warn test ");
    }

}
