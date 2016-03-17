package com.leelit.android_mvp.register;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.leelit.android_mvp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {

    @InjectView(R.id.et_username)
    EditText mEtUsername;
    @InjectView(R.id.et_password)
    EditText mEtPassword;
    @InjectView(R.id.et_password_confirm)
    EditText mEtPasswordConfirm;
    @InjectView(R.id.btn_register)
    Button mBtnRegister;
    @InjectView(R.id.btn_clear)
    Button mBtnClear;

    private ProgressDialog mProgressDialog;
    private RegisterPresenter mRegisterPresenter = new RegisterPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        mProgressDialog = new ProgressDialog(this);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegisterPresenter.doRegister(mEtUsername.getText().toString(), mEtPassword.getText().toString(), mEtPasswordConfirm.getText().toString());
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegisterPresenter.clearState();
            }
        });
    }

    @Override
    public void usernameIllegal() {
        mEtUsername.setError("username should not be empty");
    }

    @Override
    public void passwordIllegal() {
        mEtPassword.setError("password should not be empty");
    }

    @Override
    public void confirmPasswordIllegal() {
        mEtPasswordConfirm.setError("confirmPassword should not be empty");
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void dismissProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public void registerSuccessfully() {
        Toast.makeText(RegisterActivity.this, "register successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void netError() {
        Toast.makeText(RegisterActivity.this, "net error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void usernameExist() {
        Toast.makeText(RegisterActivity.this, "username already exists", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void passwordNotSame() {
        Toast.makeText(RegisterActivity.this, "passwords are not the same", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearState() {
        mEtUsername.getText().clear();
        mEtPassword.getText().clear();
        mEtPasswordConfirm.getText().clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRegisterPresenter.doDestroy();
    }
}
