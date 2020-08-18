package br.com.raphael.javaquerycli.parsing.utils;

import static java.lang.String.join;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.LF;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import br.com.raphael.javaquerycli.parsing.exception.InvalidDataSetException;
import br.com.raphael.javaquerycli.parsing.exception.ParsingException;
import br.com.raphael.javaquerycli.parsing.exception.PropertyNotFoundException;

public class ObjectIOUtils {

	public static final String DELIMITER = ",";

	public static <T> List<T> read(final InputStream inputStream, final Class<T> clazz) throws ParsingException {
		List<T> list;
		try(final Scanner scanner = new Scanner(inputStream)) {
			final String[] headers = scanner.nextLine().split(DELIMITER);

			for(list = new ArrayList<>(); scanner.hasNextLine();) {
				try {
					final String[] values = scanner.nextLine().split(DELIMITER);

					if(headers.length != values.length)
						throw new InvalidDataSetException();

					final T element = clazz.getConstructor().newInstance();
					for(int i = 0; i < headers.length; i++) {
						ObjectUtils.setValue(element, headers[i], values[i]);
					}

					list.add(element);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public static <T> void write(final List<T> list, final OutputStream outputStream, final Class<T> clazz) {
		final PrintWriter writer = new PrintWriter(outputStream);
		final List<String> headers = getHeaderFields(clazz);
		final String strHeaders = join(DELIMITER, headers);

		writeContent(writer, strHeaders);

		list
			.forEach(e -> {
				String line = headers.stream()
					.map(h -> getValue(e, h))
					.collect(joining(DELIMITER));
				writeContent(writer, line);
			});

		writer.flush();
	}

	private static <T> List<String> getHeaderFields(Class<T> clazz) {
		return Arrays.stream(clazz.getDeclaredFields())
			.map(f -> f.getDeclaredAnnotation(br.com.raphael.javaquerycli.parsing.annotation.Field.class).value())
			.collect(toList());
	}

	private static <T> String getValue(T object, String property) {
		Object value;
		try {
			value = ObjectUtils.getValue(object, property);
			if(value instanceof Boolean)
				return Boolean.TRUE.equals(value) ? value.toString() : "";
			return value != null ? value.toString() : null;
		} catch(final PropertyNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private static void writeContent(PrintWriter writer, String line) {
		writer.print(line);
		writer.print(LF);
	}

}
