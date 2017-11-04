package br.com.raphael.javaquerycli.parsing.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.raphael.javaquerycli.parsing.exception.PropertyNotFoundException;

public abstract class ObjectUtils {

	private static Field getField(final Class<?> clazz, final String property) {
		final Set<Field> fields = getAnnotatedFields(clazz, br.com.raphael.javaquerycli.parsing.annotation.Field.class);
		for(final Field field : fields) {
			final br.com.raphael.javaquerycli.parsing.annotation.Field f = field
				.getAnnotation(br.com.raphael.javaquerycli.parsing.annotation.Field.class);
			if(f.value().equalsIgnoreCase(property)) {
				field.setAccessible(true);
				return field;
			}
		}
		throw new PropertyNotFoundException();
	}

	public static Set<Field> getAnnotatedFields(final Class<?> clazz, final Class<?> annotation) {
		return Arrays.asList(clazz.getDeclaredFields()).stream()
			.filter(f -> f.getAnnotation(br.com.raphael.javaquerycli.parsing.annotation.Field.class) != null)
			.collect(Collectors.toSet());
	}

	public static <T> boolean equals(final T o1, final T o2, final Class<T> clazz) {
		final Field[] fields = clazz.getDeclaredFields();
		for(final Field field : fields) {
			field.setAccessible(true);
			try {
				if(!field.get(o1).equals(field.get(o2))) return false;
			} catch(IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static <T, P> P getValue(final T element, final String property) {
		final Field field = getField(element.getClass(), property);
		try {
			return (P) field.get(element);
		} catch(IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T, P> void setValue(final T element, final String property, final P value) {
		final Field field = getField(element.getClass(), property);
		try {
			if(Long.class.equals(field.getType())) {
				field.set(element, Long.valueOf((String) value));
			} else if(Double.class.equals(field.getType())) {
				field.set(element, Double.valueOf((String) value));
			} else if(Boolean.class.equals(field.getType())) {
				field.set(element, ((String) value).isEmpty() ? false : new Boolean((String) value));
			} else {
				field.set(element, value);
			}
		} catch(IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
