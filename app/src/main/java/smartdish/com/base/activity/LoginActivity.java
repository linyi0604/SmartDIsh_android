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
import smartdish.com.base.utils.MyUtils;

public class LoginActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = LoginActivity.this;
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
    }

    // 登录按钮点击
    public void login(View v){
        final String username = et_username.getText().toString().trim();
        final String password = et_password.getText().toString().trim();
        if(username.equals("") || password.equals("")){
            Toast.makeText(mContext,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        // 发送请求 尝试进行登录
        String url = mContext.getString(R.string.base_url)+mContext.getString(R.string.appUrl);
        OkHttpUtils.get().url( url + "/login" )
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
                                    Boolean result = MyUtils.setLogin(mContext,username,password);
                                    if(result){
                                        Toast.makeText(mContext,"登录成功！",Toast.LENGTH_LONG).show();
                                        finish();
                                    }else{
                                        Toast.makeText(mContext,"手机发生存储异常！",Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                }
                            });

                        }else if("notOK".equals(response)){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mContext,"用户名或密码错误!",Toast.LENGTH_SHORT).show();
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
    // 注册按钮点击
    public void register(View v){
        MyUtils.toPage(mContext,RegisterActivity.class);
    }
}
