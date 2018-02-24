package smartdish.com.dish.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import smartdish.com.R;
import smartdish.com.base.activity.GoodsInfoActivity;
import smartdish.com.base.bean.GoodBean;
import smartdish.com.dish.bean.ResultBean;

public class DishFragmentAdapter extends RecyclerView.Adapter {

    public static final int BANNER = 0; // 广告条幅
    public static final int CHANNEL = 1; //频道
    public static final int ACT = 2; //活动
    public static final int SECKILL = 3; //秒杀
    public static final int RECOMMEND = 4; //推荐
    public static final int HOT = 5; //热卖
    public static final String GOODBEAN = "goodbean";
    private final Context mContent; // 上下文····
    private final ResultBean resultBean;   // bean结果
    private final LayoutInflater mLayoutInflater;   // 初始化布局

    private int currentType = 0;    // 当前类型

    public DishFragmentAdapter(Context mContext, ResultBean resultBean) {
        this.mContent = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    // 相当于getView 创建ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == BANNER){ // 广告轮播图
            return new BannerViewHolder(mContent, mLayoutInflater.inflate(R.layout.banner_viewpager,null));
        }else if(viewType == CHANNEL){  // 入口通道
            return new ChannelViewHolder(mContent,mLayoutInflater.inflate(R.layout.channel_item,null));
        }else if(viewType == ACT){  // 活动 横幅
            return new ActViewHolder(mContent,mLayoutInflater.inflate(R.layout.act_item,null));
        }else if(viewType == SECKILL){  // 秒杀
            return new SecKillViewHolder(mContent,mLayoutInflater.inflate(R.layout.seckill_item,null));
        }else if(viewType == RECOMMEND){  // 秒杀
            return new RecommendViewHolder(mContent,mLayoutInflater.inflate(R.layout.recommend_item,null));
        }else if(viewType == HOT){  // 秒杀
            return new HotViewHolder(mContent,mLayoutInflater.inflate(R.layout.hot_item,null));
        }
        return null;
    }

    // 相当于 绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==BANNER){
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            List<ResultBean.BannerInfoBean> banner_info = resultBean.getBanner_info();
            if (banner_info != null){
                bannerViewHolder.setData(banner_info);
            }
        }else if(getItemViewType(position)==CHANNEL){
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            List<ResultBean.ChannelInfoBean> chennel_info = resultBean.getChannel_info();
            if(chennel_info != null){
                channelViewHolder.setData(resultBean.getChannel_info());
            }
        }else if(getItemViewType(position)==ACT){
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            List<ResultBean.ActInfoBean> act_info = resultBean.getAct_info();
            if(act_info != null){
                actViewHolder.setData(act_info);
            }
        }else if(getItemViewType(position)==SECKILL){
            SecKillViewHolder secKillViewHolder = (SecKillViewHolder) holder;
            ResultBean.SecKillInfoBean secKill_info = resultBean.getSecKill_info();
            if(secKill_info!=null){
                secKillViewHolder.setData(secKill_info);
            }
        }else if(getItemViewType(position)==RECOMMEND){
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            List<ResultBean.RecommendInfoBean> recommend_info = resultBean.getRecommend_info();
            if(recommend_info!=null){
                recommendViewHolder.setData(recommend_info);
            }
        }else if(getItemViewType(position)==HOT){
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            List<ResultBean.HotInfoBean> hot_info = resultBean.getHot_info();
            if(hot_info!=null){
                hotViewHolder.setData(hot_info);
            }
        }
    }


    // banner横幅广告
    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private View itemView;
        private Banner banner;
        public BannerViewHolder(final Context mContext, View  itemView) {
            super(itemView);
            this.mContext = mContext;
            this.itemView = itemView;
            this.banner = (Banner) itemView.findViewById(R.id.banner);

        }
        public void setData(List<ResultBean.BannerInfoBean> list) {
            final String base_url = mContext.getString(R.string.base_url);
            // 拿到图片地址
            List<String> image_url_list = new ArrayList<>();
            for( ResultBean.BannerInfoBean banner : list){
                image_url_list.add(banner.getImage_url());
            }
            // 设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            // 设置手风琴动画
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(image_url_list).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(base_url + path).into(imageView);
                }
            }).start();

            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext,"广告功能尚未开放！",Toast.LENGTH_SHORT).show();
