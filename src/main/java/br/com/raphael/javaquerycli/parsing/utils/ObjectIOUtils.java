package br.com.raphael.javaquerycli.parsing.utils;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.LF;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.raphael.javaquerycli.parsing.exception.InvalidDataSetException;
import br.com.raphael.javaquerycli.parsing.exception.ParsingException;
import br.com.raphael.javaquerycli.parsing.exception.PropertyNotFoundException;

public class ObjectIOUtils {

	public static <T> List<T> read(final InputStream inputStream, final Class<T> clazz) throws ParsingException {
		List<T> list;
		try(final Scanner scanner = new Scanner(inputStream);) {
			final String[] headers = scanner.nextLine().split(",");

			for(list = new ArrayList<>(); scanner.hasNextLine();) {
				try {
					final String[] values = scanner.nextLine().split(",");

					if(headers.length != values.length)
						throw new InvalidDataSetException();

					final T element = clazz.newInstance();
					for(int i = 0; i < headers.length; i++) {
						ObjectUtils.setValue(element, headers[i], values[i]);
					}

					list.add(element);
				} catch(InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public static <T> void write(final List<T> list, final OutputStream outputStream, final Class<T> clazz) {
		final PrintWriter writer = new PrintWriter(outputStream);
		final List<String> headers = Arrays.asList(clazz.getDeclaredFields()).stream()
			.map(f -> f.getDeclaredAnnotation(br.com.raphael.javaquerycli.parsing.annotation.Field.class).value())
			.collect(Collectors.toList());
		final String strHeaders = headers.stream()
			.collect(joining(","));

		writer.print(strHeaders);
		writer.print(LF);

		list.stream()
			.forEach(e -> {
				writer.print(
					headers.stream()
						.map(h -> {
							Object value;
							try {
								value = ObjectUtils.getValue(e, h);
								if(value instanceof Boolean)
									return Boolean.TRUE.equals(value) ? value.toString() : "";
								else return value.toString();
							} catch(final PropertyNotFoundException e1) {
								return null;
							}
						})
						.collect(joining(",")));
				writer.print(LF);
			});

		writer.flush();
	}

}
