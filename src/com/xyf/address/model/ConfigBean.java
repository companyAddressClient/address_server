package com.xyf.address.model;

import java.io.Serializable;

/**
 * Created by shxiayf on 2015/12/10.
 */
public class ConfigBean implements Serializable{

    private String version;
    private String packageName;
    private String filename;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
