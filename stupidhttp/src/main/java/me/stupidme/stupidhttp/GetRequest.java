package me.stupidme.stupidhttp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by allen on 18-4-9.
 */
public class GetRequest extends Request {

    private String urlForm;

    public GetRequest(String url) {
        super(url);
    }

    public GetRequest(String url, URLForm form) {
        super(url);
        urlForm = FormBuilder.buildURLForm(form);
    }

    public GetRequest(String url, URLForm form, int readTimeout, int connectTimeout) {
        super(url, readTimeout, connectTimeout);
        urlForm = FormBuilder.buildURLForm(form);
    }

    public String getUrlForm() {
        return urlForm;
    }

    @Override
    public HttpURLConnection getConnection() throws IOException {
        URL url = new URL(getUrl() + urlForm);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(getReadTimeout());
        connection.setConnectTimeout(getConnectTimeout());
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        return connection;
    }

}
