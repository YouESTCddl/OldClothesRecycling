package com.apress.gerber.oldclothesrecycling1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apress.gerber.oldclothesrecycling.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText mAccount;                        //用户名编辑
    private EditText mPwd;                            //密码编辑
    private EditText mPwdCheck;                       //密码编辑
    private Button mSureButton;                       //确定按钮
    private Button mCancelButton;                     //取消按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAccount = (EditText) findViewById(R.id.resetpwd_edit_name);
        mPwd = (EditText) findViewById(R.id.resetpwd_edit_pwd_old);
        mPwdCheck = (EditText) findViewById(R.id.resetpwd_edit_pwd_new);
        mSureButton = (Button) findViewById(R.id.register_btn_sure);
        mCancelButton = (Button) findViewById(R.id.register_btn_cancel);
        View.OnClickListener m_register_Listener = new View.OnClickListener() {    //不同按钮按下的监听事件选择
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.register_btn_sure:                       //确认按钮的监听事件
                        register_check();
                        break;
                    case R.id.register_btn_cancel:                     //取消按钮的监听事件,由注册界面返回登录界面
                        Intent intent_Register_to_Login = new Intent(RegisterActivity.this,LoginActivity.class) ;    //切换User Activity至Login Activity
                        startActivity(intent_Register_to_Login);
                        finish();
                        break;
                }
            }
        };
        mSureButton.setOnClickListener(m_register_Listener);      //注册界面两个按钮的监听事件
        mCancelButton.setOnClickListener(m_register_Listener);


    }
    public void register_check(){
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();
            String userPwd = mPwd.getText().toString().trim();
            String userPwdCheck = mPwdCheck.getText().toString().trim();
            int count=0;//检查用户是否存在,数据库还没写orz
            if(count>0){
                Toast.makeText(this, "this name has already existed", Toast.LENGTH_SHORT).show();
                return ;
            } //用户已经存在时返回，给出提示文字
            if(userPwd.equals(userPwdCheck)==false){     //两次密码输入不一样
                Toast.makeText(this, "the checking password is incorrect",Toast.LENGTH_SHORT).show();
                return ;
            }
            else {
                long flag =0;
                if (flag == -1) {
                    Toast.makeText(this, "sorry,register failed",Toast.LENGTH_SHORT).show();
                }//注册失败
                else{
                    Toast.makeText(this,"Register successes",Toast.LENGTH_SHORT).show();
                }
            }//新建用户信息,数据库还没写orz

        Intent intent=new Intent(RegisterActivity.this,UserActivity.class);
        }

    }
    public boolean isUserNameAndPwdValid() {
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, "the account is empty",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, "the password is empty",
                    Toast.LENGTH_SHORT).show();
            return false;
        }else if(mPwdCheck.getText().toString().trim().equals("")) {
            Toast.makeText(this, "the passwork checking is empty",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
