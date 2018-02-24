package smartdish.com.cart.fragment;

import android.annotation.SuppressLint;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import okhttp3.Call;
import smartdish.com.R;
import smartdish.com.base.BaseFragment;
import smartdish.com.base.bean.GoodBean;
import smartdish.com.base.utils.MyUtils;
import smartdish.com.cart.adapter.CartAdapter;
import smartdish.com.cart.bean.CartBean;
import smartdish.com.cart.view.NumberAddSubView;

/**
 * 菜品界面的fragment
 */

public class CartFragment extends BaseFragment implements View.OnClickListener {
    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;
    private LinearLayout ll_empty_shopcart;
    private ImageButton ib_shopcart_back;

    private CartAdapter adapter;

    //编辑状态
    private static final int ACTION_EDIT = 1;
    // 完成状态
    private static final int ACTION_COMPLETE = 2;



    private void findViews(View view) {
        ib_shopcart_back = (ImageButton) view.findViewById(R.id.ib_shopcart_back);
        tvShopcartEdit = (TextView) view.findViewById(R.id.tv_shopcart_edit);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) view.findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) view.findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) view.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) view.findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) view.findViewById(R.id.ll_delete);
        cbAll = (CheckBox) view.findViewById(R.id.cb_all);
        btnDelete = (Button) view.findViewById(R.id.btn_delete);
        btnCollection = (Button) view.findViewById(R.id.btn_collection);
        ivEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = (TextView) view.findViewById(R.id.tv_empty_cart_tobuy);
        ll_empty_shopcart = (LinearLayout) view.findViewById(R.id.ll_empty_shopcart);
        tvEmptyCartTobuy.setClickable(true);

        btnDelete.setOnClickListener(this);
        ib_shopcart_back.setOnClickListener(this);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.activity_shopping_cart , null);
        findViews(view);

        initListener();

        return view;
    }

    private void initListener() {
        // 设置默认的编辑状态
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action = (int)v.getTag();
                if(action == ACTION_EDIT){
                    // 切换为完成状态
                    showDelete();
                }else{
                    // 切换成编辑状态
                    hideDelete();
                }
            }
        });
    }


    private void showDelete(){
        // 变换状态为完成状态 编程非勾选 删除视图显示  结算视图隐藏
        tvShopcartEdit.setTag(ACTION_COMPLETE);
        tvShopcartEdit.setText("完成");
        // 项目变成非勾选
        cbAll.setChecked(false);
        if(adapter != null){
            adapter.checkAll_none(false);
        }
        // 显示删除的布局
        llDelete.setVisibility(View.VISIBLE);
        // 隐藏结算布局
        llCheckAll.setVisibility(View.GONE);
    }
    private void hideDelete(){
        // 变换状态为完成状态 编程非勾选 删除视图显示  结算视图隐藏
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setText("编辑");
        // 项目变成勾选
        if(adapter != null){
            adapter.checkAll_none(true);
            adapter.checkAllSelected();
            adapter.showTotalPrice();
        }
        // 隐藏删除的布局
        llDelete.setVisibility(View.GONE);
        // 显示结算布局
        llCheckAll.setVisibility(View.VISIBLE);
    }



    @Override
    public void initData() {
        super.initData();
        // 发送请求去服务器要购物车里的数据
        String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
        String username = MyUtils.getUsername(mContext);
        OkHttpUtils.get().url(url+"/getCart")
                .addParams("username",username)
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
                        CartBean cartBean = null;
                        try {
                            cartBean = JSON.parseObject(response, CartBean.class);
                        }catch (Exception e){
                            cartBean = null;
                        }
                        if(cartBean == null){   // 没有数据
                            showData(null);
                        }else{  // 有数据
                            showData(cartBean.getList());
                        }
                    }
                });
    }
    public void showData(List<GoodBean> list){
        // 有数据
        if(list != null && list.size()>0){
            llDelete.setVisibility(View.GONE);
            tvShopcartEdit.setVisibility(View.VISIBLE);
            // 隐藏空页面
            ll_empty_shopcart.setVisibility(View.GONE);
            // 有数据 写适配器 展示数据
            adapter = new CartAdapter(mContext,list,tvShopcartTotal,checkboxAll,cbAll);
            recyclerview.setAdapter(adapter);
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayout.VERTICAL,false));
        }else{
            // 没有数据 展示空页面
            emptyShoppingCart();
        }
    }
    private void emptyShoppingCart(){
        ll_empty_shopcart.setVisibility(View.VISIBLE);
        tvShopcartEdit.setVisibility(View.GONE);
        llDelete.setVisibility(View.GONE);

    }


    @Override
    public void onClick(View v) {
        // 点击删除按钮
        if(v == btnDelete){
            // 删除选中
            adapter.delteData();
            // 校验状态
            adapter.checkAllSelected();
            // 如果没有数据了 显示空数据的布局
            if(adapter.getItemCount()==0){
                emptyShoppingCart();
            }
        }else if(v == ib_shopcart_back){    // 点击了后退按钮
            if(back != null){
                back.clickBack();
            }
        }
    }
    public interface ClickBack{
        public void clickBack();
    }
    public ClickBack back;
    public void setBack(ClickBack back){
        this.back = back;
    }

}
