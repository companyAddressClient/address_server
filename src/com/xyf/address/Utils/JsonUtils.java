package com.xyf.address.Utils;

import org.json.JSONObject;

import java.lang.reflect.Field;

/**
 * Created by shxiayf on 2015/12/8.
 */
public class JsonUtils {

    public static String parseObj2String(Object obj)
    {
        try{
            JSONObject jsonObject = new JSONObject();
            for (Field tmp : obj.getClass().getDeclaredFields())
            {
                tmp.setAccessible(true);
                jsonObject.put(tmp.getName(),tmp.get(obj));
            }
            return jsonObject.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return "";
    }

    public static Object parseString2Obj(String json,Class<?> clz)
    {
        try{
            return parseString2Obj(new JSONObject(json),clz);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static Object parseString2Obj(JSONObject json,Class<?> clz)
    {
        try{
            Object obj = clz.newInstance();
            for (Field tmp : clz.getDeclaredFields())
            {
                tmp.setAccessible(true);
                try
                {
                    if (tmp.getType().getName().equals("int"))
                    {
                        tmp.setInt(obj,json.getInt(tmp.getName()));
                    }
                    else if (tmp.getType().getName().equals("long"))
                    {
                        tmp.setLong(obj,json.getLong(tmp.getName()));
                    }
                    else
                    {
                        tmp.set(obj,json.get(tmp.getName()));
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            return obj;
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

}


