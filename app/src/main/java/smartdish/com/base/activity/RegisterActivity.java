package smartdish.com.base.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import smartdish.com.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private EditText et_password2;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = RegisterActivity.this;
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password2 = (EditText) findViewById(R.id.et_password2);

    }

    // 点击注册按钮
    public void register(View v){
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String password2 = et_password2.getText().toString().trim();
        // 判断两次密码是否一致 否则return
        if(!password.equals(password2)){
            Toast.makeText(mContext,"两次密码不相同！",Toast.LENGTH_SHORT).show();
            return;
        }
        // 发送请求 尝试进行注册
        String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
        OkHttpUtils.get().url( url + "/register" )
                .addParams(mContext.getString(R.string.secret_key),mContext.getString(R.string.secret_value))
                .addParams("username",username)
                .addParams("password",password)
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
                                    Toast.makeText(mContext,"注册成功，请登录!",Toast.LENGTH_LONG).show();
                                }
                            });
                            finish();
                        }else if("notOK".equals(response)){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mContext,"用户名已经被注册，请更换用户名!",Toast.LENGTH_SHORT).show();
                                }
                            });
                            return;
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