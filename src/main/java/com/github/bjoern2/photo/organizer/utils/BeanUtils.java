package com.github.bjoern2.photo.organizer.utils;

import java.lang.reflect.InvocationTargetException;

import com.github.bjoern2.photo.organizer.conf.BeanXml;
import com.github.bjoern2.photo.organizer.conf.PropertyXml;

public class BeanUtils {

	public static <T> T toBean(BeanXml xml) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
		@SuppressWarnings("unchecked")
		T bean = (T)Class.forName(xml.getClazz()).newInstance();
		if (xml.getProperties() != null) {
			for (PropertyXml prop : xml.getProperties()) {
				org.apache.commons.beanutils.BeanUtils.setProperty(bean, prop.getName(), prop.getValue());
			}
		}
		return bean;
	}
	
}
