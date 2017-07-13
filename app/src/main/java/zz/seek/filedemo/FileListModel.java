package zz.seek.filedemo;

import java.util.List;

/**
 * Created by admin on 2017/7/13.
 */

public class FileListModel {


    /**
     * code : 100
     * data : [{"id":"1","partner_id":"152","doc_url":"http://fangtang.jzphp.com/static/team/document/152/云社区O2O项目商业策划书.pdf","name":"云社区O2O项目商业策划书"}]
     * result : 获取文件列表成功
     */

    private int code;
    private String result;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * partner_id : 152
         * doc_url : http://fangtang.jzphp.com/static/team/document/152/云社区O2O项目商业策划书.pdf
         * name : 云社区O2O项目商业策划书
         */

        private String id;
        private String partner_id;
        private String doc_url;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPartner_id() {
            return partner_id;
        }

        public void setPartner_id(String partner_id) {
            this.partner_id = partner_id;
        }

        public String getDoc_url() {
            return doc_url;
        }

        public void setDoc_url(String doc_url) {
            this.doc_url = doc_url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
