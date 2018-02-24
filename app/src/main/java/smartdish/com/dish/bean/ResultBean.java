package smartdish.com.dish.bean;

import java.util.List;

/**
 * Created by Lenovo on 2018/2/14.
 */

public class ResultBean {

    /**
     * banner_info : [{"image_url":"/static/media/image/16_dish_IMG_3924.JPG","price":"15","detail":"香浓的咖喱，搭配新鲜的苹果，清甜的果味和咖喱完美融合，不仅浸入鲜嫩大块的鸡肉中，更渗入到每一粒糙米中。糙米的健康，胡萝卜的甘甜，鸡肉的香嫩，与苹果的柔和甜美，共同组成了这一道健康低卡又非常美味的咖喱苹果鸡肉糙米饭！~满足热爱咖喱的你，或是热爱美食又处在减脂期的你！~（若是你吃不惯粗粮糙米，也可以换成优质五常大米。但是饭粒的黏软丝毫不减~）","id":16,"name":"咖喱苹果鸡肉饭","price_origin":"","features":"","restaurant":"元气少女糖小蓝的小厨房","restaurant_id":7},{"image_url":"/static/media/image/18_dish_IMG_3509.JPG","price":"32","detail":"想吃蛋糕？又怕胖？这道健康甜品满足你！\r\n来自沂蒙山的优质紫薯，细腻的口感，蜜糖般香甜，却不添加一颗糖，不含任何油脂，只有原生的甜蜜和美好，浇之原味酸奶，每一口都像在舔盖，柔滑细腻，口感堪比半熟芝士蛋糕但胜于蛋糕！来自冰天雪地的牛奶草莓，献给喜爱甜品的少女\u2014\u2014你，还可以在美食前保持完美身材，以最美的姿态，品尝少女之心吧！~","id":18,"name":"少女之心（紫薯酸奶蛋糕）","price_origin":"","features":"","restaurant":"元气少女糖小蓝的小厨房","restaurant_id":7},{"image_url":"/static/media/image/","price":"45","detail":"就是东北乱炖嘛","id":21,"name":"新煮艺","price_origin":"","features":"","restaurant":"沐光cafe","restaurant_id":8},{"image_url":"/static/media/image/","price":"223","detail":"223","id":23,"name":"223","price_origin":"","features":"肉多 蔬菜多 硬菜 小吃 油炸 带陷 湘菜 粤菜 甜 冷 热 清淡 重口","restaurant":"223","restaurant_id":10},{"image_url":"/static/media/image/44_dish_39552970276e25982619d123b08bb065.jpg","price":"1","detail":"","id":44,"name":"大米饭","price_origin":"","features":"","restaurant":"res1","restaurant_id":2},{"image_url":"/static/media/image/45_dish_决赛.jpg","price":"15","detail":"","id":45,"name":"西红柿炒鸡蛋","price_origin":"","features":"蔬菜多 硬菜 油炸 粤菜 酸 甜 热 清淡","restaurant":"res1","restaurant_id":2},{"image_url":"/static/media/image/46_dish_429b4e41b73f4f5d7318ab7ebdf7fd53.jpg","price":"22","detail":"","id":46,"name":"test","price_origin":"","features":"","restaurant":"res1","restaurant_id":2}]
     * act_info : [{"image_url":"/static/media/image/16_dish_IMG_3924.JPG","price":"15","detail":"香浓的咖喱，搭配新鲜的苹果，清甜的果味和咖喱完美融合，不仅浸入鲜嫩大块的鸡肉中，更渗入到每一粒糙米中。糙米的健康，胡萝卜的甘甜，鸡肉的香嫩，与苹果的柔和甜美，共同组成了这一道健康低卡又非常美味的咖喱苹果鸡肉糙米饭！~满足热爱咖喱的你，或是热爱美食又处在减脂期的你！~（若是你吃不惯粗粮糙米，也可以换成优质五常大米。但是饭粒的黏软丝毫不减~）","id":16,"name":"咖喱苹果鸡肉饭","price_origin":"","features":"","restaurant":"元气少女糖小蓝的小厨房","restaurant_id":7},{"image_url":"/static/media/image/18_dish_IMG_3509.JPG","price":"32","detail":"想吃蛋糕？又怕胖？这道健康甜品满足你！\r\n来自沂蒙山的优质紫薯，细腻的口感，蜜糖般香甜，却不添加一颗糖，不含任何油脂，只有原生的甜蜜和美好，浇之原味酸奶，每一口都像在舔盖，柔滑细腻，口感堪比半熟芝士蛋糕但胜于蛋糕！来自冰天雪地的牛奶草莓，献给喜爱甜品的少女\u2014\u2014你，还可以在美食前保持完美身材，以最美的姿态，品尝少女之心吧！~","id":18,"name":"少女之心（紫薯酸奶蛋糕）","price_origin":"","features":"","restaurant":"元气少女糖小蓝的小厨房","restaurant_id":7},{"image_url":"/static/media/image/","price":"45","detail":"就是东北乱炖嘛","id":21,"name":"新煮艺","price_origin":"","features":"","restaurant":"沐光cafe","restaurant_id":8},{"image_url":"/static/media/image/","price":"223","detail":"223","id":23,"name":"223","price_origin":"","features":"肉多 蔬菜多 硬菜 小吃 油炸 带陷 湘菜 粤菜 甜 冷 热 清淡 重口","restaurant":"223","restaurant_id":10},{"image_url":"/static/media/image/44_dish_39552970276e25982619d123b08bb065.jpg","price":"1","detail":"","id":44,"name":"大米饭","price_origin":"","features":"","restaurant":"res1","restaurant_id":2},{"image_url":"/static/media/image/45_dish_决赛.jpg","price":"15","detail":"","id":45,"name":"西红柿炒鸡蛋","price_origin":"","features":"蔬菜多 硬菜 油炸 粤菜 酸 甜 热 清淡","restaurant":"res1","restaurant_id":2},{"image_url":"/static/media/image/46_dish_429b4e41b73f4f5d7318ab7ebdf7fd53.jpg","price":"22","detail":"","id":46,"name":"test","price_origin":"","features":"","restaurant":"res1","restaurant_id":2}]
     * channel_info : [{"image_url":"/static/media/image/customer/channel/000.png","name":"肉多","id":1},{"image_url":"/static/media/image/customer/channel/001.png","name":"蔬菜多","id":2},{"image_url":"/static/media/image/customer/channel/002.png","name":"海鲜","id":3},{"image_url":"/static/media/image/customer/channel/003.png","name":"硬菜","id":4},{"image_url":"/static/media/image/customer/channel/004.png","name":"小吃","id":5},{"image_url":"/static/media/image/customer/channel/005.png","name":"正餐","id":6},{"image_url":"/static/media/image/customer/channel/006.png","name":"方便","id":7},{"image_url":"/static/media/image/customer/channel/007.png","name":"炒菜","id":8},{"image_url":"/static/media/image/customer/channel/008.png","name":"炖菜","id":9},{"image_url":"/static/media/image/customer/channel/009.png","name":"油炸","id":10},{"image_url":"/static/media/image/customer/channel/0010.png","name":"烘烤","id":11}]
     * recommend_info : [{"image_url":"/static/media/image/16_dish_IMG_3924.JPG","price":"15","features":"","id":16,"detail":"香浓的咖喱，搭配新鲜的苹果，清甜的果味和咖喱完美融合，不仅浸入鲜嫩大块的鸡肉中，更渗入到每一粒糙米中。糙米的健康，胡萝卜的甘甜，鸡肉的香嫩，与苹果的柔和甜美，共同组成了这一道健康低卡又非常美味的咖喱苹果鸡肉糙米饭！~满足热爱咖喱的你，或是热爱美食又处在减脂期的你！~（若是你吃不惯粗粮糙米，也可以换成优质五常大米。但是饭粒的黏软丝毫不减~）","name":"咖喱苹果鸡肉饭","restaurant":"元气少女糖小蓝的小厨房","restaurant_id":7},{"image_url":"/static/media/image/18_dish_IMG_3509.JPG","price":"32","features":"","id":18,"detail":"想吃蛋糕？又怕胖？这道健康甜品满足你！\r\n来自沂蒙山的优质紫薯，细腻的口感，蜜糖般香甜，却不添加一颗糖，不含任何油脂，只有原生的甜蜜和美好，浇之原味酸奶，每一口都像在舔盖，柔滑细腻，口感堪比半熟芝士蛋糕但胜于蛋糕！来自冰天雪地的牛奶草莓，献给喜爱甜品的少女\u2014\u2014你，还可以在美食前保持完美身材，以最美的姿态，品尝少女之心吧！~","name":"少女之心（紫薯酸奶蛋糕）","restaurant":"元气少女糖小蓝的小厨房","restaurant_id":7},{"image_url":"/static/media/image/","price":"45","features":"","id":21,"detail":"就是东北乱炖嘛","name":"新煮艺","restaurant":"沐光cafe","restaurant_id":8},{"image_url":"/static/media/image/","price":"223","features":"肉多 蔬菜多 硬菜 小吃 油炸 带陷 湘菜 粤菜 甜 冷 热 清淡 重口","id":23,"detail":"223","name":"223","restaurant":"223","restaurant_id":10},{"image_url":"/static/media/image/44_dish_39552970276e25982619d123b08bb065.jpg","price":"1","features":"","id":44,"detail":"","name":"大米饭","restaurant":"res1","restaurant_id":2},{"image_url":"/static/media/image/45_dish_决赛.jpg","price":"15","features":"蔬菜多 硬菜 油炸 粤菜 酸 甜 热 清淡","id":45,"detail":"","name":"西红柿炒鸡蛋","restaurant":"res1","restaurant_id":2},{"image_url":"/static/media/image/46_dish_429b4e41b73f4f5d7318ab7ebdf7fd53.jpg","price":"22","features":"","id":46,"detail":"","name":"test","restaurant":"res1","restaurant_id":2}]
     * secKill_info : {"list":[{"image_url":"/static/media/image/16_dish_IMG_3924.JPG","price":"15","detail":"香浓的咖喱，搭配新鲜的苹果，清甜的果味和咖喱完美融合，不仅浸入鲜嫩大块的鸡肉中，更渗入到每一粒糙米中。糙米的健康，胡萝卜的甘甜，鸡肉的香嫩，与苹果的柔和甜美，共同组成了这一道健康低卡又非常美味的咖喱苹果鸡肉糙米饭！~满足热爱咖喱的你，或是热爱美食又处在减脂期的你！~（若是你吃不惯粗粮糙米，也可以换成优质五常大米。但是饭粒的黏软丝毫不减~）","start_time":"","id":16,"name":"咖喱苹果鸡肉饭","price_origin":"","restaurant_id":7,"features":"","restaurant":"元气少女糖小蓝的小厨房","end_time":""},{"image_url":"/static/media/image/18_dish_IMG_3509.JPG","price":"32","detail":"想吃蛋糕？又怕胖？这道健康甜品满足你！\r\n来自沂蒙山的优质紫薯，细腻的口感，蜜糖般香甜，却不添加一颗糖，不含任何油脂，只有原生的甜蜜和美好，浇之原味酸奶，每一口都像在舔盖，柔滑细腻，口感堪比半熟芝士蛋糕但胜于蛋糕！来自冰天雪地的牛奶草莓，献给喜爱甜品的少女\u2014\u2014你，还可以在美食前保持完美身材，以最美的姿态，品尝少女之心吧！~","start_time":"","id":18,"name":"少女之心（紫薯酸奶蛋糕）","price_origin":"","restaurant_id":7,"features":"","restaurant":"元气少女糖小蓝的小厨房","end_time":""},{"image_url":"/static/media/image/","price":"45","detail":"就是东北乱炖嘛","start_time":"","id":21,"name":"新煮艺","price_origin":"","restaurant_id":8,"features":"","restaurant":"沐光cafe","end_time":""},{"image_url":"/static/media/image/","price":"223","detail":"223","start_time":"","id":23,"name":"223","price_origin":"","restaurant_id":10,"features":"肉多 蔬菜多 硬菜 小吃 油炸 带陷 湘菜 粤菜 甜 冷 热 清淡 重口","restaurant":"223","end_time":""},{"image_url":"/static/media/image/44_dish_39552970276e25982619d123b08bb065.jpg","price":"1","detail":"","start_time":"","id":44,"name":"大米饭","price_origin":"","restaurant_id":2,"features":"","restaurant":"res1","end_time":""},{"image_url":"/static/media/image/45_dish_决赛.jpg","price":"15","detail":"","start_time":"","id":45,"name":"西红柿炒鸡蛋","price_origin":"","restaurant_id":2,"features":"蔬菜多 硬菜 油炸 粤菜 酸 甜 热 清淡","restaurant":"res1","end_time":""},{"image_url":"/static/media/image/46_dish_429b4e41b73f4f5d7318ab7ebdf7fd53.jpg","price":"22","detail":"","start_time":"","id":46,"name":"test","price_origin":"","restaurant_id":2,"features":"","restaurant":"res1","end_time":""}],"end_time":1519036132094,"start_time":1519028992094}
     * hot_info : [{"image_url":"/static/media/image/16_dish_IMG_3924.JPG","price":"15","features":"","id":16,"detail":"香浓的咖喱，搭配新鲜的苹果，清甜的果味和咖喱完美融合，不仅浸入鲜嫩大块的鸡肉中，更渗入到每一粒糙米中。糙米的健康，胡萝卜的甘甜，鸡肉的香嫩，与苹果的柔和甜美，共同组成了这一道健康低卡又非常美味的咖喱苹果鸡肉糙米饭！~满足热爱咖喱的你，或是热爱美食又处在减脂期的你！~（若是你吃不惯粗粮糙米，也可以换成优质五常大米。但是饭粒的黏软丝毫不减~）","name":"咖喱苹果鸡肉饭","restaurant":"元气少女糖小蓝的小厨房","restaurant_id":7},{"image_url":"/static/media/image/18_dish_IMG_3509.JPG","price":"32","features":"","id":18,"detail":"想吃蛋糕？又怕胖？这道健康甜品满足你！\r\n来自沂蒙山的优质紫薯，细腻的口感，蜜糖般香甜，却不添加一颗糖，不含任何油脂，只有原生的甜蜜和美好，浇之原味酸奶，每一口都像在舔盖，柔滑细腻，口感堪比半熟芝士蛋糕但胜于蛋糕！来自冰天雪地的牛奶草莓，献给喜爱甜品的少女\u2014\u2014你，还可以在美食前保持完美身材，以最美的姿态，品尝少女之心吧！~","name":"少女之心（紫薯酸奶蛋糕）","restaurant":"元气少女糖小蓝的小厨房","restaurant_id":7},{"image_url":"/static/media/image/","price":"45","features":"","id":21,"detail":"就是东北乱炖嘛","name":"新煮艺","restaurant":"沐光cafe","restaurant_id":8},{"image_url":"/static/media/image/","price":"223","features":"肉多 蔬菜多 硬菜 小吃 油炸 带陷 湘菜 粤菜 甜 冷 热 清淡 重口","id":23,"detail":"223","name":"223","restaurant":"223","restaurant_id":10},{"image_url":"/static/media/image/44_dish_39552970276e25982619d123b08bb065.jpg","price":"1","features":"","id":44,"detail":"","name":"大米饭","restaurant":"res1","restaurant_id":2},{"image_url":"/static/media/image/45_dish_决赛.jpg","price":"15","features":"蔬菜多 硬菜 油炸 粤菜 酸 甜 热 清淡","id":45,"detail":"","name":"西红柿炒鸡蛋","restaurant":"res1","restaurant_id":2},{"image_url":"/static/media/image/46_dish_429b4e41b73f4f5d7318ab7ebdf7fd53.jpg","price":"22","features":"","id":46,"detail":"","name":"test","restaurant":"res1","restaurant_id":2}]
     */

