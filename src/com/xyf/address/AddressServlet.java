package com.xyf.address;

import com.xyf.address.Utils.ConfigUtils;
import com.xyf.address.Utils.JsonUtils;
import com.xyf.address.Utils.LogUtils;
import com.xyf.address.Utils.Streamutils;
import com.xyf.address.model.ConfigBean;
import com.xyf.address.model.UpdateRequestBean;
import com.xyf.address.model.UpdateResponseBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by shxiayf on 2015/12/10.
 */
public class AddressServlet extends HttpServlet {

    private static final String TAG = AddressServlet.class.getName();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogUtils.i(TAG, "asscess get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String content = Streamutils.parseStream2String(req.getInputStream());

        UpdateRequestBean requestBean = (UpdateRequestBean) JsonUtils.parseString2Obj(content,UpdateRequestBean.class);

        String configFilePath = ConfigUtils.getInstances().getWebINFPath() + "WEB-INF" + File.separator + "update.config";
        File configFile = new File(configFilePath);

        if (!configFile.exists())
        {
            responseNoUpdate(resp);
            return;
        }

        String configContent = Streamutils.parseStream2String(new FileInputStream(configFile));
        ConfigBean configBean = (ConfigBean) JsonUtils.parseString2Obj(configContent,ConfigBean.class);

        if (configBean.getVersion().compareToIgnoreCase(requestBean.getXlsVersion()) > 0 && configBean.getPackageName().equals(requestBean.getPackageName()))
        {
            String apkPath = ConfigUtils.getInstances().getWebINFPath() + File.separator + configBean.getFilename();

            File apkFile = new File(apkPath);

            if (!apkFile.exists())
            {
                responseNoUpdate(resp);
                return;
            }

            UpdateResponseBean responseBean = new UpdateResponseBean();
            String respContent = "";

            responseBean.setIsUpdate(1);
            responseBean.setDownUrl("http://192.168.0.77:8080/" + configBean.getFilename());
            responseBean.setXlsVersion(configBean.getVersion());

            respContent = JsonUtils.parseObj2String(responseBean);

            resp.getWriter().write(respContent);
            resp.getWriter().flush();
            resp.getWriter().close();

            return;
        }

    }

    private void responseNoUpdate(HttpServletResponse resp) throws IOException {
        UpdateResponseBean responseBean = new UpdateResponseBean();
        String respContent = "";

        responseBean.setIsUpdate(0);
        responseBean.setXlsVersion("");
        responseBean.setDownUrl("");

        respContent = JsonUtils.parseObj2String(responseBean);

        resp.getWriter().write(respContent);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

}
