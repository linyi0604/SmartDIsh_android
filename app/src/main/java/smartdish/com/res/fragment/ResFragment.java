package smartdish.com.res.fragment;

import android.view.View;
import android.widget.TextView;

import smartdish.com.base.BaseFragment;

/**
 * 菜品界面的fragment
 */

public class ResFragment extends BaseFragment {
    TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);

        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("res");
    }
}
