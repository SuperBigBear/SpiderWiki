package com.test.config;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtils {
    
    public static String catchWiki(String url) {
        String html = "";
        try {
            Document doc = Jsoup.connect(url).get();
            html += "<h1>" + doc.title() + "</h1><hr/>";
            //Elements newsHeadlines = doc.select("#firstHeading");
            Element img = doc.select("tr.mergedtoprow").first();
            html += img.outerHtml() + "<br/>";
            Element body = doc.getElementById("mw-content-text");
            Elements paras = body.select("div.mw-parser-output > h3,p");
            for (Element p : paras) {
                html += p.outerHtml();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error";
        }
        return html;
    }
}
