package smartdish.com.user.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.net.InterfaceAddress;
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
import smartdish.com.cart.activity.CartActivity;
import smartdish.com.cart.adapter.CartAdapter;
import smartdish.com.cart.bean.CartBean;
import smartdish.com.cart.view.NumberAddSubView;
import smartdish.com.favorite.activity.FavoriteActivity;
import smartdish.com.user.activity.UserInfoActivity;

/**
 * 菜品界面的fragment
 */

public class UserFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private RelativeLayout rl_personal_information; // 个人信息
    private RelativeLayout rl_favorite; // 收藏夹
    private RelativeLayout rl_order;    // 订单
    private RelativeLayout rl_cart;    // 订单
    private RelativeLayout rl_logout;  // 退出登录
    private RelativeLayout rl_login;  // 登录

    private void findViews(View view){
        rl_personal_information = (RelativeLayout) view.findViewById(R.id.rl_personal_information);
        rl_favorite = (RelativeLayout) view.findViewById(R.id.rl_favorite);
        rl_order = (RelativeLayout) view.findViewById(R.id.rl_order);
        rl_logout = (RelativeLayout) view.findViewById(R.id.rl_logout);
        rl_login = (RelativeLayout) view.findViewById(R.id.rl_login);
        rl_cart = (RelativeLayout) view.findViewById(R.id.rl_cart);

        // 检查是否已经登录
        if(MyUtils.isLogin(mContext)){
            rl_logout.setVisibility(View.VISIBLE);
            rl_login.setVisibility(View.GONE);
        }else{
            rl_logout.setVisibility(View.GONE);
            rl_login.setVisibility(View.VISIBLE);
        }


        rl_personal_information.setOnClickListener(this);
        rl_favorite.setOnClickListener(this);
        rl_order.setOnClickListener(this);
        rl_logout.setOnClickListener(this);
        rl_login.setOnClickListener(this);
        rl_cart.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        findViews(view);
    }

    @Override
    public void onClick(View v) {
        if(v == rl_personal_information){   // 个人信息
            if(MyUtils.isLogin(mContext)){
                Intent intent = new Intent(mContext,UserInfoActivity.class);
                startActivity(intent);
            }else{
                MyUtils.toLoginPage(mContext);
            }
        } else if( v == rl_favorite){    // 收藏
            if(MyUtils.isLogin(mContext)){
                Intent intent = new Intent(mContext,FavoriteActivity.class);
                startActivity(intent);
            }else{
                MyUtils.toLoginPage(mContext);
            }
        } else if(v==rl_cart){   // 购物车
            if(MyUtils.isLogin(mContext)){
                Intent intent = new Intent(mContext, CartActivity.class);
                startActivity(intent);
            }else{
                MyUtils.toLoginPage(mContext);
            }
        } else if( v == rl_order){    // 订单
            if(MyUtils.isLogin(mContext)){

            }else{
                MyUtils.toLoginPage(mContext);
            }
        } else if( v == rl_logout){  // 退出登录
            Boolean result = MyUtils.logout(mContext);
            if(result){
                rl_logout.setVisibility(View.GONE);
                rl_login.setVisibility(View.VISIBLE);
            }else{
                Toast.makeText(mContext,"退出登录发生错误",Toast.LENGTH_SHORT).show();
            }
        } else if( v == rl_login){  // 登录
            MyUtils.toLoginPage(mContext);
        }
    }

    @Override
    public View initView() {
        this.view = View.inflate(mContext, R.layout.fragment_user , null);

        findViews(view);
        initListener();

        return view;
    }

    private void initListener() {
        // 设置默认的编辑状态

    }




    @Override
    public void initData() {
        super.initData();

    }
    public void showData(List<GoodBean> list){

    }




}