    private SecKillInfoBean secKill_info;
    private List<BannerInfoBean> banner_info;
    private List<ActInfoBean> act_info;
    private List<ChannelInfoBean> channel_info;
    private List<RecommendInfoBean> recommend_info;
    private List<HotInfoBean> hot_info;

    public SecKillInfoBean getSecKill_info() {
        return secKill_info;
    }

    public void setSecKill_info(SecKillInfoBean secKill_info) {
        this.secKill_info = secKill_info;
    }

    public List<BannerInfoBean> getBanner_info() {
        return banner_info;
    }

    public void setBanner_info(List<BannerInfoBean> banner_info) {
        this.banner_info = banner_info;
    }

    public List<ActInfoBean> getAct_info() {
        return act_info;
    }

    public void setAct_info(List<ActInfoBean> act_info) {
        this.act_info = act_info;
    }

    public List<ChannelInfoBean> getChannel_info() {
        return channel_info;
    }

    public void setChannel_info(List<ChannelInfoBean> channel_info) {
        this.channel_info = channel_info;
    }

    public List<RecommendInfoBean> getRecommend_info() {
        return recommend_info;
    }

    public void setRecommend_info(List<RecommendInfoBean> recommend_info) {
        this.recommend_info = recommend_info;
    }

    public List<HotInfoBean> getHot_info() {
        return hot_info;
    }

