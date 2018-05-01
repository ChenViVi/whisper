package com.valorachen.whisper.model;

import com.valorachen.whisper.model.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vivi on 2018/4/30.
 */

public class Article extends BaseModel{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 4
         * type_id : 3
         * type_name : to
         * content : dfvffdv bfdvb
         * time : 2018-04-30 22:04:12
         */

        private int id;
        private int type_id;
        private String type_name;
        private String content;
        private String time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
