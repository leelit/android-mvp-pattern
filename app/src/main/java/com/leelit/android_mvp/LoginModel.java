package com.leelit.android_mvp;

import android.os.Handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Leelit on 2016/3/6.
 */
public class LoginModel {

    public static Map<String, String> maps = new HashMap<>();
    private Handler mHandler = new Handler();

    static {
        maps.put("key1", "password1");
        maps.put("key2", "password2");
        maps.put("key3", "password3");
    }

    public void getPassword(final String username, final OnGetPasswordListener listener) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String password = maps.get(username);
                if (password == null) {
                    listener.onGetError();
                } else {
                    listener.onGet(password);
                }
            }
        }, new Random().nextInt(2000));
    }

    public void addUser(final String username, final String password, final OnRegisterListener registerListener) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (LoginModel.maps.containsKey(username)) {
                        registerListener.onUserExist();
                    } else {
                        LoginModel.maps.put(username, password);
                        registerListener.onSuccessful();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    registerListener.onNetError();
                }
            }
        }, new Random().nextInt(2000));
    }

    public interface OnGetPasswordListener {
        void onGet(String password);

        void onGetError();
    }

    public interface OnRegisterListener {
        void onSuccessful();

        void onUserExist();

        void onNetError();
    }
}
