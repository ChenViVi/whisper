package com.valorachen.whisper.model;

import com.valorachen.whisper.model.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vivi on 2018/4/30.
 */

public class Type extends BaseModel {

    /**
     * status : 0
     * msg : 查询成功
     * data : [{"id":2,"name":"心情"},{"id":3,"name":"2333"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 2
         * name : 心情
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
