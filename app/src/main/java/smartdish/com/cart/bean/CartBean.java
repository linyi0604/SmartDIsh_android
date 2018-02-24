package smartdish.com.cart.bean;

import java.util.List;

import smartdish.com.base.bean.GoodBean;

public class CartBean {

    public List<GoodBean> getList() {
        return list;
    }

    public void setList(List<GoodBean> list) {
        this.list = list;
    }

    private List<GoodBean> list;

}
