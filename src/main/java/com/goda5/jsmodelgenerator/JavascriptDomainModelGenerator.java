package com.goda5.jsmodelgenerator;

import java.lang.reflect.Field;

public class JavascriptDomainModelGenerator {
	public void generate(Class<?> clazz) throws Exception {
		if (clazz.isAnnotationPresent(JavascriptDomainModel.class)) {
			for(Field field:clazz.getDeclaredFields()) {

			}
		}
	}
}
