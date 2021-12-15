package com.peer39.categorize;

import com.peer39.categorize.models.Category;
import com.peer39.categorize.models.CategoryKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Runner {

    private static WebCategorizor webCategorizer;

    @Autowired
    private WebCategorizor tWebCategorizer;

    @PostConstruct
    private void init(){
        Runner.webCategorizer = tWebCategorizer;
    }

    public static List<CategoryKeyword> initializeModel(List<String> keywords) {
        return keywords.stream().map(CategoryKeyword::new).collect(Collectors.toList());
    }

    public static Category initializeModel(String name, List<CategoryKeyword> keywords) {
        return new Category(name, keywords);
    }

    public static void run(List<Category> categories, List<String> urls){
        webCategorizer.init(categories, urls);
        webCategorizer.categorize();

        var categorizedUrls = webCategorizer.getCategorizedUrls();
        print(categorizedUrls);
    }

    private static void print(Map<String, HashSet<String>> categorizedUrls) {
        categorizedUrls.forEach((k, v) -> System.out.println("Category: " + k + " -> " + "Urls: " + v));
    }
}
