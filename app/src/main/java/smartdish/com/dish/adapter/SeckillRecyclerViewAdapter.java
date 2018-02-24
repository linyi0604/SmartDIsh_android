package smartdish.com.dish.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import smartdish.com.R;
import smartdish.com.dish.bean.ResultBean;

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ResultBean.SecKillInfoBean.ListBean> list;
    private OnSeckillRecyclerView onSeckillRecyclerView;

    public SeckillRecyclerViewAdapter(Context mContext,List<ResultBean.SecKillInfoBean.ListBean> list){
        this.mContext = mContext;
        this.list = list;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_seckill,null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ResultBean.SecKillInfoBean.ListBean secKillInfo = list.get(position);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        // 绑定数据
        Glide.with(mContext).load(mContext.getString(R.string.base_url)+secKillInfo.getImage_url()).into(viewHolder.iv_figure);
        viewHolder.tv_cover_price.setText(secKillInfo.getPrice());
        viewHolder.tv_origin_price.setText(secKillInfo.getPrice_origin());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_figure = (ImageView) itemView.findViewById(R.id.iv_figure);
            tv_cover_price = (TextView) itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = (TextView) itemView.findViewById(R.id.tv_origin_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(mContext,"特价秒杀暂未开放",Toast.LENGTH_SHORT).show();
                    if(onSeckillRecyclerView != null){
                        onSeckillRecyclerView.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }
    public interface OnSeckillRecyclerView{
        public void onItemClick(int position);
    }
    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView){
        this.onSeckillRecyclerView = onSeckillRecyclerView;
    }

}
