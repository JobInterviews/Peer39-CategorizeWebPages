package com.peer39.categorize;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.peer39.categorize.models.Category;
import com.peer39.categorize.models.CategoryKeyword;
import com.peer39.categorize.services.TextService;

@SpringBootApplication
public class Peer39CategorizeWebPagesApplication implements CommandLineRunner{

	@Autowired
	private TextService service;

	public static void main(String[] args) {
		SpringApplication.run(Peer39CategorizeWebPagesApplication.class, args);
	}
	
	
    @Override
    public void run(String... args) throws Exception {
		List<String> keywordsPhrases = List.of("star war", "starwars", "starwar", "starwars", "r2d2", "may the force be with you");

		List<CategoryKeyword> keywords = Runner.initializeModel(keywordsPhrases);
		Category c1 = Runner.initializeModel("Star Wars", keywords);

		keywordsPhrases = List.of("basketball", "nba", "ncaa", "lebron james", "john stokton", "anthony davis");
		keywords = Runner.initializeModel(keywordsPhrases);
		Category c2 = Runner.initializeModel("Basketball", keywords);

		String url1 = "http://www.msn.com/en-nz/travel/tripideas/70-of-the-planets-most-breathtaking-sightsss-AAIUpDp";
		String url2 = "https://www.radiosport.co.nz/sport-news/rugby/accident-or-one-last-dig-eddie-jones-reveals-hansens-next-job/";
		String url3 = "https://www.glamour.de/frisuren/frisurenberatung/haarschnitte";
		String url4 = "https://www.bbc.com";
		String url5 = "https://www3.forbes.com/business/2020-upcoming-hottest-new-vehicles/13/?nowelcome";
		String url6 = "https://www.tvblog.it/post/1681999/valerio-fabrizio-salvatori-gli-inseparabili-chi-sono-pechino-express-2020";
		String url7 = "http://edition.cnn.com/";
		String url8 = "https://www.starwars.com/news/everything-we-know-about-the-mandalorian";

		List<String> urls = List.of(url1, url2, url3, url4, url5, url6, url7, url8);

		Runner.run(List.of(c1, c2), urls);
    }
}
