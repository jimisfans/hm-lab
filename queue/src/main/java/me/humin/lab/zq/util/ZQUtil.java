package me.humin.lab.zq.util;

import java.util.Collection;

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

    public static boolean isEmpty(Collection var) {
        return !isNotEmpty(var);
    }

    public static boolean isNotEmpty(Collection var) {
        return var != null && var.size() > 0;
    }
}
