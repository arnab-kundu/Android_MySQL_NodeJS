package com.example.akundu.android_mysql_nodejs.network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.akundu.android_mysql_nodejs.pojo.Response;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import javax.net.ssl.SSLHandshakeException;

public class NetworkTask extends AsyncTask<String, Void, Response> {

    API api_name;
    private ApiResponseListener apiResponseListener;
    int responseCode = 0;

    public NetworkTask(ApiResponseListener apiResponseListener, API api_name) {
        this.apiResponseListener = apiResponseListener;
        this.api_name = api_name;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Response doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0].replaceAll(" ", "%20"));
            Log.d("msg Request", "" + url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(7000);
            responseCode = httpURLConnection.getResponseCode();
            Log.d("msg ResponseCode", "" + httpURLConnection.getResponseCode());
            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            return new Response(scanner.hasNext() ? scanner.next() : "", api_name);
            //return scanner.hasNext() ? scanner.next() : "";
        } catch (MalformedURLException e) {
            Log.e("msg MalformedURL", "" + e);
            return new Response("MalformedURLException", api_name);
        } catch (ConnectException e) {
            Log.e("msg ConnectException", "No Internet/WIFI" + e);
            return new Response("ConnectException", api_name);
        } catch (SSLHandshakeException e) {
            Log.e("msg SSLHandshake", "Restricted the request by Firewall" + e);
            return new Response("SSLHandshakeException", api_name);
        } catch (FileNotFoundException e) {
            Log.e("msg FileNotFoundExcepti", "Error code 500/404 comes under this exception" + e);
            Log.e("msg ResponseCode",""+responseCode);
            return new Response("FileNotFoundException", api_name);
        } catch (EOFException e) {
            Log.e("msg EOFException", "" + e);
            return new Response("EOFException", api_name);
        } catch (SocketException e) {
            Log.e("msg SocketException", "" + e);
            return new Response("SocketException", api_name);
        } catch (SocketTimeoutException e) {
            Log.e("msg SocketTimeoutExcept", "" + e);
            return new Response("SocketTimeoutException", api_name);
        }catch (IOException e) {
            Log.e("msg IOException", "" + e);
            return new Response("IOException", api_name);
        }catch (Exception e) {
            Log.e("msg Exception", "" + e);
            return new Response("Exception", api_name);
        }
    }

    @Override
    protected void onPostExecute(Response res) {
        super.onPostExecute(res);
        Log.d("msg Response", res.response);
        apiResponseListener.onResponse(res);
    }
}
