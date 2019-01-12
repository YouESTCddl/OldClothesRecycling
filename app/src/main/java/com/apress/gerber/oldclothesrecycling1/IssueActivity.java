package com.apress.gerber.oldclothesrecycling1;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apress.gerber.oldclothesrecycling.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IssueActivity extends AppCompatActivity {
    private ListView lv;
    public ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    static int flag=0;
    private String url="http://ssh2.evi0s.com:3000/alldata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);
        lv=(ListView)findViewById(R.id.listview);
        init();
    }
    private void init(){
        list.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    OkHttpClient okHttpClient=new OkHttpClient();
                    //服务器返回的地址
                    Request request=new Request.Builder().url(url).build();
                    Response response=okHttpClient.newCall(request).execute();

                    //获取数据
                    String data=response.body().string();
                    //把数据传入解析json的数据方法
                    jsonJX(data);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void jsonJX(String data){
        Log.d("IssueActivity","dio");
        if(data!=null){
            try{
                JSONObject jsonObject=new JSONObject(data);
                String code=jsonObject.getString("error_code");
                if(code.equals("0")){
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0;i<jsonArray.length();i++){
                        Map<String,Object> map=new HashMap<String,Object>();
                        JSONObject object=jsonArray.getJSONObject(i);
                        String pic=object.getString("pic");
                        String title=object.getString("title");
                        Log.d("IssueActivity","dio");
                        map.put("title",title);
                        map.put("pic",pic);
                        list.add(map);
                    }
                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                }
                else {
                    Message message=new Message();
                    message.what=2;
                    handler.sendMessage(message);
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
    public Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    Mybaseadapter list_item=new Mybaseadapter();
                    lv.setAdapter(list_item);
                    list_item.notifyDataSetChanged();
                    break;
                case 2:
                    Toast.makeText(IssueActivity.this,"数据错误",Toast.LENGTH_LONG).show();
                    break;
            }
        };
    };
    //listview的适配器
    public class Mybaseadapter extends BaseAdapter{
        @Override
        public int getCount(){
            return list.size();
        }
        @Override
        public Object getItem(int position){
            return list.get(position);
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            ViewHolder viewHolder=null;
            if(convertView==null){
                viewHolder = new ViewHolder();
                LayoutInflater layoutInflater=LayoutInflater.from(IssueActivity.this);
                convertView=layoutInflater.inflate(R.layout.listview_item,null);
                viewHolder.img=(ImageView)convertView.findViewById(R.id.list_img);
                viewHolder.textView=(TextView)convertView.findViewById(R.id.list_textview);
                convertView.setTag(viewHolder);
            }
            else viewHolder=(ViewHolder)convertView.getTag();
            //判断获取的json中图片是否为空
            if(TextUtils.isEmpty(list.get(position).get("pic").toString())){
                Picasso.with(IssueActivity.this).cancelRequest(viewHolder.img);
                viewHolder.img.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background,null));
            }
            else{
                //图片加载
                Picasso.with(IssueActivity.this)
                        .load(list.get(position).get("pic").toString())
                        .placeholder(R.drawable.ic_launcher_background).into(viewHolder.img);
            }
            viewHolder.textView.setText(list.get(position).get("title").toString());
            return convertView;
        }
    }
    final class ViewHolder{
        TextView textView;
        ImageView img;
    }
}
