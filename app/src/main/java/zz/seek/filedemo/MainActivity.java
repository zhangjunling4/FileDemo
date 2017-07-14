package zz.seek.filedemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;

import zz.seek.filedemo.callback.FileCallBack;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    //教师上传文档接口    POST id (教师id)
    private final static String UPFile = "http://fangtang.jzphp.com/m/molie/app_teacher/docUp.php?";

    private String path;

    private TextView upFile;
    private TextView downFile;

    private ProgressBar progressBarUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upFile = (TextView) findViewById(R.id.up_file);
        downFile = (TextView) findViewById(R.id.down_file);
        progressBarUp = (ProgressBar) findViewById(R.id.progressBar_up);

        //上传文件
        upFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.up_file), Toast.LENGTH_SHORT).show();
                FileUtils.selectFileFromMobile(MainActivity.this);
            }
        });

        //下载文件
        downFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.file_list), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, FileListActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if ("file".equalsIgnoreCase(uri.getScheme())){//使用第三方应用打开
                path = uri.getPath();
                Log.i(TAG, "Path="+path);

                Toast.makeText(this,path+"11111",Toast.LENGTH_SHORT).show();
                return;
            }
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
                path = FileUtils.getPath(this, uri);
                Log.i(TAG, "Path="+path);
                Toast.makeText(this,path,Toast.LENGTH_SHORT).show();
            } else {//4.4以下下系统调用方法
                path = FileUtils.getRealPathFromURI(MainActivity.this, uri);
                Log.i(TAG, "Path="+path);
                Toast.makeText(MainActivity.this, path+"222222", Toast.LENGTH_SHORT).show();
            }

            upFileToServlet(path);
        }
    }

    private void upFileToServlet(String path) {
        final File file = new File(path);
        OkHttpUtils.post()
                .addFile("file", file.getName(), file)
                .addParams("id", "152")
                .url(UPFile).build().execute(new FileCallBack() {

            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(FileModel response) {
                Toast.makeText(MainActivity.this, response.getResult(), Toast.LENGTH_SHORT).show();

                upFile.setText("上传完毕");
                progressBarUp.setVisibility(View.GONE);
            }

            @Override
            public void inProgress(float progress) {
                super.inProgress(progress);

                progressBarUp.setVisibility(View.VISIBLE);
                Log.i(TAG, "progress=" + progress);
                upFile.setText("正在上传中..."+Math.round(progress * 100)+"%");
                progressBarUp.setProgress((int)(progress * 100));

            }
        });

    }
}
