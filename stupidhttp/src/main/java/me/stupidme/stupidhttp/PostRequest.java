package me.stupidme.stupidhttp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by allen on 18-4-9.
 */
public class PostRequest extends Request {

    private PostForm mForm;

    public PostRequest(String url) {
        super(url);
    }

    public PostRequest(String url, PostForm form) {
        super(url);
        mForm = form;
    }

    public PostRequest(String url, PostForm form, int readTimeout, int connectTimeout) {
        super(url, readTimeout, connectTimeout);
        mForm = form;
    }

    @Override
    public HttpURLConnection getConnection() throws IOException {
        URL url = new URL(getUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(getReadTimeout());
        connection.setConnectTimeout(getConnectTimeout());
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", mForm.encoding() + ";charset=utf-8");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write(FormBuilder.buildPostForm(mForm));
        writer.flush();
        writer.close();
        outputStream.close();
        return connection;
    }

}
