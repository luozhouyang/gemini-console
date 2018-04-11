package me.stupidme.console.account;

import android.util.ArrayMap;

import java.util.Map;

import me.stupidme.stupidhttp.PostForm;

/**
 * Created by allen on 18-3-28.
 */
public class UserInfoItem implements PostForm {

    private String username;
    private String email;
    private String password;

    public UserInfoItem(String name, String email, String password) {
        this.username = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public Map<String, String> pairs() {
        Map<String, String> map = new ArrayMap<>(2);
        map.put("name", username);
        map.put("email", email);
        map.put("password", password);
        return map;
    }

    @Override
    public String encoding() {
        return "application/x-www-form-urlencoded";
    }
}
