package smartdish.com.favorite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import smartdish.com.R;
import smartdish.com.base.bean.GoodBean;


public class FavoriteAdapter extends BaseAdapter {
    private Context mContext;
    private List<GoodBean> list;
    public FavoriteAdapter(Context mContext, List<GoodBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null){
            view = View.inflate(mContext, R.layout.item_favorite_listview,null);
        }else{
            view = convertView;
        }
        GoodBean data = list.get(position);
        showData(data,view);
        return view;
    }

    private void showData(GoodBean data, View view) {
        ImageView iv_image = (ImageView) view.findViewById(R.id.iv_image);
        TextView tv_name = (TextView)view.findViewById(R.id.tv_name);
        TextView tv_restaurant = (TextView) view.findViewById(R.id.tv_restaurant);
        TextView tv_detail = (TextView) view.findViewById(R.id.tv_detail);

        Glide.with(mContext).load(mContext.getString(R.string.base_url)+data.getImage_url()).into(iv_image);
        tv_name.setText(data.getName());
        tv_restaurant.setText(data.getRestaurant());
        tv_detail.setText(data.getDetail());

    }


}
