package smartdish.com.dish.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import smartdish.com.R;
import smartdish.com.base.activity.GoodsInfoActivity;
import smartdish.com.base.adapter.GoodItemAdapter;
import smartdish.com.base.bean.GoodBean;
import smartdish.com.dish.bean.DishSearchBean;

public class SearchActivity extends Activity implements ViewStub.OnClickListener {
    private String keyWord;
    private String sort = "create_time";
    private TextView tv_search_home;
    private TextView tv_message_home;
    private Context mContext;
    private TextView tv_default;
    private TextView tv_price;
    private TextView tv_grade;
    private TextView tv_sell;
    private TextView tv_empty_search;
    private ListView lv_search_item;
    private DishSearchBean dishSearchBean;
    private GoodItemAdapter adapter;

    public void findViews(){
        mContext = SearchActivity.this;
        Intent intent = getIntent();
        keyWord= intent.getStringExtra("key_word");
        tv_search_home = (TextView) findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) findViewById(R.id.tv_message_home);
        tv_default = (TextView) findViewById(R.id.tv_default);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_grade = (TextView) findViewById(R.id.tv_grade);
        tv_empty_search = (TextView) findViewById(R.id.tv_empty_search);
        tv_sell = (TextView) findViewById(R.id.tv_sell);
        lv_search_item = (ListView) findViewById(R.id.lv_search_item);

        tv_search_home.setText(this.keyWord);

        tv_message_home.setOnClickListener(this);   // 搜索按钮
        tv_default.setOnClickListener(this);   // 默认排序
        tv_price.setOnClickListener(this);   // 价格排序
        tv_grade.setOnClickListener(this);   // 评分排序
        tv_sell.setOnClickListener(this);   // 销量排序

    }

    @Override
    public void onClick(View v) {
        if(v == tv_message_home){
            keyWord = tv_search_home.getText().toString().trim();
            initData();
        }else if(v==tv_default){
            sort = "-create_time";   // 默认按照创建时间降序
            initData();
        }else if(v==tv_price){  // 按照价格升序
            sort = "dishPrice";
            initData();
        }else if(v==tv_grade){  // 按照评分降序
            sort = "-dishGrade";
            initData();
        }else if(v==tv_sell){   // 按照销量降序
            sort = "-dishSellCount";
            initData();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findViews();
        initData();
    }

    public void initData(){
        String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
        OkHttpUtils.get()
                .url(url+"/searchDish")
                .addParams("key_word",keyWord)
                .addParams("sort",sort)
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
                        try{
                            dishSearchBean = JSON.parseObject(response,DishSearchBean.class);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        if(dishSearchBean != null && dishSearchBean.getList().size()>0){
                            tv_empty_search.setVisibility(View.GONE);
                            lv_search_item.setVisibility(View.VISIBLE);
                            showData(dishSearchBean.getList());
                        }
                    }
                });
    }
    public void showData(List<GoodBean> list){
        this.adapter = new GoodItemAdapter(mContext,list);
        lv_search_item.setAdapter(adapter);
        lv_search_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 跳转到商品详情页面
                Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                intent.putExtra("goodbean", dishSearchBean.getList().get(position));
                mContext.startActivity(intent);
            }
        });
    }

}