//                    startDishInfoActivity();
                }
            });
        }
    }
    // channel 通道部分
    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView gv_Channel;
        private ChannelAdapter adapter;
        public ChannelViewHolder(final Context mContent, View itemView) {
            super(itemView);
            this.mContext = mContent;
            this.gv_Channel = (GridView) itemView.findViewById(R.id.gv_channel);

        }
        public void setData(List<ResultBean.ChannelInfoBean> list){
            // 获得数据 设置适配器
            adapter = new ChannelAdapter(mContext, list);
            gv_Channel.setAdapter(adapter);
            // 设置点击事件
            gv_Channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(mContent,"入口尚未开放！",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    // act 活动部分
    class ActViewHolder extends  RecyclerView.ViewHolder{
        private Context mContext;
        private ViewPager act_viewPager;
        public ActViewHolder(Context mContent, View itemView) {
            super(itemView);
            this.mContext = mContent;
            this.act_viewPager = (ViewPager)itemView.findViewById(R.id.act_viewpager);
        }
        public void setData(final List<ResultBean.ActInfoBean> list){
            // 调整图片间距 渐变效果动画
            act_viewPager.setPageMargin(40);
            act_viewPager.setOffscreenPageLimit(3);
            act_viewPager.setPageTransformer(true,new ScaleInTransformer());
            act_viewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return list.size();
                }
                @Override
                public boolean isViewFromObject(View view, Object object) {
                    // 比较两个对象是不是同一个类型
                    // view 是页面的对象
                    // object 是 instantiateItem方法返回的
                    return view == object;
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    // 新建imageview 添加到容器当中  容器container就是viewpager
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(mContext).load(mContent.getString(R.string.base_url)+list.get(position).getImage_url()).into(imageView);
                    container.addView(imageView);

                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext,"活动暂未开放!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View)object);
                }
            });
        }
    }
    // seckill 秒杀部分
    class SecKillViewHolder extends RecyclerView.ViewHolder{
        private SeckillRecyclerViewAdapter seckillRecyclerViewAdapter;
        private Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        public SecKillViewHolder(Context mContent, View itemView) {
            super(itemView);
            this.mContext = mContent;
            tv_time_seckill = (TextView) itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = (TextView) itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill = (RecyclerView) itemView.findViewById(R.id.rv_seckill);
        }

        private long dt = 0;
        public void setData(final ResultBean.SecKillInfoBean secKillInfoBean){
            seckillRecyclerViewAdapter = new SeckillRecyclerViewAdapter(mContext,secKillInfoBean.getList());
            rv_seckill.setAdapter(seckillRecyclerViewAdapter);
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            seckillRecyclerViewAdapter.setOnSeckillRecyclerView(new SeckillRecyclerViewAdapter.OnSeckillRecyclerView() {
                @Override
                public void onItemClick(int position) {
                    ResultBean.SecKillInfoBean.ListBean data = secKillInfoBean.getList().get(position);
                    String json = JSON.toJSONString(data);
                    startDishInfoActivity(json);
                }
            });
            // 秒杀倒计时 算出时间后
            dt = Long.valueOf(secKillInfoBean.getEnd_time()) - Long.valueOf(secKillInfoBean.getStart_time());
            handler.sendEmptyMessage(0);
        }

        //秒杀倒计时
        @SuppressLint("HandlerLeak")
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dt = dt - 1000;
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                String time = format.format(new Date(dt));
                tv_time_seckill.setText(time);
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);
                if(dt<=0){
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };
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
    }
    // 热卖部分
    class HotViewHolder extends  RecyclerView.ViewHolder{
        private final Context mContext;
        private TextView tv_more_hot;
        private GridView gv_hot;
        private HotGridViewAdapter adapter;
        public HotViewHolder(final Context mContent, View itemView) {
            super(itemView);
            this.mContext = mContent;
            this.tv_more_hot = (TextView) itemView.findViewById(R.id.tv_more_hot);
            this.gv_hot = (GridView) itemView.findViewById(R.id.gv_hot);
        }
        public void setData(final List<ResultBean.HotInfoBean> list) {
            adapter = new HotGridViewAdapter(mContext,list);
            gv_hot.setAdapter(adapter);

            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    ResultBean.HotInfoBean data = list.get(i);
                    // 准备数据传递给下一个
                    String json = JSON.toJSONString(data);
                    startDishInfoActivity(json);
                }
            });
        }
    }

    // 获取类型
    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    // 总共有多少个item
    @Override
    public int getItemCount() {
        return 6;
    }


    // 启动菜品详情页面
    public void startDishInfoActivity(String data){
        GoodBean goodBean = JSON.parseObject(data,GoodBean.class);
        Intent intent = new Intent(mContent, GoodsInfoActivity.class);
        intent.putExtra(GOODBEAN,goodBean);
        mContent.startActivity(intent);
    }
}
