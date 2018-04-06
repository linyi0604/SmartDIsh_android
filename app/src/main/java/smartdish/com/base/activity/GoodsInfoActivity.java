package smartdish.com.base.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import smartdish.com.R;
import smartdish.com.base.adapter.RecommendFragmentAdapter;
import smartdish.com.base.bean.GoodBean;
import smartdish.com.base.utils.MyUtils;
import smartdish.com.cart.activity.CartActivity;
import smartdish.com.cart.fragment.CartFragment;
import smartdish.com.dish.adapter.DishFragmentAdapter;
import smartdish.com.dish.bean.ResultBean;
import smartdish.com.res.activity.ResMainPageActivity;

public class GoodsInfoActivity extends Activity implements View.OnClickListener {
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private TextView tv_more_share;
    private TextView tv_more_search;
    private TextView tv_more_home;
    private Button btn_more;
    private LinearLayout ll_root;
    private GoodBean goodBean;
    private TextView tv_good_info_feature;
    private RecyclerView wb_detail;
    private TextView tv_good_info_restaurant;
    private Context mContext;
    private RecommendFragmentAdapter adapter;    // 数据适配器

    private GoodBean currentDish;

    private void findViews() {
        mContext = GoodsInfoActivity.this;
        tv_good_info_restaurant = (TextView)findViewById(R.id.tv_good_info_restaurant);
        tv_more_search = (TextView) findViewById(R.id.tv_more_search);
        tv_more_home = (TextView) findViewById(R.id.tv_more_home);
        tv_more_share = (TextView) findViewById(R.id.tv_more_share);
        btn_more      = (Button) findViewById(R.id.btn_more);
        ll_root = (LinearLayout) findViewById(R.id.ll_root);
        tv_good_info_feature = (TextView)findViewById(R.id.tv_good_info_feature);
        wb_detail = (RecyclerView) findViewById(R.id.wb_detail);


        ibGoodInfoBack = (ImageButton)findViewById( R.id.ib_good_info_back );
        ibGoodInfoMore = (ImageButton)findViewById( R.id.ib_good_info_more );
        ivGoodInfoImage = (ImageView)findViewById( R.id.iv_good_info_image );
        tvGoodInfoName = (TextView)findViewById( R.id.tv_good_info_name );
        tvGoodInfoDesc = (TextView)findViewById( R.id.tv_good_info_desc );
        tvGoodInfoPrice = (TextView)findViewById( R.id.tv_good_info_price );
        llGoodsRoot = (LinearLayout)findViewById( R.id.ll_goods_root );
        tvGoodInfoCallcenter = (TextView)findViewById( R.id.tv_good_info_callcenter );
        tvGoodInfoCollection = (TextView)findViewById( R.id.tv_good_info_collection );
        tvGoodInfoCart = (TextView)findViewById( R.id.tv_good_info_cart );
        btnGoodInfoAddcart = (Button)findViewById( R.id.btn_good_info_addcart );

        Drawable drawable=this.getResources().getDrawable(R.drawable.good_uncollected);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvGoodInfoCollection.setCompoundDrawables(null,drawable,null,null);

        ibGoodInfoBack.setOnClickListener( this );
        ibGoodInfoMore.setOnClickListener( this );
        btnGoodInfoAddcart.setOnClickListener( this );

        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);

