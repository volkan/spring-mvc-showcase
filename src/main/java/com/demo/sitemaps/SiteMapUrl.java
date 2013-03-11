package com.demo.sitemaps;

import java.net.MalformedURLException;
import java.net.URL;

public class SiteMapUrl {

    private URL url;

    private boolean isVisit = false;

    public SiteMapUrl(String url) {
        setUrl(url);
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void setUrl(String url) {
        try {
            this.url = new URL(url);
        }
        catch (MalformedURLException e) {
            this.url = null;
        }
    }

    public boolean isVisit() {
        return isVisit;
    }

    public void setVisit(boolean visit) {
        isVisit = visit;
    }
}