    public void setHot_info(List<HotInfoBean> hot_info) {
        this.hot_info = hot_info;
    }

    public static class SecKillInfoBean {
        /**
         * list : [{"image_url":"/static/media/image/16_dish_IMG_3924.JPG","price":"15","detail":"香浓的咖喱，搭配新鲜的苹果，清甜的果味和咖喱完美融合，不仅浸入鲜嫩大块的鸡肉中，更渗入到每一粒糙米中。糙米的健康，胡萝卜的甘甜，鸡肉的香嫩，与苹果的柔和甜美，共同组成了这一道健康低卡又非常美味的咖喱苹果鸡肉糙米饭！~满足热爱咖喱的你，或是热爱美食又处在减脂期的你！~（若是你吃不惯粗粮糙米，也可以换成优质五常大米。但是饭粒的黏软丝毫不减~）","start_time":"","id":16,"name":"咖喱苹果鸡肉饭","price_origin":"","restaurant_id":7,"features":"","restaurant":"元气少女糖小蓝的小厨房","end_time":""},{"image_url":"/static/media/image/18_dish_IMG_3509.JPG","price":"32","detail":"想吃蛋糕？又怕胖？这道健康甜品满足你！\r\n来自沂蒙山的优质紫薯，细腻的口感，蜜糖般香甜，却不添加一颗糖，不含任何油脂，只有原生的甜蜜和美好，浇之原味酸奶，每一口都像在舔盖，柔滑细腻，口感堪比半熟芝士蛋糕但胜于蛋糕！来自冰天雪地的牛奶草莓，献给喜爱甜品的少女\u2014\u2014你，还可以在美食前保持完美身材，以最美的姿态，品尝少女之心吧！~","start_time":"","id":18,"name":"少女之心（紫薯酸奶蛋糕）","price_origin":"","restaurant_id":7,"features":"","restaurant":"元气少女糖小蓝的小厨房","end_time":""},{"image_url":"/static/media/image/","price":"45","detail":"就是东北乱炖嘛","start_time":"","id":21,"name":"新煮艺","price_origin":"","restaurant_id":8,"features":"","restaurant":"沐光cafe","end_time":""},{"image_url":"/static/media/image/","price":"223","detail":"223","start_time":"","id":23,"name":"223","price_origin":"","restaurant_id":10,"features":"肉多 蔬菜多 硬菜 小吃 油炸 带陷 湘菜 粤菜 甜 冷 热 清淡 重口","restaurant":"223","end_time":""},{"image_url":"/static/media/image/44_dish_39552970276e25982619d123b08bb065.jpg","price":"1","detail":"","start_time":"","id":44,"name":"大米饭","price_origin":"","restaurant_id":2,"features":"","restaurant":"res1","end_time":""},{"image_url":"/static/media/image/45_dish_决赛.jpg","price":"15","detail":"","start_time":"","id":45,"name":"西红柿炒鸡蛋","price_origin":"","restaurant_id":2,"features":"蔬菜多 硬菜 油炸 粤菜 酸 甜 热 清淡","restaurant":"res1","end_time":""},{"image_url":"/static/media/image/46_dish_429b4e41b73f4f5d7318ab7ebdf7fd53.jpg","price":"22","detail":"","start_time":"","id":46,"name":"test","price_origin":"","restaurant_id":2,"features":"","restaurant":"res1","end_time":""}]
         * end_time : 1519036132094
         * start_time : 1519028992094
         */

