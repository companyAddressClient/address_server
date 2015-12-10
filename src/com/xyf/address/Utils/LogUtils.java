package com.xyf.address.Utils;

/**
 * Created by shxiayf on 2015/12/8.
 */
public class LogUtils {

    private static final boolean debug = true;

    public static void i(String ...args)
    {
        if (!debug)
        {
            return;
        }

        if (args.length != 2)
        {
            return;
        }

        System.out.println(String.format("[%s][%s]",args[0],args[1]));
    }

}
