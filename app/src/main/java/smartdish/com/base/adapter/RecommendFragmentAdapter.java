package smartdish.com.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.util.List;

import smartdish.com.R;
import smartdish.com.base.activity.GoodsInfoActivity;
import smartdish.com.base.bean.GoodBean;
import smartdish.com.dish.adapter.DishFragmentAdapter;
import smartdish.com.dish.adapter.RecommendGridViewAdapter;
import smartdish.com.dish.bean.ResultBean;

import static smartdish.com.dish.adapter.DishFragmentAdapter.GOODBEAN;

/**
 * Created by Lenovo on 2018/4/6.
 */

public class RecommendFragmentAdapter extends RecyclerView.Adapter  {
    private Context mContext;
    private final LayoutInflater mLayoutInflater;   // 初始化布局
    private final ResultBean resultBean;   // bean结果

    public RecommendFragmentAdapter(Context mContext, ResultBean resultBean) {
        super();
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.resultBean = resultBean;
    }

    // 相当于getView 创建ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(mContext,mLayoutInflater.inflate(R.layout.recommend_item,null));
    }

    // 相当于 绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
        List<ResultBean.RecommendInfoBean> recommend_info = resultBean.getRecommend_info();
        if(recommend_info!=null){
            recommendViewHolder.setData(recommend_info);
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    // 推荐部分
    class RecommendViewHolder extends RecyclerView.ViewHolder{
        private final Context mContext;
        private final TextView tv_more_recommend;
        private final GridView gv_recommend;
        private RecommendGridViewAdapter adapter;
        public RecommendViewHolder(final Context mContent, View itemView) {
            super(itemView);
            this.mContext = mContent;
            this.tv_more_recommend = (TextView) itemView.findViewById(R.id.tv_more_recommend);
            this.gv_recommend = (GridView) itemView.findViewById(R.id.gv_recommend);
        }
        public void setData(final List<ResultBean.RecommendInfoBean> list) {
            adapter = new RecommendGridViewAdapter(mContext,list);
            gv_recommend.setAdapter(adapter);
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    ResultBean.RecommendInfoBean data = list.get(i);
                    String json = JSON.toJSONString(data);
                    startDishInfoActivity(json);
                }
            });
        }
        // 启动菜品详情页面
        public void startDishInfoActivity(String data){
            final GoodBean goodBean = JSON.parseObject(data,GoodBean.class);
            String dish_id = goodBean.getId();
            Intent intent = new Intent(mContext, GoodsInfoActivity.class);
            intent.putExtra(GOODBEAN,goodBean);
            mContext.startActivity(intent);

        }
    }
}
