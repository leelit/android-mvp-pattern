package com.leelit.android_mvp.register;

/**
 * Created by Leelit on 2016/3/7.
 */
public interface IRegisterView {

    void usernameIllegal();

    void passwordIllegal();

    void confirmPasswordIllegal();

    void showProgress();

    void dismissProgress();

    void registerSuccessfully();

    void netError();

    void usernameExist();

    void passwordNotSame();

    void clearState();

}
