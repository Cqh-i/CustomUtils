package com.qunhuichen.utils;

import com.qunhuichen.utils.util.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UtilsApplication {

    private static final Logger logger = LoggerFactory.getLogger(UtilsApplication.class);

    public static void main(String[] args) {
        logger.debug("this is debug");
        logger.info("this is info");
        SpringApplication.run(UtilsApplication.class, args);
    }

}
