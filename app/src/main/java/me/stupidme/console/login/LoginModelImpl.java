package me.stupidme.console.login;

import me.stupidme.console.utils.LoggerProxy;
import me.stupidme.stupidhttp.HttpRequest;
import me.stupidme.stupidhttp.RequestCallback;
import me.stupidme.stupidhttp.StupidHttp;

/**
 * Created by allen on 18-3-28.
 */
public class LoginModelImpl implements LoginModel {

    @Override
    public boolean login(String username, String password) {
        UserInfoItem infoItem = new UserInfoItem(username, password);
        HttpRequest request = new HttpRequest.Builder()
                .method("POST")
                .url("http://127.0.0.1:80/login")
                .postRequestForm(infoItem)
                .build();
        final boolean[] success = {false};
        RequestCallback callback = new RequestCallback() {
            @Override
            public void onException(Exception e) {
                LoggerProxy.e(LoginModelImpl.class.getCanonicalName(), e.getMessage());
                success[0] = false;
            }

            @Override
            public void onSuccess(String response) {
                success[0] = true;
            }
        };
        StupidHttp.getInstance().go(request, callback);
        //just for test.
        return true;
//        return success[0];
    }
}
