package com.stylight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stylight.entity.Currency;
import com.stylight.service.CurrencyConverterManager;

@Controller
public class CurrencyController {

	@Autowired
	CurrencyConverterManager currencyConverterManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listEmployees(ModelMap map) {
		
		//To display the dropdown
//		Map<String,String> currencies = new LinkedHashMap<String,String>();
//		currencies.put("euro", "Euro");
//		currencies.put("dollar", "Dollar");
//		currencies.put("yen", "Yen");
//		map.addAttribute("currencies", currencies);
		
		map.addAttribute("converSionRate", new Currency());
		
		return "currConverter";
	}

	
	@RequestMapping(value = "/show/{source}to{target}")
	public String showCurrency(ModelMap map,
			@PathVariable("source") String source,
			@PathVariable("target") String target) {

		Currency curr = (Currency) map.get("converSionRate");
		if(null == curr){
			curr = new Currency();	
		}
		curr.setConversionRate(currencyConverterManager.convert(source, target)
				.toString());
		map.addAttribute("converSionRate", curr);
//		return "redirect:/";
		return "showRate";
	}

}
