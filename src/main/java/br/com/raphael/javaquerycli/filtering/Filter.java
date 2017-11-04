package br.com.raphael.javaquerycli.filtering;

import java.util.List;
import java.util.stream.Collectors;

import br.com.raphael.javaquerycli.parsing.Parser;

public class Filter<T> {

	public <P> List<T> filter(final List<T> list, final String property, final P value) {
		return list.stream()
			.filter(e -> value.equals(Parser.getValue(e, property)))
			.collect(Collectors.toList());
	}

	public List<?> distinct(final List<T> list, final String property) {
		return list.stream()
			.map(e -> Parser.getValue(e, property))
			.distinct()
			.collect(Collectors.toList());
	}

}
