package com.leelit.android_mvp.login;

/**
 * Created by Leelit on 2016/3/6.
 */
public interface ILoginView {

    void usernameIllegal();

    void passwordIllegal();

    void showProgress();

    void dismissProgress();

    void loginSuccessfully();

    void loginUnsuccessfully();

    void clearState();

}
