package learn.a2014wang.com.retrofitlearn.login.presenter;

import learn.a2014wang.com.retrofitlearn.login.model.ILoginInteractor;
import learn.a2014wang.com.retrofitlearn.login.model.LoginInteractorImpl;
import learn.a2014wang.com.retrofitlearn.login.view.LoginView;
import learn.a2014wang.com.retrofitlearn.login.lisenter.OnLoginFinishListener;

/**
 * Created by 2014wang on 2016/7/30   @desc
 */
public class LoginPersenterImpl implements ILoginPresenter, OnLoginFinishListener {
    private LoginView loginview;
    private ILoginInteractor loginInteractor;

    public LoginPersenterImpl(LoginView loginView) {
      this.loginview = loginView;
        loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void validateInfomation(String uname, String pswd) {
        if (loginview != null) {
            loginInteractor.login(this, uname, pswd);
            loginview.showProgress();
        }
    }

    @Override
    public void Destory() {
        loginview = null;
    }

    @Override
    public void onError() {
        loginview.hideProgress();
        loginview.showError();
    }

    @Override
    public void onSuccess() {
        loginview.hideProgress();
        loginview.navigateToHome();
    }
}