        private long end_time;
        private long start_time;
        private List<ListBean> list;

        public long getEnd_time() {
            return end_time;
        }

        public void setEnd_time(long end_time) {
            this.end_time = end_time;
        }

        public long getStart_time() {
            return start_time;
        }

        public void setStart_time(long start_time) {
            this.start_time = start_time;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * image_url : /static/media/image/16_dish_IMG_3924.JPG
             * price : 15
             * detail : 香浓的咖喱，搭配新鲜的苹果，清甜的果味和咖喱完美融合，不仅浸入鲜嫩大块的鸡肉中，更渗入到每一粒糙米中。糙米的健康，胡萝卜的甘甜，鸡肉的香嫩，与苹果的柔和甜美，共同组成了这一道健康低卡又非常美味的咖喱苹果鸡肉糙米饭！~满足热爱咖喱的你，或是热爱美食又处在减脂期的你！~（若是你吃不惯粗粮糙米，也可以换成优质五常大米。但是饭粒的黏软丝毫不减~）
             * start_time :
             * id : 16
             * name : 咖喱苹果鸡肉饭
             * price_origin :
             * restaurant_id : 7
             * features :
             * restaurant : 元气少女糖小蓝的小厨房
             * end_time :
             */

            private String image_url;
            private String price;
            private String detail;
            private String start_time;
            private int id;
            private String name;
            private String price_origin;
            private int restaurant_id;
            private String features;
            private String restaurant;
            private String end_time;

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice_origin() {
                return price_origin;
            }

            public void setPrice_origin(String price_origin) {
                this.price_origin = price_origin;
            }

            public int getRestaurant_id() {
                return restaurant_id;
            }

            public void setRestaurant_id(int restaurant_id) {
                this.restaurant_id = restaurant_id;
            }

            public String getFeatures() {
                return features;
            }

            public void setFeatures(String features) {
                this.features = features;
            }

            public String getRestaurant() {
                return restaurant;
            }

            public void setRestaurant(String restaurant) {
                this.restaurant = restaurant;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }
        }
    }

