package smartdish.com.res.bean;


import java.util.List;

import smartdish.com.base.bean.GoodBean;

public class ResDishTypeListDishListBean {
    private List<ResDishTypeBean> list;

    public List<ResDishTypeBean> getList() {
        return list;
    }

    public void setList(List<ResDishTypeBean> list) {
        this.list = list;
    }

    public class ResDishTypeBean{
        private String typeId;
        private String typeName;
        private List<GoodBean> dishList;

        public List<GoodBean> getDishList() {
            return dishList;
        }

        public void setDishList(List<GoodBean> dishList) {
            this.dishList = dishList;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }

}