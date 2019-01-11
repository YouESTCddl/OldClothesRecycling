package com.apress.gerber.oldclothesrecycling1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.apress.gerber.oldclothesrecycling.R;
import com.google.gson.Gson;


import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class QueryActivity extends AppCompatActivity {
    TextView titleText,balanceText;
    Button backButton;
    String url;
    static int flag=0;
    Gson gson=new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        titleText=findViewById(R.id.titleText);
        balanceText=findViewById(R.id.balancetext);
        backButton=findViewById(R.id.backbutton);
        Response response;
        httpUtlil();
        try {
            if (flag==1)
                balanceText.setText(gson.fromJson(httpUtlil().body().string(),String.class));
        }catch (IOException e){
            e.printStackTrace();
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public Response httpUtlil(){
        final Response[] response = new Response[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                Request request=new Request.Builder().url("http://10.0.2.2/test.json").build();

                try {
                    response[0] =okHttpClient.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (response[0].isSuccessful())
                    flag=1;
            }

        }).start();
        return response[0];

    }
}