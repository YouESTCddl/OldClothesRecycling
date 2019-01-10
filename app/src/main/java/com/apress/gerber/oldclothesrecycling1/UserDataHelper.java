package com.apress.gerber.oldclothesrecycling1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wuruoling on 2018/12/25.
 */

public class UserDataHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "user_data";//数据库名
    private static final String TABLE_NAME = "users";//表名
    public static final String ID = "verdsion_id";//版本号
    public static final String USER_NAME = "user_name";//用户名
    public static final String USER_PWD = "user_pwd";//用户密码
    private Context context;
    
    private static final String CREATE_USERTABLE="CREATE"+TABLE_NAME+"("+ ID + " integer primary key," + USER_NAME + " varchar,"
            + USER_PWD + " varchar" + ")";
    public UserDataHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USERTABLE);

    }
    public void onUpgrade(SQLiteDatabase db,int OldVersion,int newVersion){
        db.execSQL("drop table if exists "+TABLE_NAME+";");
        onCreate(db);

    }
}
