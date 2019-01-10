package com.apress.gerber.oldclothesrecycling1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.apress.gerber.oldclothesrecycling.R;

public class RecoverActivity extends AppCompatActivity {
    private EditText mtime;
    private EditText mplace;
    private EditText mphone;
    private EditText mcomments;
    private Button mcommit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);
        mtime=(EditText)findViewById(R.id.recover_time);
        mplace=(EditText)findViewById(R.id.recover_place);
        mphone=(EditText)findViewById(R.id.recover_phone);
        mcomments=(EditText)findViewById(R.id.recover_comments);
        mcommit=(Button)findViewById(R.id.recover_commit);
        //test

    }
}
