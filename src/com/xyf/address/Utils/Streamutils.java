package com.xyf.address.Utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by shxiayf on 2015/12/8.
 */
public class Streamutils {

    public static String parseStream2String(InputStream is)
    {
        try {
            byte[] buf = new byte[1024];
            int rs = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while((rs = is.read(buf)) != -1)
            {
                baos.write(buf,0,rs);
            }
            baos.flush();
            is.close();
            String content = new String(baos.toByteArray());
            baos.close();

            LogUtils.i(Streamutils.class.getSimpleName(),String.format("download:[%s]",content));

            return content;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return "";
    }

}
