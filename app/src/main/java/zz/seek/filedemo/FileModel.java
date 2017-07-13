package zz.seek.filedemo;

/**
 * Created by admin on 2017/7/13.
 */

public class FileModel {

    /**
     * code : 100
     * data : {"id":1,"doc":"http://fangtang.jzphp.com/static/team/document/152/云社区O2O项目商业策划书.pdf"}
     * result : 文件上传保存成功
     */

    private int code;
    private DataBean data;
    private String result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static class DataBean {
        /**
         * id : 1
         * doc : http://fangtang.jzphp.com/static/team/document/152/云社区O2O项目商业策划书.pdf
         */

        private int id;
        private String doc;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDoc() {
            return doc;
        }

        public void setDoc(String doc) {
            this.doc = doc;
        }
    }
}
