package zz.seek.filedemo.callback;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import zz.seek.filedemo.FileModel;

/**
 * Created by admin on 2017/7/13.
 */

public abstract class FileCallBack extends Callback<FileModel> {
    @Override
    public FileModel parseNetworkResponse(Response response) throws IOException {
        String result = response.body().string();

        Log.i("result", "result="+result);
        FileModel fileModel = JSONObject.parseObject(result, FileModel.class);
        return fileModel;
    }
}
