package com.bankingapp.enums;

import java.util.HashMap;
import java.util.Map;

public enum TemplateType {

	SUCCESSTEMPLATE("0"), FAILEDTEMPLATE("1");

	private static Map<String, TemplateType> map = new HashMap<String, TemplateType>();
	private final String template;

	TemplateType(String template) {
		this.template = template;
	}

	static {
		for (TemplateType template : values()) {
			map.put(template.template, template);
		}
	}

	public static TemplateType of(String value) {
		TemplateType templateType = map.get(value);
		return templateType;
	}

}
