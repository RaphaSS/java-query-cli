package br.com.raphael.javaquerycli.collections;

import static java.util.stream.Collectors.toList;

import java.util.List;

import br.com.raphael.javaquerycli.parsing.utils.ObjectUtils;

public class CollectionUtils {

	public static <T, P> List<T> filter(final List<T> list, final String property, final P value) {
		return list.stream()
			.filter(e -> value.equals(ObjectUtils.getValue(e, property)))
			.collect(toList());
	}

	public static <T, P> List<P> distinct(final List<T> list, final String property) {
		return list.stream()
			.map(e -> ObjectUtils.<T, P> getValue(e, property))
			.distinct()
			.collect(toList());
	}

}
