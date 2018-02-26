package smartdish.com.favorite.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import smartdish.com.R;
import smartdish.com.base.activity.GoodsInfoActivity;
import smartdish.com.base.bean.GoodBean;
import smartdish.com.base.utils.MyUtils;
import smartdish.com.favorite.adapter.FavoriteAdapter;
import smartdish.com.favorite.bean.FavoriteBean;

public class FavoriteActivity extends Activity {
    private ImageButton ib_back;
    private ListView lv_favorite_list;
    private Context mContext;
    private FavoriteBean favoriteBean;
    private TextView tv_empty_private;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        mContext = FavoriteActivity.this;
        ib_back = findViewById(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv_favorite_list = findViewById(R.id.lv_favorite_list);
        tv_empty_private = findViewById(R.id.tv_empty_private);

        initData();
        initListener();

    }

    private void initListener() {
        lv_favorite_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                intent.putExtra("goodbean",favoriteBean.getList().get(position));
                mContext.startActivity(intent);
            }
        });

    }

    private void initData() {
        String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
        String username = MyUtils.getUsername(mContext);
        OkHttpUtils.get()
            .url(url+"/getFavorite")
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
                    try{
                        favoriteBean = JSON.parseObject(response,FavoriteBean.class);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if(favoriteBean != null && favoriteBean.getList().size()>0){
                        tv_empty_private.setVisibility(View.GONE);
                        lv_favorite_list.setVisibility(View.VISIBLE);
                        showData(favoriteBean.getList());
                    }
                }
            });

    }

    private void showData(List<GoodBean> list) {
        lv_favorite_list.setAdapter(new FavoriteAdapter(mContext,list));

    }

}
