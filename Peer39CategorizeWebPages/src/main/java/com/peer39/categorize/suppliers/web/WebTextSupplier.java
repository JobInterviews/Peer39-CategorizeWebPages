package com.peer39.categorize.suppliers.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peer39.categorize.suppliers.TextSupplier;
@Component
public class WebTextSupplier implements TextSupplier{
	
	public String fetchText(String url) throws IOException {
		String html = Jsoup.connect(url).get().html();
		return Jsoup.parse(html).text();
	}

}
