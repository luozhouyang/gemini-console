package me.stupidme.console.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.stupidme.console.R;
import me.stupidme.console.account.Account;
import me.stupidme.console.account.AccountManager;
import me.stupidme.console.account.AccountManagerImpl;
import me.stupidme.console.main.MainActivity;
import me.stupidme.console.utils.LoggerProxy;
import me.stupidme.stupidhttp.PostRequest;
import me.stupidme.stupidhttp.Request;
import me.stupidme.stupidhttp.RequestCallback;
import me.stupidme.stupidhttp.StupidHttp;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        AccountManager manager = AccountManagerImpl.getInstance();
        Account account = manager.getAccount(this);
        if (account == null) {
            LoggerProxy.e(SplashActivity.class.getCanonicalName(),
                    "Account is null.");
            turnToLoginActivity();
            return;
        }
        String name = account.getUserName();
        String password = account.getPassword();
        if (name.isEmpty() || password.isEmpty()) {
            LoggerProxy.e(SplashActivity.class.getCanonicalName(),
                    "Account is empty.");
            turnToLoginActivity();
            return;
        }

        UserInfoItem infoItem = new UserInfoItem(name, password);
        Request request = new PostRequest("http://127.0.0.1:8080", infoItem);
        RequestCallback callback = new RequestCallback() {
            @Override
            public void onException(Exception e) {
                LoggerProxy.e(SplashActivity.class.getCanonicalName(),
                        "Login failed.");
                turnToLoginActivity();
            }

            @Override
            public void onSuccess(String response) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        };
        StupidHttp.getInstance().go(request, callback);
    }

    private void turnToLoginActivity() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }
}
