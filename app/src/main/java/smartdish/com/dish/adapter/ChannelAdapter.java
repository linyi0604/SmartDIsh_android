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

/**
 * Created by Lenovo on 2018/2/16.
 */

public class ChannelAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ResultBean.ChannelInfoBean> list;

    public ChannelAdapter(Context mContext, List<ResultBean.ChannelInfoBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_channel,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_channel);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_channel);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        ResultBean.ChannelInfoBean li = list.get(position);
        Glide.with(mContext).load(mContext.getString(R.string.base_url)+li.getImage_url()).into(viewHolder.iv_icon);
        viewHolder.tv_title.setText(li.getName());

        return convertView;
    }
    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
    }
}
