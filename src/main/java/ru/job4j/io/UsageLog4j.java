package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
/*        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");*/
        double double01 = 2.2D;
        int int02 = 11;
        short short03 = 1;
        long long04 = 123456;
        float float05 = 1.1F;
        char char06 = 'A';
        byte byte07 = 127;
        boolean bol08 = true;
        LOG.debug("variables : double : {}, int : {}, short : {}, long : {}, "
                + "float : {}, char : {}, byte : {}, boolean : {}",
                double01, int02, short03, long04, float05, char06, byte07, bol08);
    }
}