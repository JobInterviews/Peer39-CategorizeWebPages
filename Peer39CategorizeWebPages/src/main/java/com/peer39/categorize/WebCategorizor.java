package com.peer39.categorize;

import java.util.*;

import com.peer39.categorize.services.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peer39.categorize.models.Category;

@Service
public class WebCategorizor {
	
	@Autowired
	private TextService service;

	private List<Category> categories;
	private List<String> urls;

	public Map<String, HashSet<String>> categorizedUrls = new HashMap<>();
	
	
	public void init(List<Category> categories, List<String> urls) {
		this.categories = categories;
		this.urls = urls;
	}


	public void categorize() {
		Map<String, String> urlToText = service.fetchUrlsText(urls);

		urlToText.forEach((url, text) -> {
			categories.stream().parallel().forEach(category -> {
				if (category.isAssociated(text))
					categorizedUrls.computeIfAbsent(category.getName(), (key) -> new HashSet<String>()).add(url);
			});
		});
	}

	public Map<String, HashSet<String>> getCategorizedUrls(){
		return categorizedUrls;
	}
}
