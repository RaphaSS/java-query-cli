package br.com.raphael.javaquerycli.parsing.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.raphael.javaquerycli.parsing.exception.InvalidDataSetException;

public class ObjectIOUtils {

	public static <T> List<T> read(final InputStream inputStream, final Class<T> clazz) {
		List<T> list;
		try(final Scanner scanner = new Scanner(inputStream);) {
			final String[] headers = scanner.nextLine().split(",");

			for(list = new ArrayList<>(); scanner.hasNextLine();) {
				try {
					final String[] values = scanner.nextLine().split(",");

					if(headers.length != values.length) throw new InvalidDataSetException();

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
			.collect(Collectors.joining(","));

		writer.println(strHeaders);

		list.stream()
			.forEach(e -> writer.println(
				headers.stream()
					.map(h -> {
						final Object value = ObjectUtils.getValue(e, h);
						if(value instanceof Boolean)
							return Boolean.TRUE.equals(value) ? value.toString() : "";
						else return value.toString();
					})
					.collect(Collectors.joining(","))));

		writer.close();
	}

}