    public static class BannerInfoBean {
        /**
         * image_url : /static/media/image/16_dish_IMG_3924.JPG
         * price : 15
         * detail : 香浓的咖喱，搭配新鲜的苹果，清甜的果味和咖喱完美融合，不仅浸入鲜嫩大块的鸡肉中，更渗入到每一粒糙米中。糙米的健康，胡萝卜的甘甜，鸡肉的香嫩，与苹果的柔和甜美，共同组成了这一道健康低卡又非常美味的咖喱苹果鸡肉糙米饭！~满足热爱咖喱的你，或是热爱美食又处在减脂期的你！~（若是你吃不惯粗粮糙米，也可以换成优质五常大米。但是饭粒的黏软丝毫不减~）
         * id : 16
         * name : 咖喱苹果鸡肉饭
         * price_origin :
         * features :
         * restaurant : 元气少女糖小蓝的小厨房
         * restaurant_id : 7
         */

        private String image_url;
        private String price;
        private String detail;
        private int id;
        private String name;
        private String price_origin;
        private String features;
        private String restaurant;
        private int restaurant_id;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice_origin() {
            return price_origin;
        }

        public void setPrice_origin(String price_origin) {
            this.price_origin = price_origin;
        }

        public String getFeatures() {
            return features;
        }

