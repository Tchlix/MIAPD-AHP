package com.agh.vacation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logging {
    private Logging(){
    }
    private static final Logger logger = LogManager.getLogger("Logger");
    public static void error(Object message){
        logger.error(message);
    }
    public static void info(Object message){
        logger.info(message);
    }
}
