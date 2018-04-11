package me.stupidme.console.login;

import me.stupidme.console.account.UserInfoItem;
import me.stupidme.stupidhttp.PostRequest;
import me.stupidme.stupidhttp.Request;
import me.stupidme.stupidhttp.RequestCallback;
import me.stupidme.stupidhttp.StupidHttp;

/**
 * Created by allen on 18-3-28.
 */
class LoginPresenter {

    private LoginView mView;

    LoginPresenter(LoginView view) {
        mView = view;
    }

    public void login(String username, String email, String password) {
        UserInfoItem userInfoItem = new UserInfoItem(username, email, password);
        Request request = new PostRequest("http://127.0.0.1:8080", userInfoItem);
        RequestCallback callback = new RequestCallback() {
            @Override
            public void onException(Exception e) {
                mView.loginFailed();
            }

            @Override
            public void onSuccess(String response) {
                mView.loginSuccess();
            }
        };
        StupidHttp.getInstance().go(request, callback);
    }
}
