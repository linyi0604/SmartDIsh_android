package smartdish.com.order.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import smartdish.com.R;
import smartdish.com.base.adapter.GoodItemAdapter;
import smartdish.com.base.bean.GoodBean;
import smartdish.com.order.bean.OrderBean;
import smartdish.com.order.bean.OrderListBean;

public class OrderItemInfoActivity extends Activity {
    private Context mContext;
    private String order_id;
    private ListView lv_order_item;
    private OrderListBean order;

    public void findViews(){
        mContext = OrderItemInfoActivity.this;
        order_id = getIntent().getStringExtra("order_id");
        lv_order_item = findViewById(R.id.lv_order_item);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item_info);
        findViews();
        initData();


    }
    public void initData(){
        String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
        OkHttpUtils.get()
                .url(url+"/getOrderInfo")
                .addParams("order_id",order_id)
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
                        System.out.println(response);
                        try{
                            order = JSON.parseObject(response,OrderListBean.class);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        if(order != null && order.getDish_list().size()>0){
                            showData(order);
                        }
                    }
                });
    }
    public void showData(OrderListBean order){
        TextView tv_restaurant_name = findViewById(R.id.tv_restaurant_name);
        TextView tv_order_state = findViewById(R.id.tv_order_state);
        TextView tv_dish_count_total_price= findViewById(R.id.tv_dish_count_total_price);
        TextView tv_order_time = findViewById(R.id.tv_order_time);

        tv_restaurant_name.setText(order.getRestaurant()+" >");
        tv_order_state.setText(order.getIs_payed());
        tv_dish_count_total_price.setText("共计 "+order.getDish_count()+" 件菜品 "+order.getTotal_price()+" 元");
        tv_order_time.setText(order.getTime());

        lv_order_item.setAdapter(new GoodItemAdapter(mContext,order.getDish_list()));
    }
}
