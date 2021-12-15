package com.peer39.categorize.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.regex.Pattern;

import lombok.Data;
import lombok.NonNull;

@Data
public class Category {

	private static final int MAX_KEYWORDS_FOR_CATEGORY = 1000;
	
	private String name;
	private PriorityBlockingQueue<KeywordData> keywords;
	private Set<String> keywordsPile;

	public Category(@NonNull String name, @NonNull List<CategoryKeyword> keywords) {
		validate(keywords);
		this.name = name;
		initQueue(keywords);
	}

	private void initQueue(List<CategoryKeyword> keywords) {
		keywordsPile = new HashSet<>();
		this.keywords = new PriorityBlockingQueue<>();
		keywords.forEach(this::addKeyWord);
	}

	private boolean isNotExistInCategory(CategoryKeyword keyword) {
		return keywordsPile.add(keyword.getPhrase());
	}

	public void setKeywords(@NonNull List<CategoryKeyword> keywords) {
		validate(keywords);
		initQueue(keywords);
	}
	
	public void addKeyWord(@NonNull CategoryKeyword keyword) {
		validate();
		if(isNotExistInCategory(keyword))
			keywords.add(new KeywordData(keyword));
	}

	public boolean isAssociated(String text){
		//return keywords.stream().anyMatch(keyword -> text.matches(keyword.keywordRegex));
		return keywords.stream().anyMatch(keyword -> keyword.pattern.matcher(text).matches());
	}

	private void validate() {
		if(keywords.size() == MAX_KEYWORDS_FOR_CATEGORY)
			throw new RuntimeException("Category reached max keywords capacity");
	}

	private void validate(List<CategoryKeyword> keywords) {
		if(keywords.size() > MAX_KEYWORDS_FOR_CATEGORY)
			throw new RuntimeException("Number of keywords is bigger than category max keywords capacity");
	}

	private static class KeywordData implements Comparable<KeywordData> {
		static final String PHRASE_REGEX = "(?i)(?s).*\\b%s\\b.*";

		String keywordRegex;
		int numOfWords;
		int length;
		Pattern pattern;

		public KeywordData(CategoryKeyword keyword){
			String phrase = keyword.getPhrase();
			keywordRegex = String.format(PHRASE_REGEX, phrase);
			pattern = Pattern.compile(keywordRegex);
			numOfWords = keyword.getNumOfWords();
			length = phrase.length();
		}

		@Override
		public int compareTo(KeywordData o) {
			if(this.numOfWords == o.numOfWords)
				return Integer.compare(this.length, o.length);

			return Integer.compare(this.numOfWords, o.numOfWords);
		}
	}
}
