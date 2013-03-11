package com.demo.sitemaps;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class SiteMap {

    private URL url;

    private ArrayList<SiteMapUrl> urlList;

    public SiteMap() {
        urlList = new ArrayList<SiteMapUrl>();
    }

    public SiteMap(String url) {
        setUrl(url);
        urlList = new ArrayList<SiteMapUrl>();
    }

    public void parse() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try  {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(url.openStream());
            NodeList nodeList = doc.getElementsByTagName("url");
            for (int count = 0; count < nodeList.getLength(); count++) {
                Node nNode = nodeList.item(count);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    NodeList nameNodeList = eElement.getElementsByTagName("loc");
                    for (int eIndex = 0; eIndex < nameNodeList.getLength(); eIndex++) {
                        if (nameNodeList.item(eIndex).getNodeType() == Node.ELEMENT_NODE) {
                            Element nameElement = (Element) nameNodeList.item(eIndex);
                            String url = nameElement.getFirstChild().getNodeValue().trim();
                            SiteMapUrl siteMapUrl = new SiteMapUrl(url);
                            addUrl(siteMapUrl);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUrl(String url) {
        try {
            this.url = new URL(url);
        }
        catch (MalformedURLException e) {
            this.url = null;
        }
    }

    public ArrayList<SiteMapUrl> getUrlList() {
        return urlList;
    }

    protected void addUrl(SiteMapUrl url) {
        urlList.add(url);
    }
}
