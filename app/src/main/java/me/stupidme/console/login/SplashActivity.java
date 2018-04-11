package me.stupidme.console.login;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.stupidme.console.R;
import me.stupidme.console.account.UserInfoItem;
import me.stupidme.console.account.UserInfoProvider;
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

        LoggerProxy.i("SplashActivity", "onCreate()");

        Uri uri = UserInfoProvider.USER_INFO_URI;
        Cursor cursor = getContentResolver().query(uri, new String[]{"username", "email", "password"},
                null, null, null);
        if (cursor == null) {
            System.out.println("Cursor is null...");
            LoggerProxy.w("SplashActivity", "Cursor is null. Can't get user info. ");
            turnToLoginActivity();
            return;
        }
        if (cursor.getCount() <= 0) {
            cursor.close();
            LoggerProxy.w("SplashActivity", "Cursor size is 0. Can't get user info. ");
            turnToLoginActivity();
            return;
        }
        cursor.moveToLast();
        String name = cursor.getString(0);
        String email = cursor.getString(1);
        String password = cursor.getString(2);
        cursor.close();
        LoggerProxy.i("SplashActivity", name + "\t" + email + "\t" + password);

        if (password.isEmpty()) {
            LoggerProxy.e(SplashActivity.class.getCanonicalName(),
                    "Account is empty.");
            turnToLoginActivity();
            return;
        }

        UserInfoItem infoItem = new UserInfoItem(name, email, password);
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
