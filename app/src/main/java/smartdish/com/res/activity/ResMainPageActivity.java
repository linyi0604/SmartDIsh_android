package smartdish.com.res.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;

import okhttp3.Call;
import smartdish.com.R;
import smartdish.com.res.bean.ResBean;

public class ResMainPageActivity extends Activity implements View.OnClickListener {
    private Context mContext;
    private ResBean data;
    private ImageButton ib_res_main_back;
    private ImageView iv_res_image;
    private TextView tv_res_name;
    private TextView tv_res_address;
    private TextView tv_res_phone;
    private TextView tv_res_grade;
    private TextView tv_res_sell_count;
    private TextView tv_res_detail;
    private String res_id;
    private TextView tv_res_dish_list;

    public void findViews(){
        mContext = this;
        res_id = getIntent().getStringExtra("res_id");
        ib_res_main_back = findViewById(R.id.ib_res_main_back);
        iv_res_image = findViewById(R.id.iv_res_image);
        tv_res_name = findViewById(R.id.tv_res_name);
        tv_res_address = findViewById(R.id.tv_res_address);
        tv_res_phone = findViewById(R.id.tv_res_phone);
        tv_res_grade = findViewById(R.id.tv_res_grade);
        tv_res_sell_count = findViewById(R.id.tv_res_sell_count);
        tv_res_detail = findViewById(R.id.tv_res_detail);
        tv_res_dish_list = findViewById(R.id.tv_res_dish_list);

        ib_res_main_back.setOnClickListener(this);
        tv_res_dish_list.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == ib_res_main_back){
            finish();
        }else if(v == tv_res_dish_list){
            Intent intent = new Intent(mContext,ResDishActivity.class);
            intent.putExtra("res_id",data.getId());
            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_main_page);
        findViews();
        initData();
    }
    public void initData(){
        // 发送网络请求 进行删除该条收藏
        String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
        OkHttpUtils.get()
                .url(url+"/getResInfo")
                .addParams("res_id",res_id)
                .build()
                .execute(new StringCallback() {
                    // 请求失败的时候
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println(e);
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
                    }
                    // 请求成功的时候
                    @Override
                    public void onResponse(final String response, int id) {
                        data = null;
                        try{
                            data = JSON.parseObject(response,ResBean.class);
                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(mContext, "发生错误："+response, Toast.LENGTH_SHORT).show();
                        }
                        if(data == null){
                            Toast.makeText(mContext, "网络状况不好！", Toast.LENGTH_SHORT).show();
                        }else{
                            showData(data);
                        }
                    }
                });
    }
    public void showData(ResBean data){
        Glide.with(mContext).load(mContext.getString(R.string.base_url)+data.getImage_url()).into(iv_res_image);
        tv_res_name.setText("餐厅："+data.getName());
        tv_res_address.setText("地址："+data.getAddress());
        tv_res_phone.setText("电话："+data.getPhone());
        tv_res_grade.setText("评分："+data.getGrade());
        tv_res_sell_count.setText("月售："+data.getSellCount());
        tv_res_detail.setText("介绍"+data.getDetail());

    }
}
