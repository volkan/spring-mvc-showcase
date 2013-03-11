package com.demo.sitemaps;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.*;

public class MyTask implements Runnable {
    public static final int maxCalls = 10;
    public static final int sleepMillis = 10;
    private HttpResponse response;
    private HttpClient httpclient;

    public void run(){
        int counter = 0;

        while (true) {

            if (counter >= maxCalls) {
                break;
            }
            try {
                Thread.currentThread().sleep(sleepMillis);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            execHttpRequest();

            ++counter;
        }
    }

    private void execHttpRequest() {
        httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://localhost:8081/resources/sitemap.xml");

        try {

            response = httpclient.execute(httpget);

            httpclient.getConnectionManager().shutdown();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{

            httpclient.getConnectionManager().shutdown();
        }

    }


}