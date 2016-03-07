package com.leelit.android_mvp.login;

import com.leelit.android_mvp.LoginModel;

/**
 * Created by Leelit on 2016/3/6.
 */
public class LoginPresenter {

    private ILoginView mLoginView;

    private LoginModel mModel = new LoginModel();

    public LoginPresenter(ILoginView loginView) {
        mLoginView = loginView;
    }


    public void doLogin(String inputUsername, final String inputPassword) {
        if (inputUsername.equals("")) {
            mLoginView.usernameIllegal();
            return;
        }
        if (inputPassword.equals("")) {
            mLoginView.passwordIllegal();
            return;
        }
        mLoginView.showProgress();
        mModel.getPassword(inputUsername, new LoginModel.OnGetPasswordListener() {
            public void onGet(String password) {
                if (inputPassword.equals(password)) {
                    mLoginView.loginSuccessfully();
                } else {
                    mLoginView.loginUnsuccessfully();
                }
                mLoginView.dismissProgress();
            }

            public void onGetError() {
                mLoginView.loginUnsuccessfully();
                mLoginView.dismissProgress();
            }
        });
    }

    public void doClear() {
        mLoginView.clearState();
    }
}
