package com.xyf.address.model;

import java.io.Serializable;

/**
 * Created by shxiayf on 2015/12/9.
 */
public class UpdateResponseBean implements Serializable {

    private int isUpdate;
    private String xlsVersion;
    private String downUrl;

    public void setIsUpdate(int isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setXlsVersion(String xlsVersion) {
        this.xlsVersion = xlsVersion;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public int getIsUpdate() {
        return isUpdate;
    }

    public String getXlsVersion() {
        return xlsVersion;
    }

    public String getDownUrl() {
        return downUrl;
    }
}
