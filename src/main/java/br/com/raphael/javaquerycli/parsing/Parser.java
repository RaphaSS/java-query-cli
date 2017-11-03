package br.com.raphael.javaquerycli.parsing;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface Parser<T> {

	List<T> read(InputStream inputStream);

	void write(List<T> list, OutputStream outputStream);

}
