package learn.a2014wang.com.retrofitlearn.login.model;

import android.os.Handler;
import android.text.TextUtils;

import learn.a2014wang.com.retrofitlearn.login.lisenter.OnLoginFinishListener;

/**
 * Created by 2014wang on 2016/7/30   @desc
 */
public class LoginInteractorImpl implements ILoginInteractor {
    @Override
    public void login(final OnLoginFinishListener listener, final String uname, final String pswd) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(uname) || TextUtils.isEmpty(pswd)) {
                    listener.onError();
                } else if (uname.length() < 3 || pswd.length() < 3) {
                    listener.onError();
                } else {
                    listener.onSuccess();
                }
            }
        },2000);
    }
}