        public void setFeatures(String features) {
            this.features = features;
        }

        public String getRestaurant() {
            return restaurant;
        }

        public void setRestaurant(String restaurant) {
            this.restaurant = restaurant;
        }

        public int getRestaurant_id() {
            return restaurant_id;
        }

        public void setRestaurant_id(int restaurant_id) {
            this.restaurant_id = restaurant_id;
        }
    }

    public static class ActInfoBean {
        /**
         * image_url : /static/media/image/16_dish_IMG_3924.JPG
         * price : 15
         * detail : 香浓的咖喱，搭配新鲜的苹果，清甜的果味和咖喱完美融合，不仅浸入鲜嫩大块的鸡肉中，更渗入到每一粒糙米中。糙米的健康，胡萝卜的甘甜，鸡肉的香嫩，与苹果的柔和甜美，共同组成了这一道健康低卡又非常美味的咖喱苹果鸡肉糙米饭！~满足热爱咖喱的你，或是热爱美食又处在减脂期的你！~（若是你吃不惯粗粮糙米，也可以换成优质五常大米。但是饭粒的黏软丝毫不减~）
         * id : 16
         * name : 咖喱苹果鸡肉饭
         * price_origin :
         * features :
         * restaurant : 元气少女糖小蓝的小厨房
         * restaurant_id : 7
         */

