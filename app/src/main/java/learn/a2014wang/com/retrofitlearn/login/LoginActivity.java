package learn.a2014wang.com.retrofitlearn.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import learn.a2014wang.com.retrofitlearn.R;
import learn.a2014wang.com.retrofitlearn.login.presenter.ILoginPresenter;
import learn.a2014wang.com.retrofitlearn.login.presenter.LoginPersenterImpl;
import learn.a2014wang.com.retrofitlearn.login.view.LoginView;
import learn.a2014wang.com.retrofitlearn.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener{
    private ProgressDialog progressDialog;
    private ILoginPresenter loginPresenter;
    private EditText etname;
    private EditText etpswd;
    private Button loginbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.loginbut = (Button) findViewById(R.id.loginbut);
        this.etpswd = (EditText) findViewById(R.id.etpswd);
        this.etname = (EditText) findViewById(R.id.etname);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在登录...");
        progressDialog.setTitle("提示");
        loginPresenter = new LoginPersenterImpl(this);
        loginbut.setOnClickListener(this);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showError() {
        Toast.makeText(this, "登录名或密码错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.loginbut){
           String uname =  etname.getText().toString();
            String pswd = etpswd.getText().toString();
            loginPresenter.validateInfomation(uname,pswd);
         }
    }
}
