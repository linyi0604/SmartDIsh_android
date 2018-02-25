package smartdish.com.base.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import smartdish.com.R;
import smartdish.com.base.activity.LoginActivity;
import smartdish.com.base.activity.MainActivity;

/**
 * 该应用程序的工具类
 */
public class MyUtils {
    public static final String SP_NAME = "smartDish";
    public static final String IS_LOGIN = "isLogin";
    public static final String USERNAME = "username";
    public static final String PASSWORD= "password";
    public static final String NAME= "name";
    public static final String PHONE= "phone";
    public static final String DETAIL= "detail";

    // 检查是否已经登录
    public static Boolean isLogin(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME,0);
        Boolean result = sp.getBoolean(IS_LOGIN,false);
        return result;
    }
    // 设置已经登录的状态
    public static boolean setLogin(Context mContext,String username, String password){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME,0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(USERNAME,username);
        editor.putString(PASSWORD,password);
        Boolean result = editor.commit();
        return result;
    }

    // 更新用户信息
    public static Boolean updateUserInfo(Context mContext, String password, String name, String phone, String detail) {
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME,0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(NAME,name);
        editor.putString(PASSWORD,password);
        editor.putString(PHONE,phone);
        editor.putString(DETAIL,detail);
        Boolean result = editor.commit();
        return result;
    }


    // 退出登录功能
    public static boolean logout(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME,0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(IS_LOGIN,false);
        editor.clear();
        Boolean result = editor.commit();
        return result;
    }

    // 跳转到登录页面
    public static void toLoginPage(Context mContent){
        Intent intent = new Intent(mContent, LoginActivity.class);
        mContent.startActivity(intent);
    }
    // 跳转到指定页面
    public static void toPage(Context mContext, Class cls){
        Intent intent = new Intent(mContext,cls);
        mContext.startActivity(intent);
    }

    // 获取已经登录的用户的用户名
    public static String getUsername(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME,0);
        String username = sp.getString(USERNAME,"");
        return username;
    }

    // 获取与服务器交互的url
    public static String getBaseUrl(Context mContext){
        return mContext.getString(R.string.base_url);
    }
    // 获取 手机端app的url后缀
    public static String getAppUrl(Context mContext){
        return mContext.getString(R.string.appUrl);
    }



}
