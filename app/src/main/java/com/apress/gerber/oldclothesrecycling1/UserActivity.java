package com.apress.gerber.oldclothesrecycling1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.apress.gerber.oldclothesrecycling.R;

public class UserActivity extends AppCompatActivity {
    private Button mretrieve;                                       //回收
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mretrieve=(Button)findViewById(R.id.retrieve);
        mretrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,RetrieveActivity.class);
                startActivity(intent);

            }
        });
    }

}
