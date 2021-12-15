package com.peer39.categorize.models;

import lombok.NonNull;
import lombok.Data;

@Data
public class CategoryKeyword {

	private static final String DELIMITER = " ";
	private static final int MAX_WORDS_FOR_KEYWORD = 6;
	
	private String phrase;
	private int numOfWords;
	
	public CategoryKeyword(@NonNull String phrase) {
		setPhrase(phrase);
	}
	
	public void setPhrase(String phrase) {
		validate(phrase);
		this.numOfWords = phrase.split(DELIMITER).length;
		this.phrase = phrase;		
	}

	private void validate(String phrase) {
		if(phrase.isBlank() || phrase.split(DELIMITER).length > MAX_WORDS_FOR_KEYWORD)
			throw new IllegalArgumentException("Phrase is empty, or over max words for keyword");
	}
}
