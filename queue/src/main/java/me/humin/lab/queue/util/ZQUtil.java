package me.humin.lab.queue.util;

/**
 * @author: humin
 * @date: 2019-07-25
 */
public class ZQUtil {

    public static boolean isEmpty(String var) {
       return !isNotEmpty(var);
    }

    public static boolean isNotEmpty(String var) {
        return var != null && var.length() > 0;
    }

}
