package com.peer39.categorize.suppliers;

import java.io.IOException;

public interface TextSupplier {

	String fetchText(String url) throws IOException;
}
