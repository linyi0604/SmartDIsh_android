package smartdish.com.cart.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

import okhttp3.Call;
import smartdish.com.R;
import smartdish.com.base.activity.MainActivity;
import smartdish.com.base.bean.GoodBean;
import smartdish.com.base.utils.MyUtils;
import smartdish.com.cart.bean.CartBean;
import smartdish.com.cart.view.NumberAddSubView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private final CheckBox cbAll;   // 删除时候checkbox
    private CheckBox checkboxAll;   // 计算价格时候的checkbox
    private TextView tvShopcartTotal;
    private Context mContext;
    private List<GoodBean> dataList;


    public CartAdapter(Context mContext, List<GoodBean> list, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox cbAll) {
        this.mContext = mContext;
        this.cbAll = cbAll;
        this.dataList = list;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        showTotalPrice();
        // 设置点击事件
        setListener();
        // 设置全部选中
        checkAllSelected();
    }
    

    private void setListener(){
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 1 根据位置找到bean对象
                GoodBean data = dataList.get(position);
                // 2 设置取反状态

                data.setSelected(!data.isSelected());
                // 3 刷新状态
                notifyItemChanged(position);
                // 4 校验是否全选
                checkAllSelected();
                // 5 重新计算总价格
                showTotalPrice();
            }
        });
        // 设置全选 反选
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1 获得状态 改变全选按钮的选择状态
                boolean isChecked = checkboxAll.isChecked();
                // 2 根据状态设置全选和非全选
                checkAll_none(isChecked);
                // 3 计算总价格
                showTotalPrice();
            }
        });
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1 获得状态 改变全选按钮的选择状态
                boolean isChecked = cbAll.isChecked();
                // 2 根据状态设置全选和非全选
                checkAll_none(isChecked);
            }
        });
    }
    public void checkAll_none(boolean isCheck){
        if(dataList!= null && dataList.size()>0){
            for(int i=0;i<dataList.size();i++){
                GoodBean data = dataList.get(i);
                data.setSelected(isCheck);
                notifyItemChanged(i);
            }
        }
    }


    public void checkAllSelected() {
        int selectedNumber = 0;
        if(dataList != null && dataList.size()>0){
            for(GoodBean data : dataList){
                if(data.isSelected()){
                   // 选中了
                    selectedNumber++;
                }else{
                    //没选中
                    checkboxAll.setChecked(false);
                    cbAll.setChecked(false);
                }
                if(selectedNumber == dataList.size()){
                    checkboxAll.setChecked(true);
                    cbAll.setChecked(true);
                }
            }
        }else{
            checkboxAll.setChecked(true);
            cbAll.setChecked(true);
        }
    }

    public void showTotalPrice() {
        tvShopcartTotal.setText("合计："+getTotalPrice());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_shop_cart,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GoodBean data = dataList.get(position);
        holder.cb_gov.setChecked(data.isSelected());
        Glide.with(mContext).load(mContext.getString(R.string.base_url)+data.getImage_url()).into(holder.iv_gov);
        holder.tv_desc_gov.setText(data.getName());
        holder.tv_price_gov.setText("￥"+data.getPrice());
        holder.addSubView.setValue(Integer.valueOf(data.getCount()));
        holder.addSubView.setOnNumberChangeListener(new NumberAddSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(View v, int value) {
                // 将数量改变同步到网络 并且通过回调 改变页面数据
                update_count(data.getEvent_id(),String.valueOf(value));
            }

            @SuppressLint("HandlerLeak")
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if(msg.obj.equals("OK")){
                        // 更新数量改变的结果
                        data.setCount(String.valueOf(msg.arg1));
                        // 更新位置上的data
                        notifyItemChanged(position);
                        // 更新总价格
                        showTotalPrice();
                    }else{
                        Toast.makeText(mContext,"网络发生异常",Toast.LENGTH_SHORT).show();
                    }
                }
            };
            public void update_count(String id, final String value){
                // 发送请求去服务器要购物车里的数据
                String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
                OkHttpUtils.get().url(url+"/updateCartDishCount")
                        .addParams("cart_id",id)
                        .addParams("count",value)
                        .build()
                        .execute(new StringCallback() {
                            Message msg= new Message();
                            // 请求失败的时候
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
                                System.out.println(e);
                                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
                                msg.obj = "notOK";
                                handler.sendMessage(msg);
                            }
                            // 请求成功的时候
                            @Override
                            public void onResponse(final String response, int id) {
                                if(response.equals("OK") ){
                                    msg.obj = "OK";
                                    msg.arg1 = Integer.valueOf(value);
                                }else{
                                    msg.obj = "notOK";
                                }
                                handler.sendMessage(msg);
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        if(dataList != null && dataList.size()>0){
            for(int i=0;i<dataList.size();i++){
                GoodBean goodBean = dataList.get(i);
                if(goodBean.isSelected() == true) {
                    totalPrice += Double.valueOf(goodBean.getPrice())*Integer.valueOf(goodBean.getCount());
                }
            }
        }

        return totalPrice;
    }

    // 删除列表中的数据
    public void delteData() {
        if(dataList!= null && dataList.size()>0){
            // 删除选中的
            List<String> deleteId = new ArrayList<>();
            for(int i = 0;i<dataList.size();i++){
                GoodBean data = dataList.get(i);
                if(data.isSelected()){  // 如果勾选了移除
                    // 1 要删除的 购物车id  存入id列表， 索引存入index列表
                    deleteId.add(data.getEvent_id());
                    // 2 内存数据移除
                    dataList.remove(i);
                    // 3 刷新页面显示
                    notifyItemRemoved(i);
                    i--;
                }
            }
            // 向服务器发送请求 进行删除
            if(deleteId != null && deleteId.size()>0){
                // 1 发送请求
                String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
                OkHttpUtils.get().url(url+"/deleteCartInfo")
                        .addParams("id_list",String.valueOf(deleteId))
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
                                if(response.equals("OK")){
                                }else{
                                    Toast.makeText(mContext,"发生异常："+response,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private NumberAddSubView addSubView;
        public ViewHolder(View itemView){
            super(itemView);
            cb_gov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            iv_gov = (ImageView) itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = (TextView) itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = (TextView) itemView.findViewById(R.id.tv_price_gov);
            addSubView = (NumberAddSubView) itemView.findViewById(R.id.numberAddSubView);
            // 设置item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }

    }

    // 监听点击item 勾选
    public interface  OnItemClickListener{
        public void onItemClick(int position);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
