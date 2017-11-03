package br.com.raphael.javaquerycli.filtering.impl;

import java.util.List;

import br.com.raphael.javaquerycli.filtering.Filter;
import br.com.raphael.javaquerycli.model.Cidade;

public class CidadeFilter implements Filter<Cidade> {

	@Override
	public <P> void filter(final List<Cidade> list, final String property, final P value) {
		// PropertyNotFoundException
	}

	@Override
	public void distinct(final List<Cidade> list, final String property) {
		// PropertyNotFoundException
	}

}
