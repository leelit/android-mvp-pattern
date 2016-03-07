package com.leelit.android_mvp.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.leelit.android_mvp.R;
import com.leelit.android_mvp.register.RegisterActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @InjectView(R.id.et_username)
    EditText mEtUsername;
    @InjectView(R.id.et_password)
    EditText mEtPassword;
    @InjectView(R.id.btn_login)
    Button mBtnLogin;
    @InjectView(R.id.btn_clear)
    Button mBtnClear;
    private ProgressDialog mProgressDialog;

    private LoginPresenter mLoginPresenter = new LoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mProgressDialog = new ProgressDialog(this);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.doLogin(mEtUsername.getText().toString(), mEtPassword.getText().toString());
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.doClear();
            }
        });
    }

    @Override
    public void usernameIllegal() {
        mEtUsername.setError("should not be empty");
    }

    @Override
    public void passwordIllegal() {
        mEtPassword.setError("should not be empty");
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
    public void loginSuccessfully() {
        Toast.makeText(LoginActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginUnsuccessfully() {
        Toast.makeText(LoginActivity.this, "login unsuccessfully, please check", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearState() {
        mEtUsername.getText().clear();
        mEtPassword.getText().clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, RegisterActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
