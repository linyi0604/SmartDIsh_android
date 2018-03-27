package smartdish.com.base.bean;

import java.io.Serializable;

/*
商品对象
 */
public class GoodBean implements Serializable{

    private String id;
    private String name;
    private String image_url;
    private String price;
    private String detail;
    private String grade;  // 评分
    private String sell_count;    //销量
    private String restaurant_id;
    private String restaurant;
    private String start_time;
    private String price_origin;
    private String end_time;
    private String features;
    private boolean isSelected = true;
    private String count;
    private String event_id;


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSell_count() {
        return sell_count;
    }

    public void setSell_count(String sell_count) {
        this.sell_count = sell_count;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getPrice_origin() {
        return price_origin;
    }

    public void setPrice_origin(String price_origin) {
        this.price_origin = price_origin;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }



    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "GoodBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", price='" + price + '\'' +
                ", detail='" + detail + '\'' +
                ", grade='" + grade + '\'' +
                ", sell_count='" + sell_count + '\'' +
                ", restaurant_id='" + restaurant_id + '\'' +
                ", restaurant='" + restaurant + '\'' +
                ", start_time='" + start_time + '\'' +
                ", price_origin='" + price_origin + '\'' +
                ", end_time='" + end_time + '\'' +
                ", features='" + features + '\'' +
                ", isSelected=" + isSelected +
                ", count='" + count + '\'' +
                ", event_id='" + event_id + '\'' +
                '}';
    }
}
