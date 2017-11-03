package br.com.raphael.javaquerycli.utils;

import java.io.IOException;
import java.io.OutputStream;

public class StringOutputStream extends OutputStream {

	private final StringBuilder builder = new StringBuilder();

	@Override
	public void write(final int b) throws IOException {
		builder.append((char) b);
	}

	@Override
	public String toString() {
		return builder.toString();
	}

}
