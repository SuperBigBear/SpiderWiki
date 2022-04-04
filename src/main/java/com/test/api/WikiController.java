package com.test.api;

import com.test.config.JsoupUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.sampled.AudioFormat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping(value = "/api")
public class WikiController {
    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        String html = JsoupUtils.catchWiki("https://en.wikipedia.org/wiki/Heidenheim_an_der_Brenz");
        return html;
    }


}
