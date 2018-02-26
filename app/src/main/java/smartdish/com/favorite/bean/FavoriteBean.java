package smartdish.com.favorite.bean;


import java.util.List;

import smartdish.com.base.bean.GoodBean;

public class FavoriteBean {
    private List<GoodBean> list;

    public List<GoodBean> getList() {
        return list;
    }

    public void setList(List<GoodBean> list) {
        this.list = list;
    }
}
