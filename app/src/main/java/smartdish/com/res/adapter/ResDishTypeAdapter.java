package smartdish.com.res.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import smartdish.com.R;
import smartdish.com.base.adapter.GoodItemAdapter;
import smartdish.com.res.bean.ResDishTypeListDishListBean;


public class ResDishTypeAdapter extends BaseAdapter {
    private List<ResDishTypeListDishListBean.ResDishTypeBean> typeList;
    private ListView lv_dish;
    private Context mContext;
    private TextView tv_empty_dish_list;

    public ResDishTypeAdapter(Context mContext,List<ResDishTypeListDishListBean.ResDishTypeBean> typeList, ListView lv_dish,TextView tv_empty_dish_list) {
        this.mContext = mContext;
        this.typeList = typeList;
        this.lv_dish = lv_dish;
        this.tv_empty_dish_list = tv_empty_dish_list;
    }

    @Override
    public int getCount() {
        return typeList.size();
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
        View view = null;
        if(convertView == null){
            view = View.inflate(mContext, R.layout.layout_res_dish_type,null);
        }else{
            view = convertView;
        }
        System.out.println(view);
        initData(view,typeList.get(position));
        return view;
    }
    public void initData(final View view, final ResDishTypeListDishListBean.ResDishTypeBean data){
        TextView tv_dish_type = view.findViewById(R.id.tv_dish_type);
        tv_dish_type.setText(data.getTypeName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            lv_dish.setAdapter(new ResDishTypeDishAdapter(mContext,data.getDishList(),tv_empty_dish_list));

            }
        });
    }
}
