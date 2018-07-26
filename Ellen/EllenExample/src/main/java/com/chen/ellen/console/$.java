package com.chen.ellen.console;

import com.chen.jeneral.utils.DateUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class $ {

    private static Logger logger = Logger.getLogger($.class);

    public static void echo(CharSequence words) {
//        System.out.println(words);
//        logger.info(words);
        System.out.println(DateUtils.currentTime() + " : " + words);
    }

    public static String enter() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String readLine = null;
        try {
            while ((readLine = reader.readLine()) != null) {
                break;
            }
        } catch (IOException e) {
//            e.printStackTrace();
            logger.error(e);
        } finally {
            return readLine;
        }
    }
}
