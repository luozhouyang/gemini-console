package me.stupidme.console.login;

import android.util.ArrayMap;

import java.util.Map;

import me.stupidme.stupidhttp.PostForm;

/**
 * Created by allen on 18-3-28.
 */
public class UserInfoItem implements PostForm {

    private String username;
    private String password;

    public UserInfoItem(String name, String password) {
        this.username = name;
        this.password = password;
    }

    @Override
    public Map<String, String> pairs() {
        Map<String, String> map = new ArrayMap<>(2);
        map.put("username", username);
        map.put("password", password);
        return map;
    }
}
