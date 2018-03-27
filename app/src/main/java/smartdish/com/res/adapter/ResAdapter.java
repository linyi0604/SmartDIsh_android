package smartdish.com.res.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import smartdish.com.R;
import smartdish.com.res.bean.ResBean;

/**
 * Created by Lenovo on 2018/3/2.
 */

public class ResAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResBean> list;

    public ResAdapter(Context mContext, List<ResBean> list) {
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
        if(convertView != null){
            view = convertView;
        }else{
            view = View.inflate(mContext, R.layout.item_res_listview,null);
        }
        showData(view,list.get(position));
        return view;
    }

    public void showData(View view,ResBean data){
        ImageView iv_image = view.findViewById(R.id.iv_image);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_detail = view.findViewById(R.id.tv_detail);
        TextView tv_grade = view.findViewById(R.id.tv_grade);
        TextView tv_sell_count = view.findViewById(R.id.tv_sell_count);
        TextView tv_address = view.findViewById(R.id.tv_address);

        Glide.with(mContext).load(mContext.getString(R.string.base_url)+data.getImage_url()).into(iv_image);
        tv_name.setText(data.getName());
        tv_detail.setText(data.getDetail());
        tv_grade.setText(data.getGrade()+"分");
        tv_sell_count.setText("月销："+data.getSellCount());
        tv_address.setText("地址："+data.getAddress());
    }
}
