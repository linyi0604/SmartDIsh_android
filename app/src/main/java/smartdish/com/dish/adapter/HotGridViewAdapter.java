package smartdish.com.dish.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import smartdish.com.R;
import smartdish.com.dish.bean.ResultBean;

public class HotGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBean.HotInfoBean> list;
    public HotGridViewAdapter(Context mContext, List<ResultBean.HotInfoBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_hot_grid_view,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_hot = (ImageView)convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView)convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ResultBean.HotInfoBean data = list.get(i);
        Glide.with(mContext).load(mContext.getString(R.string.base_url)+data.getImage_url()).into(viewHolder.iv_hot);
        viewHolder.tv_price.setText(data.getPrice());
        viewHolder.tv_name.setText(data.getName());

        return convertView;
    }
    static class ViewHolder{
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
