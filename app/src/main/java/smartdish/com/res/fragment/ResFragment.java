package smartdish.com.res.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.List;

import okhttp3.Call;
import smartdish.com.R;
import smartdish.com.base.BaseFragment;
import smartdish.com.res.activity.ResMainPageActivity;
import smartdish.com.res.adapter.ResAdapter;
import smartdish.com.res.bean.ResBean;
import smartdish.com.res.bean.ResSearchBean;

/**
 * 菜品界面的fragment
 */

public class ResFragment extends BaseFragment implements View.OnClickListener{
    private TextView tv_empty_search;
    private TextView tv_message_home;
    private TextView tv_search_home;
    private String keyWord = "";
    private String sort = "create_time";
    private TextView tv_default;
    private TextView tv_sell;
    private TextView tv_grade;
    private ResSearchBean resSearchBean;
    private ListView lv_search_item;
    private ResAdapter adapter;

    public void findViews(View view ){
        tv_empty_search = (TextView)view.findViewById(R.id.tv_empty_search);
        tv_empty_search.setText("没有搜索到相关餐厅哦~");
        tv_empty_search.setVisibility(View.VISIBLE);

        lv_search_item = (ListView) view.findViewById(R.id.lv_search_item);
        tv_message_home =(TextView) view.findViewById(R.id.tv_message_home);

        tv_search_home = (TextView)view.findViewById(R.id.tv_search_home);
        tv_default = (TextView)view.findViewById(R.id.tv_default_res);
        tv_sell = (TextView)view.findViewById(R.id.tv_sell_res);
        tv_grade = (TextView)view.findViewById(R.id.tv_grade_res);

        tv_message_home.setOnClickListener(this);
        tv_default.setOnClickListener(this);
        tv_sell.setOnClickListener(this);
        tv_grade.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v == tv_message_home){   // 点击了搜索按钮
            keyWord = tv_search_home.getText().toString().trim();
            initData();
        }else if(v == tv_default){
            sort = "create_time";
            initData();
        }else if(v == tv_sell){
            sort = "sellCount";
            initData();
        }else if(v == tv_grade){
            sort = "grade";
            initData();
        }

    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_res,null);
        findViews(view);
        initData();
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
        OkHttpUtils.get()
                .url(url+"/searchRes")
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
                            resSearchBean = JSON.parseObject(response,ResSearchBean.class);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        if(resSearchBean != null && resSearchBean.getList().size()>0){
                            tv_empty_search.setVisibility(View.GONE);
                            lv_search_item.setVisibility(View.VISIBLE);
                            showData(resSearchBean.getList());
                        }else{
                            tv_empty_search.setVisibility(View.VISIBLE);
                            lv_search_item.setVisibility(View.GONE);
                        }
                    }
                });
    }
    public void showData(final List<ResBean> list){
        // 设置adapter   设置显示数据
        adapter = new ResAdapter(mContext,list);
        lv_search_item.setAdapter(adapter);
        lv_search_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext,ResMainPageActivity.class);
                intent.putExtra("res_id", list.get(position).getId());
                startActivity(intent);
            }
        });
    }


}
