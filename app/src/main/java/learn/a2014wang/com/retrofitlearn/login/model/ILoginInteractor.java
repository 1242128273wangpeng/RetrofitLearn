package learn.a2014wang.com.retrofitlearn.login.model;

import learn.a2014wang.com.retrofitlearn.login.lisenter.OnLoginFinishListener;

/**
 * Created by 2014wang on 2016/7/30   @desc
 */
public interface ILoginInteractor {
    public void login(OnLoginFinishListener onLoginFinishListener, String uname, String pswd);
}