        tv_more_home.setOnClickListener(this);
        tv_more_share.setOnClickListener(this);
        tv_more_search.setOnClickListener(this);
        ll_root.setOnClickListener(this);
        btn_more.setOnClickListener(this);

    }


    @Override
    protected void onResume() {
        super.onResume();
        setDataForView(currentDish);
    }

    @Override
    public void onClick(View v) {
        if ( v == ibGoodInfoBack ) {    // 返回按钮
            finish();
        } else if ( v == ibGoodInfoMore ) { //点击了更多 三个...
            ll_root.setVisibility(View.VISIBLE);
        } else if ( v == btnGoodInfoAddcart ) {     // 添加购物车
            if(MyUtils.isLogin(mContext)){   // 检查是否登录过
                String username = MyUtils.getUsername(mContext);
                String dish_id = currentDish.getId();
                String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
                OkHttpUtils.get().url(url+"/addCart")
                        .addParams("username",username)
                        .addParams("dish_id",dish_id)
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
                                if("OK".equals(response)){
                                    //添加购物车成功
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(mContext, "添加购物车成功", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }else if("Unique".equals(response)){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(mContext, "购物车中已经有其他餐厅的菜了，可以先收藏~下次再点餐哦~", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    return;
                                }else{
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(mContext,"发生了未知错误:"+response,Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    return;
                                }
                            }
                        });
            }else{  // 尚未登录 跳转到登录界面
                MyUtils.toLoginPage(mContext);
            }

        } else if(v == tvGoodInfoCallcenter){       // 看这家店
            Intent intent = new Intent(mContext, ResMainPageActivity.class);
            intent.putExtra("res_id",goodBean.getRestaurant_id());
            startActivity(intent);
        } else if(v == tvGoodInfoCollection){       // 收藏 或 取消 收藏
            if(MyUtils.isLogin(mContext)){   // 检查是否登录过
                // 已经登陆过 添加到收藏夹当中
                String username = MyUtils.getUsername(mContext);
                String dish_id = currentDish.getId();
                String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
                OkHttpUtils.get().url(url+"/addFavorite")
                        .addParams("username",username)
                        .addParams("dish_id",dish_id)
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
                                if("OK".equals(response)){
                                    //添加购物车成功
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(mContext, "收藏成功！", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    Drawable drawable=mContext.getResources().getDrawable(R.drawable.good_collected);
                                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                    tvGoodInfoCollection.setCompoundDrawables(null,drawable,null,null);
                                }else{
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(mContext,"提示:"+response,Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    return;
                                }
                            }
                        });




            }else{  // 尚未登录 跳转到登录界面
                MyUtils.toLoginPage(mContext);
            }
        }else if(v == tvGoodInfoCart){      // 查看购物车
            if(MyUtils.isLogin(mContext)){   // 检查是否登录过
                Intent intent = new Intent(mContext,CartActivity.class);
                startActivity(intent);
            }else{  // 尚未登录 跳转到登录界面
                MyUtils.toLoginPage(mContext);
            }
        }else if(v == tv_more_home){    // 返回首页
            Toast.makeText(this,"首页",Toast.LENGTH_SHORT).show();
        }else if(v == tv_more_share){       // 分享
            Toast.makeText(this,"分享",Toast.LENGTH_SHORT).show();
        }else if(v == tv_more_search){      //搜索
            Toast.makeText(this,"搜索",Toast.LENGTH_SHORT).show();
        }else if(v == btn_more){    // 点击查看更多
            ll_root.setVisibility(View.GONE);
        }
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();
        // 接受数据
        goodBean = (GoodBean)getIntent().getSerializableExtra(DishFragmentAdapter.GOODBEAN);
        if(goodBean == null){
            Toast.makeText(this,"数据传递出错",Toast.LENGTH_SHORT).show();
        }else{
            // 向页面 设置数据
            currentDish = goodBean;
            setDataForView(goodBean);
        }

        addStep(goodBean.getId());
    }
    public void addStep(String dish_id){
        String url = mContext.getString(R.string.base_url) + mContext.getString(R.string.appUrl);
        if( MyUtils.getUsername(mContext).equals("")){
            MyUtils.setUsername(mContext);
        }
        String username = MyUtils.getUsername(mContext);

        OkHttpUtils.get().url(url + "/addStep")
                .addParams("dish_id",dish_id)
                .addParams("username",username)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {}
                    @Override
                    public void onResponse(String response, int id) {
                        // 内嵌推荐模块
                        ResultBean resultBean = JSON.parseObject(response,ResultBean.class);
                        if(resultBean == null){
                            // 没有数据
                        }else{
                            adapter = new RecommendFragmentAdapter(mContext,resultBean);
                            GridLayoutManager manager = new GridLayoutManager(mContext, 1);
                            wb_detail.setAdapter(adapter);
                            wb_detail.setLayoutManager(manager);
                        }
                    }
                });
    }
    // 设置页面数据
    private void setDataForView(GoodBean goodBean) {
        // 检查改商品是不是收藏过了
        checkFavorite();

        // 设置图片
        Glide.with(this).load(this.getString(R.string.base_url)+goodBean.getImage_url()).into(ivGoodInfoImage);
        // 设置名称
        tvGoodInfoName.setText("名称："+goodBean.getName());
        tv_good_info_restaurant.setText("餐厅："+goodBean.getRestaurant());
        tvGoodInfoPrice.setText("价格： ￥"+goodBean.getPrice());
        tvGoodInfoDesc.setText("介绍："+goodBean.getDetail());
        tv_good_info_feature.setText("特点："+goodBean.getFeatures());


    }


    // 检查改商品是不是已经被收藏过了
    private void checkFavorite() {
        if(!MyUtils.isLogin(mContext)){
            return;
        }
        // 检查改商品是不是已经被收藏过了
        String username = MyUtils.getUsername(mContext);
        String dish_id = currentDish.getId();
        String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
        OkHttpUtils.get().url(url+"/checkFavorite")
                .addParams("username",username)
                .addParams("dish_id",dish_id)
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
                        if(response.equals("true")){
                            Drawable drawable=mContext.getResources().getDrawable(R.drawable.good_collected);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            tvGoodInfoCollection.setCompoundDrawables(null,drawable,null,null);
                        }
                    }
                });
    }

}
