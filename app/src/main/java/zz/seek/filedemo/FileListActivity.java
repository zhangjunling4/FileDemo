package zz.seek.filedemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import zz.seek.filedemo.callback.FileListCallBack;

public class FileListActivity extends AppCompatActivity {

    //学生下载文档接口    POST partner_id (教师id)
    private final static String DownFile = "http://fangtang.jzphp.com/m/molie/app_student/docList.php?";

    private RecyclerView fileList;
    private List<FileListModel.DataBean> fileListData;
    private FileListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);

        fileList = (RecyclerView) findViewById(R.id.file_list);

        OkHttpUtils.post().url(DownFile)
                .addParams("partner_id", "152")
                .build()
                .execute(new FileListCallBack() {
                             @Override
                             public void onError(Request request, Exception e) {

                             }

                             @Override
                             public void onResponse(FileListModel response) {
                                 fileListData = response.getData();

                                 adapter = new FileListAdapter(FileListActivity.this, fileListData);
                                 fileList.setAdapter(adapter);
                                 fileList.setLayoutManager(new LinearLayoutManager(FileListActivity.this));
                             }
                         }
                );



    }

    class FileListAdapter extends RecyclerView.Adapter<FileHolder>{

        private List<FileListModel.DataBean> fileListData;
        private Context mContext;

        public FileListAdapter(Context context, List<FileListModel.DataBean> fileListData) {
            this.mContext = context;
            this.fileListData = fileListData;
        }
        @Override
        public FileHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.file_list_item, parent, false);;
            FileHolder holder = new FileHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final FileHolder holder, int position) {

            holder.fileName.setText(fileListData.get(position).getName());
            holder.fileDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, getResources().getString(R.string.down_file), Toast.LENGTH_SHORT).show();
                    holder.fileDownProcess.setVisibility(View.VISIBLE);

                }
            });
        }

        @Override
        public int getItemCount() {
            return fileListData.size();
        }
    }

    class FileHolder extends RecyclerView.ViewHolder{
        private TextView fileName;
        private TextView fileDownload;
        private ProgressBar fileDownProcess;

        public FileHolder(View itemView) {
            super(itemView);
            fileName = (TextView) itemView.findViewById(R.id.file_item_name);
            fileDownload = (TextView) itemView.findViewById(R.id.file_item_download);
            fileDownProcess = (ProgressBar) itemView.findViewById(R.id.file_item_process);
        }
    }
}
