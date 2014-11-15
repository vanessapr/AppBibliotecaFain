package com.vanessapr.appbibliotecafain.utils;

import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Milagros on 15/11/2014.
 */
public class HttpConnection {
    private static final String TAG = "HttpConnection";
    private int fileLength;
    private HttpURLConnection httpConn = null;

    public InputStream open(String urlString) throws Exception {
        InputStream in;

        try {
            URL url = new URL(urlString);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            if(httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                fileLength = httpConn.getContentLength();
                in = httpConn.getInputStream();
            } else {
                Log.e(TAG, "open: No se pudo establecer la conexion con el servidor");
                throw  new Exception("No se pudo establecer la conexión con el servidor");
            }

        } catch (Exception e) {
            Log.e(TAG, "open: " + e.getLocalizedMessage());
            throw new Exception("Error en la conexión. Intente nuevamente");
        }

        return in;
    }

    public int getFileLength() {
        return fileLength;
    }

    public HttpURLConnection getHttpConn() {
        return httpConn;
    }
}
