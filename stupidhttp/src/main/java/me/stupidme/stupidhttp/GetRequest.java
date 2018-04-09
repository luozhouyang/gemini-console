package me.stupidme.stupidhttp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by allen on 18-4-9.
 */
public class GetRequest extends Request {

    private URLForm mForm;

    public GetRequest(String url) {
        super(url);
    }

    public GetRequest(String url, URLForm form) {
        super(url);
        mForm = form;
    }

    public GetRequest(String url, URLForm form, int readTimeout, int connectTimeout) {
        super(url, readTimeout, connectTimeout);
        mForm = form;
    }

    @Override
    public HttpURLConnection getConnection() throws IOException {
        URL url = new URL(getUrl() + FormBuilder.buildURLForm(mForm));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(getReadTimeout());
        connection.setConnectTimeout(getConnectTimeout());
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", mForm.encoding() + ";charset=utf-8");
        connection.setDoInput(true);
        return connection;
    }

}
