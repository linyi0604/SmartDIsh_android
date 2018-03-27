package smartdish.com.res.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import smartdish.com.R;
import smartdish.com.res.adapter.ResDishTypeAdapter;
import smartdish.com.res.adapter.ResDishTypeDishAdapter;
import smartdish.com.res.bean.ResDishTypeListDishListBean;

public class ResDishActivity extends Activity {
    private Context mContext;
    private String res_id;
    private ListView lv_menu;
    private ListView lv_dish;
    private List<ResDishTypeListDishListBean.ResDishTypeBean> typeList;
    private TextView tv_empty_dish_list;

    public void findViews(){
        mContext = ResDishActivity.this;
        res_id = getIntent().getStringExtra("res_id");
        lv_menu =  findViewById(R.id.lv_menu);
        lv_dish = findViewById(R.id.lv_dish);
        tv_empty_dish_list= findViewById(R.id.tv_empty_dish_list);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_dish);
        findViews();
        initData();

    }

    public void initData(){
        String url = mContext.getString(R.string.base_url) + mContext.getString(R.string.appUrl);
        OkHttpUtils
            .get()
            .url(url+"/getTypeDishInfo")
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

                @Override
                public void onResponse(String response, int id) {
                    ResDishTypeListDishListBean resDishTypeBean = null;
                    try{
                        resDishTypeBean = JSON.parseObject(response,ResDishTypeListDishListBean.class);


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if(resDishTypeBean !=null && resDishTypeBean.getList().size()>0 ){
                        typeList = resDishTypeBean.getList();
                        lv_menu.setAdapter(new ResDishTypeAdapter(mContext,typeList,lv_dish,tv_empty_dish_list));
                        lv_dish.setAdapter(new ResDishTypeDishAdapter(mContext,typeList.get(0).getDishList(),tv_empty_dish_list));
                    }else{
                        Toast.makeText(mContext, "出现问题~抱歉哦~", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
    }
}
