package com.xyf.address.Utils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by shxiayf on 2015/12/8.
 */
public class ConfigUtils {

    private static final class Util {
        public static final ConfigUtils INSTANCES = new ConfigUtils();
    }

    public static ConfigUtils getInstances()
    {
        return Util.INSTANCES;
    }


    private URL getURLOfClassPath() {
        return this.getClass().getClassLoader().getResource("/");
    }

    private String getAbsolutePathOfClassPath() {
        try {
            URI uri = this.getURLOfClassPath().toURI();
            File tmp = new File(uri);
            return tmp.getAbsolutePath();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String getWebINFPath() {
        String absolutePathOfClassPath = this.getAbsolutePathOfClassPath();
        String pathStr = absolutePathOfClassPath.replace(File.separator, "/");
        pathStr = pathStr.substring(0, pathStr.indexOf("WEB-INF"));

        return pathStr;
    }



}
