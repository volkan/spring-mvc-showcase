package org.springframework.samples.mvc.simple;

import com.demo.sitemaps.MyTask;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.demo.sitemaps.SiteMapUrl;
import java.util.ArrayList;
import com.demo.sitemaps.SiteMap;

@Controller
public class SimpleController {

	@RequestMapping("/simple")
	public @ResponseBody String simple() {
		return "Hello world!";
	}

    @RequestMapping("/cachewarmup")
    public @ResponseBody String xmlread() {
        String loc = "";
        int nThreads = 2;

        try {
            String url = "http://localhost:8081/resources/sitemap.xml";

            ExecutorService execute = Executors.newFixedThreadPool(nThreads);

            for (int i = 0; i < nThreads; ++i) {
                execute.execute(new MyTask());
            }

            execute.awaitTermination(1000, TimeUnit.MILLISECONDS);

            execute.shutdown();

            /*
            SiteMap sm = new SiteMap();
            sm.setUrl(url);
            sm.parse();
            ArrayList<SiteMapUrl> urlList = sm.getUrlList();

            for (int count = 0; count < urlList.size(); count++) {
                String s = "1";
            }*/

            /*
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL(url).openStream());
            NodeList nodeList = doc.getElementsByTagName("url");

            for (int count = 0; count < nodeList.getLength(); count++) {
                Node nNode = nodeList.item(count);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    NodeList nameNodeList = eElement.getElementsByTagName("loc");
                    for (int eIndex = 0; eIndex < nameNodeList.getLength(); eIndex++) {
                        if (nameNodeList.item(eIndex).getNodeType() == Node.ELEMENT_NODE) {
                            Element nameElement = (Element) nameNodeList.item(eIndex);
                            loc = loc.concat(nameElement.getFirstChild().getNodeValue().trim());
                            loc = loc.concat("<br>");
                        }
                    }
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }


        return loc.toString();
    }

}
