package com.stylight.service;

public interface CurrencyConverterManager {

	String EURO = "EURO";
	String DOLLAR = "DOLLAR";
	String YEN = "YEN";
	
	public String convert(String source, String target);
}
