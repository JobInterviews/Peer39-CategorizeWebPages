package com.peer39.categorize.services;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.peer39.categorize.suppliers.TextSupplier;

@Slf4j
@Service
public class TextService {

	@Autowired
	@Qualifier("webTextSupplier")
	TextSupplier textSupplier;
	
	public Map<String, String> fetchUrlsText(List<String> urls){
		return urls.stream()
				.map(this::process)
				.flatMap(Optional::stream)
				.collect((toMap(Pair::getLeft, Pair::getRight)));
	}

	private Optional<Pair<String, String>> process(String url){
		try{
			return Optional.of(new ImmutablePair<String, String>(url, textSupplier.fetchText(url)));
		} catch (Exception e ){
			log.error(e.getMessage());
		}
		return Optional.empty();
	}
}
