package com.apress.gerber.oldclothesrecycling1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.apress.gerber.oldclothesrecycling.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecoverActivity extends AppCompatActivity {
    EditText timeText,phoneText,commentText,orderText;
    Button submitButton;
    String url="http://10.0.2.2/test.json";
    static int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);
        timeText=findViewById(R.id.recover_time);
        phoneText=findViewById(R.id.recover_phone);
        commentText=findViewById(R.id.recover_comments);
        orderText=findViewById(R.id.order_number);
        submitButton=findViewById(R.id.recover_commit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String s=timeText.getText().toString()+phoneText.getText().toString()+commentText.getText().toString()+orderText.getText().toString();
                            OkHttpClient okHttpClient=new OkHttpClient();//创建OkHttpClient对象
                            //提交的接口还未完成
                            Request request = new Request.Builder()
                                    .url(url)
                                    .build();
                            Response response = null;
                            response = okHttpClient.newCall(request).execute();//得到Response 对象
                            if (response.isSuccessful()) {
                                flag=1;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                if (flag==1)
                    Toast.makeText(RecoverActivity.this,"成功预约回收，您将会在三天内收到估价信息",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(RecoverActivity.this,"抱歉，网络错误",Toast.LENGTH_SHORT).show();
            }
        });




    }

}