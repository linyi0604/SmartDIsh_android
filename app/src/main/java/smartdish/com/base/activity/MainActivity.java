package smartdish.com.base.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import java.util.ArrayList;

import smartdish.com.R;
import smartdish.com.base.BaseFragment;
import smartdish.com.base.utils.MyUtils;
import smartdish.com.cart.fragment.CartFragment;
import smartdish.com.dish.fragment.DishFragment;
import smartdish.com.order.fragment.OrderFragment;
import smartdish.com.res.fragment.ResFragment;
import smartdish.com.user.fragment.UserFragment;

public class MainActivity extends FragmentActivity {


    // 存放多个fragment实例的集合
    private ArrayList<BaseFragment> fragments;
    private RadioGroup rg_main;
    private int position = 0;
    private Fragment currentFragment; // 缓存fragment
    private BaseFragment mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_main = findViewById(R.id.rg_main);

        initFragment();
        // 设置radioGroup的监听
        initListener();

    }

    // 设置radioGroup的监听
    private void initListener() {
        rg_main.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_dish:  // 点击了菜品
                        position = 0;
                        fragments.set(0,new DishFragment());
                        break;
                    case R.id.rb_res:   // 点击了餐厅
                        position = 1;
                        fragments.set(1,new ResFragment());
                        break;
                    case R.id.rb_cart:  //点击了购物车
                        position = 2;
                        if(MyUtils.isLogin(MainActivity.this)){
                            fragments.set(2,new CartFragment());
                        }else{
                            MyUtils.toLoginPage(MainActivity.this);
                        }
                        break;
                    case R.id.rb_order: // 点击了订单
                        position = 3;
                        if(MyUtils.isLogin(MainActivity.this)){
                            fragments.set(3,new OrderFragment());
                        }else{
                            MyUtils.toLoginPage(MainActivity.this);
                        }
                        break;
                    case R.id.rb_me:    // 点击了我
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }
                // 根据位置 取不同的fragment
                BaseFragment toFragment = getFragment(position);
                switchFragment(currentFragment,toFragment);

            }
        });

        rg_main.check(R.id.rb_dish);
    }

    // 根据点击 获取相应的fragment
    private BaseFragment getFragment(int position){
        if(fragments != null && fragments.size()>0){
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    // 将fragment 放入布局当中 from是上次fragment  to是要切换的fragment
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment){
        if(fromFragment != nextFragment){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            currentFragment = nextFragment;
            if(!nextFragment.isAdded()){
                if(fromFragment != null){
                    transaction.hide(fromFragment);
                }
                transaction.add(R.id.frameLayout,nextFragment).commit();
            }else{
                if(fromFragment!=null){
                    transaction.hide(fromFragment);
                }
                transaction.show(nextFragment).commit();
            }
        }
    }

    // 初始化fragment 要按照顺序
    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new DishFragment());
        fragments.add(new ResFragment());
        fragments.add(new CartFragment());
        fragments.add(new OrderFragment());
        fragments.add(new UserFragment());

    }
}
