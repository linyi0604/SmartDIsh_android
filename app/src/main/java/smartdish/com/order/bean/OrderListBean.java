package smartdish.com.order.bean;

import java.util.ArrayList;
import java.util.List;

import smartdish.com.base.bean.GoodBean;

public class OrderListBean{
    private String id;  // 订单id
    private String time;    // 发起订单时间
    private String restaurant_id;
    private String restaurant;
    private String dish_count;
    private String total_price;
    private String is_payed;
    private ArrayList<GoodBean> dish_list;

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getDish_count() {
        return dish_count;
    }

    public void setDish_count(String dish_count) {
        this.dish_count = dish_count;
    }

    public String getIs_payed() {
        return is_payed;
    }

    public void setIs_payed(String is_payed) {
        this.is_payed = is_payed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public List<GoodBean> getDish_list() {
        return dish_list;
    }

    public void setDish_list(ArrayList<GoodBean> dish_list) {
        this.dish_list = dish_list;
    }
}