package learn.a2014wang.com.retrofitlearn.login.presenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import learn.a2014wang.com.retrofitlearn.R;

public interface ILoginPresenter {
  public void  validateInfomation(String uname,String pswd);
  public void Destory();
}