        private String image_url;
        private String price;
        private String detail;
        private int id;
        private String name;
        private String price_origin;
        private String features;
        private String restaurant;
        private int restaurant_id;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice_origin() {
            return price_origin;
        }

        public void setPrice_origin(String price_origin) {
            this.price_origin = price_origin;
        }

        public String getFeatures() {
            return features;
        }

        public void setFeatures(String features) {
            this.features = features;
        }

        public String getRestaurant() {
            return restaurant;
        }

        public void setRestaurant(String restaurant) {
            this.restaurant = restaurant;
        }

        public int getRestaurant_id() {
            return restaurant_id;
        }

        public void setRestaurant_id(int restaurant_id) {
            this.restaurant_id = restaurant_id;
        }
    }

    public static class ChannelInfoBean {
        /**
         * image_url : /static/media/image/customer/channel/000.png
         * name : 肉多
         * id : 1
         */

        private String image_url;
        private String name;
        private int id;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class RecommendInfoBean {
        /**
         * image_url : /static/media/image/16_dish_IMG_3924.JPG
         * price : 15
         * features :
         * id : 16
         * detail : 香浓的咖喱，搭配新鲜的苹果，清甜的果味和咖喱完美融合，不仅浸入鲜嫩大块的鸡肉中，更渗入到每一粒糙米中。糙米的健康，胡萝卜的甘甜，鸡肉的香嫩，与苹果的柔和甜美，共同组成了这一道健康低卡又非常美味的咖喱苹果鸡肉糙米饭！~满足热爱咖喱的你，或是热爱美食又处在减脂期的你！~（若是你吃不惯粗粮糙米，也可以换成优质五常大米。但是饭粒的黏软丝毫不减~）
         * name : 咖喱苹果鸡肉饭
         * restaurant : 元气少女糖小蓝的小厨房
         * restaurant_id : 7
         */

        private String image_url;
        private String price;
        private String features;
        private int id;
        private String detail;
        private String name;
        private String restaurant;
        private int restaurant_id;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getFeatures() {
            return features;
        }

        public void setFeatures(String features) {
            this.features = features;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRestaurant() {
            return restaurant;
        }

        public void setRestaurant(String restaurant) {
            this.restaurant = restaurant;
        }

        public int getRestaurant_id() {
            return restaurant_id;
        }

        public void setRestaurant_id(int restaurant_id) {
            this.restaurant_id = restaurant_id;
        }
    }

    public static class HotInfoBean {
        /**
         * image_url : /static/media/image/16_dish_IMG_3924.JPG
         * price : 15
         * features :
         * id : 16
         * detail : 香浓的咖喱，搭配新鲜的苹果，清甜的果味和咖喱完美融合，不仅浸入鲜嫩大块的鸡肉中，更渗入到每一粒糙米中。糙米的健康，胡萝卜的甘甜，鸡肉的香嫩，与苹果的柔和甜美，共同组成了这一道健康低卡又非常美味的咖喱苹果鸡肉糙米饭！~满足热爱咖喱的你，或是热爱美食又处在减脂期的你！~（若是你吃不惯粗粮糙米，也可以换成优质五常大米。但是饭粒的黏软丝毫不减~）
         * name : 咖喱苹果鸡肉饭
         * restaurant : 元气少女糖小蓝的小厨房
         * restaurant_id : 7
         */

        private String image_url;
        private String price;
        private String features;
        private int id;
        private String detail;
        private String name;
        private String restaurant;
        private int restaurant_id;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getFeatures() {
            return features;
        }

        public void setFeatures(String features) {
            this.features = features;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRestaurant() {
            return restaurant;
        }

        public void setRestaurant(String restaurant) {
            this.restaurant = restaurant;
        }

        public int getRestaurant_id() {
            return restaurant_id;
        }

        public void setRestaurant_id(int restaurant_id) {
            this.restaurant_id = restaurant_id;
        }
    }
}
