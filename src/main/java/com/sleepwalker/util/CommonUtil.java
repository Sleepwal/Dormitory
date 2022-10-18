package com.sleepwalker.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @package: com.sleepwalker.util
 * @className: CommonUtil
 * @author: SleepWalker
 * @description: TODO
 * @date: 21:15
 * @version: 1.0
 */
public class CommonUtil {
    public static String createDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
}
