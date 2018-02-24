package smartdish.com.user.fragment;

import android.view.View;
import android.widget.TextView;

import smartdish.com.base.BaseFragment;

/**
 * 菜品界面的fragment
 */

public class UserFragment extends BaseFragment {
    TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);

        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("新的ui界面");
    }
}
