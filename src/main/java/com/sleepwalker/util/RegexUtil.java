package com.sleepwalker.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @package: com.sleepwalker.util
 * @className: RegexUtil
 * @author: SleepWalker
 * @description: 匹配正则
 * @date: 13:22
 * @version: 1.0
 */
public class RegexUtil {

    public static String replaceRegex(String regex, String target, String replace) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.replaceAll(replace).trim();
    }
}
