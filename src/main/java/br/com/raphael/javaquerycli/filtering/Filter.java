package br.com.raphael.javaquerycli.filtering;

import java.util.List;

public interface Filter<T> {

	<P> void filter(List<T> list, String property, P value);

	void distinct(List<T> list, String property);

}
