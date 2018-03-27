package smartdish.com.order.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import smartdish.com.R;
import smartdish.com.base.BaseFragment;
import smartdish.com.base.utils.MyUtils;
import smartdish.com.order.adapter.OrderAdapter;
import smartdish.com.order.bean.OrderBean;

/**
 * 菜品界面的fragment
 */

public class OrderFragment extends BaseFragment {
    private ListView lv_item_order;
    private OrderBean data;
    private TextView tv_empty_order_item;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_order,null);
        lv_item_order = view.findViewById(R.id.lv_item_order);
        tv_empty_order_item = view.findViewById(R.id.tv_empty_order_item);
        initData();
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
        String username = MyUtils.getUsername(mContext);
        OkHttpUtils.get()
                .url(url+"/getOrder")
                .addParams("username",username)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println(e);
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        try{
                            data = JSON.parseObject(response,OrderBean.class);
                        }catch (Exception e){
                            Toast.makeText(mContext, e+"", Toast.LENGTH_SHORT).show();
                            System.out.println(e.toString());
                        }
                        if(data == null || data.getList().size()==0){ // 订单为空
                            lv_item_order.setVisibility(View.GONE);
                            tv_empty_order_item.setVisibility(View.VISIBLE);
                        }else{ // 展示订单信息
                            lv_item_order.setVisibility(View.VISIBLE);
                            tv_empty_order_item.setVisibility(View.GONE);
                            lv_item_order.setAdapter(new OrderAdapter(mContext,data.getList()));
                        }

                    }
                });
    }
}
