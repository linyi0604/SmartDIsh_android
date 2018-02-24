package smartdish.com.base.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import smartdish.com.R;
import smartdish.com.base.bean.GoodBean;
import smartdish.com.base.utils.MyUtils;
import smartdish.com.cart.activity.CartActivity;
import smartdish.com.cart.fragment.CartFragment;
import smartdish.com.dish.adapter.DishFragmentAdapter;

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
    private WebView wb_detail;
    private TextView tv_good_info_restaurant;
    private Context mContext;

    private GoodBean currentDish;
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-02-18 14:45:44 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        mContext = GoodsInfoActivity.this;
        tv_good_info_restaurant = (TextView)findViewById(R.id.tv_good_info_restaurant);
        tv_more_search = (TextView) findViewById(R.id.tv_more_search);
        tv_more_home = (TextView) findViewById(R.id.tv_more_home);
        tv_more_share = (TextView) findViewById(R.id.tv_more_share);
        btn_more      = (Button) findViewById(R.id.btn_more);
        ll_root = (LinearLayout) findViewById(R.id.ll_root);
        tv_good_info_feature = (TextView)findViewById(R.id.tv_good_info_feature);
        wb_detail = (WebView) findViewById(R.id.wb_detail);


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

        } else if(v == tvGoodInfoCallcenter){       // 联系客服
            if(MyUtils.isLogin(mContext)){   // 检查是否登录过
                Toast.makeText(this,"添加购物车",Toast.LENGTH_SHORT).show();
            }else{  // 尚未登录 跳转到登录界面
                MyUtils.toLoginPage(mContext);
            }
        } else if(v == tvGoodInfoCollection){       // 收藏
            if(MyUtils.isLogin(mContext)){   // 检查是否登录过
                Toast.makeText(this,"添加购物车",Toast.LENGTH_SHORT).show();
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
    }
    // 设置页面数据
    private void setDataForView(GoodBean goodBean) {
        // 设置图片
        Glide.with(this).load(this.getString(R.string.base_url)+goodBean.getImage_url()).into(ivGoodInfoImage);
        // 设置名称
        tvGoodInfoName.setText("名称："+goodBean.getName());
        tv_good_info_restaurant.setText("餐厅："+goodBean.getRestaurant());
        tvGoodInfoPrice.setText("价格： ￥"+goodBean.getPrice());
        tvGoodInfoDesc.setText("介绍："+goodBean.getDetail());
        tv_good_info_feature.setText("特点："+goodBean.getFeatures());

        // 设置内嵌浏览器控件
        // 拿到设置对象 设置支持双击放大 支持 js  支持先缓存后网页
        WebSettings settings = wb_detail.getSettings();
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wb_detail.loadUrl(this.getString(R.string.base_url));
        // 设置不适用系统浏览器 而在页面加载
        wb_detail.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }
        });

    }


}
