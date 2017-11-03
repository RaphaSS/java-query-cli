package br.com.raphael.javaquerycli.parsing.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.raphael.javaquerycli.model.Cidade;
import br.com.raphael.javaquerycli.parsing.Parser;

public class CidadeParser implements Parser<Cidade> {

	@Override
	public List<Cidade> read(final InputStream inputStream) {
		// InvalidHeaderSetException
		// InvalidDataSetException
		return new ArrayList<>();
	}

	@Override
	public void write(final List<Cidade> list, final OutputStream outputStream) {
		// TODO Auto-generated method stub
	}

}
