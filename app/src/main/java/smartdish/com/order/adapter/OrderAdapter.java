package smartdish.com.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import smartdish.com.R;
import smartdish.com.base.adapter.GoodItemAdapter;
import smartdish.com.base.bean.GoodBean;
import smartdish.com.order.activity.OrderItemInfoActivity;
import smartdish.com.order.bean.OrderBean;
import smartdish.com.order.bean.OrderListBean;

/**
 * Created by Lenovo on 2018/3/11.
 */

public class OrderAdapter extends BaseAdapter {
    private Context mContext;
    private List<OrderListBean> orderList;

    public OrderAdapter(Context mContext, List<OrderListBean> orderList){
        this.mContext = mContext;
        this.orderList = orderList;

    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView != null){
            view = convertView;
        }else{
            view = View.inflate(mContext, R.layout.order_item_layout,null);
        }

        showData(view,orderList.get(position));

        return view;
    }

    public void showData(View view, final OrderListBean order){
        TextView tv_restaurant_name = view.findViewById(R.id.tv_restaurant_name);
        TextView tv_order_state = view.findViewById(R.id.tv_order_state);
        TextView tv_dish_count_total_price= view.findViewById(R.id.tv_dish_count_total_price);
        TextView tv_order_time = view.findViewById(R.id.tv_order_time);

        tv_restaurant_name.setText(order.getRestaurant()+" >");
        tv_order_state.setText(order.getIs_payed());
        tv_dish_count_total_price.setText("共计 "+order.getDish_count()+" 件菜品 "+order.getTotal_price()+" 元");
        tv_order_time.setText(order.getTime());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderItemInfoActivity.class);
                intent.putExtra("order_id", order.getId());
                mContext.startActivity(intent);
            }
        });
    }
}
