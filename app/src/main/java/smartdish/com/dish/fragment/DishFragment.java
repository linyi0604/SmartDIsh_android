package smartdish.com.dish.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import smartdish.com.R;
import smartdish.com.base.BaseFragment;
import smartdish.com.dish.adapter.DishFragmentAdapter;
import smartdish.com.dish.bean.ResultBean;

/**
 * 菜品界面的fragment
 */

public class DishFragment extends BaseFragment {
    private TextView tv_search_home;    // 搜索文本框
    private ImageView ib_top;           // 回到顶部按钮
    private TextView tv_search;         // 搜索按键
    private RecyclerView rvHome;        // 主屏幕部分recyclerView
    private DishFragmentAdapter adapter;    // 数据适配器


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_dish,null);
        tv_search_home = (TextView)view.findViewById(R.id.tv_search_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        tv_search = (TextView) view.findViewById(R.id.tv_message_home);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);

        initListener();
        initData();

        return view;
    }

    private void initListener() {
        // 按钮置顶
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvHome.scrollToPosition(0);
            }
        });
        // 发起搜索
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String value = tv_search_home.getText().toString().trim();
            Toast.makeText(mContext,value,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void initData() {
        super.initData();

        String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
        OkHttpUtils.get().url( url + "/getDishPage" )
//                .addParams("username","aaa")
//                .addParams("password","bbb")
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
                    public void onResponse(String response, int id) {
//                        if(response == null || response.equals("") || id!=200){
//                            return;
//                        }
                        ResultBean resultBean = JSON.parseObject(response,ResultBean.class);
                        if(resultBean == null){
                            // 没有数据
                        }else{
                            // 有数据
                            adapter = new DishFragmentAdapter(mContext,resultBean);
                            rvHome.setAdapter(adapter);
                            GridLayoutManager manager = new GridLayoutManager(mContext, 1);
                            // 设置滑动大小的监听
                            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    if(position>3){
                                        // 显示按钮
                                        ib_top.setVisibility(ImageView.VISIBLE);
                                    }else{
                                        // 隐藏按钮
                                        ib_top.setVisibility(ImageView.INVISIBLE);
                                    }
                                    return 1;
                                }
                            });
                            rvHome.setLayoutManager(manager);
                        }
                    }
                });
    }
}
