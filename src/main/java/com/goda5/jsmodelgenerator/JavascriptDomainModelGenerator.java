package com.goda5.jsmodelgenerator;

import java.io.File;
import java.lang.reflect.Field;

public class JavascriptDomainModelGenerator {
	public void generate(Class<?> clazz, File targetJsFolder) throws Exception {
		if (clazz.isAnnotationPresent(JavascriptDomainModel.class)) {
			for(Field field:clazz.getDeclaredFields()) {

			}
		}
	}
}
