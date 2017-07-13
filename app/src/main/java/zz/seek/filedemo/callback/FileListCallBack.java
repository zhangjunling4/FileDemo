package zz.seek.filedemo.callback;

import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import zz.seek.filedemo.FileListModel;
import zz.seek.filedemo.FileModel;

/**
 * Created by admin on 2017/7/13.
 */

public abstract class FileListCallBack extends Callback<FileListModel> {
    @Override
    public FileListModel parseNetworkResponse(Response response) throws IOException {
        String result = response.body().string();
        FileListModel fileModel = JSONObject.parseObject(result, FileListModel.class);
        return fileModel;
    }
}
