package smartdish.com.user.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import smartdish.com.R;
import smartdish.com.base.BaseFragment;
import smartdish.com.base.activity.MainActivity;
import smartdish.com.base.utils.MyUtils;

public class UserInfoActivity extends Activity implements View.OnClickListener {
    private Context mContext;
    private TextView tv_username;
    private EditText et_password;
    private EditText et_password2;
    private EditText et_name;
    private EditText et_phone;
    private EditText et_detail;
    private ImageButton ib_back;
    private Button btn_save;
    private Button btn_back;


    public void findViews(){
        mContext = UserInfoActivity.this;
        et_password = findViewById(R.id.et_password);
        et_password2 = findViewById(R.id.et_password2);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_detail = findViewById(R.id.et_detail);
        ib_back = findViewById(R.id.ib_back);
        btn_save = findViewById(R.id.btn_save);
        btn_back = findViewById(R.id.btn_back);
        tv_username = findViewById(R.id.tv_username);

        ib_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        btn_back.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        findViews();
        initData();
    }

    public void initData(){
        try{
            SharedPreferences sp = mContext.getSharedPreferences(MyUtils.SP_NAME,0);
            tv_username.setText(sp.getString(MyUtils.USERNAME,""));
            et_password.setText(sp.getString(MyUtils.PASSWORD,""));
            et_password2.setText(sp.getString(MyUtils.PASSWORD,""));
            et_name.setText(sp.getString(MyUtils.NAME,""));
            et_phone.setText(sp.getString(MyUtils.PHONE,""));
            et_detail.setText(sp.getString(MyUtils.DETAIL,""));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if( v == ib_back || v == btn_back){ // 返回
            finish();
        }else if(v == btn_save){    // 保存信息
            final String password = et_password.getText().toString().trim();
            String password2 = et_password2.getText().toString().trim();
            if(!password.equals(password2)){
                Toast.makeText(mContext,"两次密码不同！",Toast.LENGTH_SHORT).show();
                return;
            }
            final String name = et_name.getText().toString().trim();
            final String phone = et_phone.getText().toString().trim();
            Pattern pattern = Pattern.compile("[0-9]{1,}");
            Matcher matcher = pattern.matcher((CharSequence)phone);
            boolean result=matcher.matches();
            if(!result){
                Toast.makeText(mContext,"请输入正确电话号码！",Toast.LENGTH_SHORT).show();
                return;
            }
            final String detail = et_detail.getText().toString().trim();

            // 发送请求 修改用户信息
            String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
            OkHttpUtils.get().url( url + "/updateCustomerInfo" )
                    .addParams(mContext.getString(R.string.secret_key),mContext.getString(R.string.secret_value))
                    .addParams("username",MyUtils.getUsername(mContext))
                    .addParams("password",password)
                    .addParams("name",name)
                    .addParams("phone",phone)
                    .addParams("detail",detail)
                    .build()
                    .execute(new StringCallback() {
                        // 请求失败的时候
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.out.println(e);
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                        // 请求成功的时候
                        @Override
                        public void onResponse(final String response, int id) {
                            if("OK".equals(response)){
                                //注册成功
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Boolean result = MyUtils.updateUserInfo(mContext,password,name,phone,detail);
                                        if(result){
                                            Toast.makeText(mContext,"修改成功！",Toast.LENGTH_LONG).show();
                                            finish();
                                        }else{
                                            Toast.makeText(mContext,"手机发生存储异常！",Toast.LENGTH_LONG).show();
                                            return;
                                        }
                                    }
                                });
                            }else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext,"发生了未知错误:"+response,Toast.LENGTH_SHORT).show();
                                    }
                                });
                                return;
                            }
                        }
                    });
        }



        }

}
