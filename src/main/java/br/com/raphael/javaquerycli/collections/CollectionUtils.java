package br.com.raphael.javaquerycli.collections;

import java.util.List;
import java.util.stream.Collectors;

import br.com.raphael.javaquerycli.parsing.utils.ObjectUtils;

public class CollectionUtils {

	public static <T, P> List<T> filter(final List<T> list, final String property, final P value) {
		return list.stream()
			.filter(e -> value.equals(ObjectUtils.getValue(e, property)))
			.collect(Collectors.toList());
	}

	public static <T> List<?> distinct(final List<T> list, final String property) {
		return list.stream()
			.map(e -> ObjectUtils.getValue(e, property))
			.distinct()
			.collect(Collectors.toList());
	}

}
