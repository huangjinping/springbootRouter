package com.winter.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
    static Logger logger = LoggerFactory.getLogger("OKHTTP");

    public static void logFormat(Object o) {
        logger.info(JSON.toJSONStringWithDateFormat(o, "yyyy-MM-dd HH:mm:ss"));
    }

    public static void log(Object o) {
        String s = JSON.toJSONString(o);
        logger.info(s);
    }
}
