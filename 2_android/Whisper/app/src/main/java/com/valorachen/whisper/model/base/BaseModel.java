package com.valorachen.whisper.model.base;

import java.io.Serializable;

/**
 * Created by vivi on 2016/9/17.
 */
public class BaseModel implements Serializable {

    private int status;
    private String msg;

    public int getCode() {
        return status;
    }

    public void setCode(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String message) {
        this.msg = msg;
    }
}
