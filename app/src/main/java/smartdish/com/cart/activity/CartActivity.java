package smartdish.com.cart.activity;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import smartdish.com.R;
import smartdish.com.cart.fragment.CartFragment;

public class CartActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        CartFragment fragment = new CartFragment();

        fragment.setBack(new CartFragment.ClickBack() {
            @Override
            public void clickBack() {
                CartActivity.this.finish();
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout,fragment).commit();



    }
}
