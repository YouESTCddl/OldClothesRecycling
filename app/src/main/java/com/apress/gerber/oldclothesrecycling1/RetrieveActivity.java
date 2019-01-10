package com.apress.gerber.oldclothesrecycling1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.apress.gerber.oldclothesrecycling.R;

public class RetrieveActivity extends AppCompatActivity {
    private Button mIssue;                                      //旧衣发布
    private Button mEvaluate;                                   //预约估价
    private Button mRecover;                                    //预约回收
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        //
        mIssue=(Button)findViewById(R.id.issue);
        mEvaluate=(Button)findViewById(R.id.evaluate);
        mRecover=(Button)findViewById(R.id.recover);
        //
        mIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RetrieveActivity.this,IssueActivity.class);
                startActivity(intent);
            }
        });

        mEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RetrieveActivity.this,EvaluateActivity.class);
                startActivity(intent);
            }
        });

        mRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RetrieveActivity.this,RecoverActivity.class);
                startActivity(intent);
            }
        });
        //
    }
}
