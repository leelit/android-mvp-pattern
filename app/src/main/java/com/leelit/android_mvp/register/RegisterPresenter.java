package com.leelit.android_mvp.register;

import com.leelit.android_mvp.LoginModel;

/**
 * Created by Leelit on 2016/3/7.
 */
public class RegisterPresenter {

    private IRegisterView mRegisterView;
    private LoginModel mModel;

    public RegisterPresenter(IRegisterView registerView) {
        mRegisterView = registerView;
        mModel = new LoginModel();
    }

    public void doRegister(String username, String password, String confirmPassword) {
        if (username.equals("")) {
            mRegisterView.usernameIllegal();
            return;
        }
        if (password.equals("")) {
            mRegisterView.passwordIllegal();
            return;
        }
        if (confirmPassword.equals("")) {
            mRegisterView.confirmPasswordIllegal();
            return;
        }
        if (!password.equals(confirmPassword)) {
            mRegisterView.passwordNotSame();
            return;
        }
        mRegisterView.showProgress();
        mModel.addUser(username, password, new LoginModel.OnRegisterListener() {
            @Override
            public void onSuccessful() {
                mRegisterView.registerSuccessfully();
                mRegisterView.dismissProgress();
            }

            @Override
            public void onUserExist() {
                mRegisterView.usernameExist();
                mRegisterView.dismissProgress();
            }

            @Override
            public void onNetError() {
                mRegisterView.netError();
                mRegisterView.dismissProgress();
            }
        });
    }

    public void clearState() {
        mRegisterView.clearState();
    }
}
