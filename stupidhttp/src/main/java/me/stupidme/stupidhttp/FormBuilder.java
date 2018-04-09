package me.stupidme.stupidhttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by allen on 18-4-9.
 */
public class FormBuilder {

    private static String buildURLParams(Map<String, String> pairs) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : pairs.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            try {
                builder.append(URLEncoder.encode(key, "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(value, "UTF-8"))
                        .append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (builder.toString().isEmpty()) {
            return builder.toString();
        }
        builder.insert(0, "?");
        return builder.substring(0, builder.length() - 1);
    }

    public static String buildURLForm(URLForm form) {
        String params = buildURLParams(form.pairs());
        System.out.println(params);
        return params;
    }

    public static String buildPostForm(PostForm form) {
        String encoding = form.encoding();
        if ("application/x-www-form-urlencoded".equals(encoding)) {
            String params = buildPostParams(form.pairs());
            System.out.println(params);
            return params;
        } else if ("application/json".equals(encoding)) {
            String params = buildPostParamsJson(form.pairs());
            System.out.println(params);
            return params;
        } else {
            return "";
        }
    }

    private static String buildPostParams(Map<String, String> pairs) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : pairs.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            try {
                builder.append(URLEncoder.encode(key, "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(value, "UTF-8"))
                        .append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String result = builder.toString();
        if (result.isEmpty()) {
            return result;
        }
        return result.substring(0, result.length() - 1);
    }

    private static String buildPostParamsJson(Map<String, String> pairs) {
        JSONObject object = new JSONObject(pairs);
        try {
            String result = object.toString(4);
            System.out.println(result);
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}
