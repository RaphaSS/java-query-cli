package br.com.raphael.javaquerycli.parsing;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.raphael.javaquerycli.parsing.exception.InvalidDataSetException;

public abstract class Parser<T> {

	private static Field getField(final Class<?> clazz, final String property) {
		final Field[] fields = clazz.getDeclaredFields();
		for(final Field field : fields) {
			final br.com.raphael.javaquerycli.parsing.annotation.Field f = field
				.getAnnotation(br.com.raphael.javaquerycli.parsing.annotation.Field.class);
			if(f.value().equalsIgnoreCase(property)) {
				field.setAccessible(true);
				return field;
			}
		}
		return null;
	}

	public static <T> boolean equals(final T o1, final T o2, final Class<T> clazz) {
		final Field[] fields = clazz.getDeclaredFields();
		for(final Field field : fields) {
			field.setAccessible(true);
			try {
				if(!field.get(o1).equals(field.get(o2))) return false;
			} catch(IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private final Class<T> clazz;

	public Parser(final Class<T> clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	private <P> P getValue(final T element, final String property) {
		final Field field = getField(element.getClass(), property);
		try {
			return (P) field.get(element);
		} catch(IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	private <P> void setValue(final T element, final String property, final P value) {
		final Field field = getField(element.getClass(), property);
		try {
			if(Long.class.equals(field.getType())) {
				field.set(element, Long.valueOf((String) value));
			} else if(Double.class.equals(field.getType())) {
				field.set(element, Double.valueOf((String) value));
			} else if(Boolean.class.equals(field.getType())) {
				field.set(element, ((String) value).isEmpty() ? false : new Boolean((String) value));
			} else {
				field.set(element, value);
			}
		} catch(IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public List<T> read(final InputStream inputStream) {
		List<T> list;
		try(final Scanner scanner = new Scanner(inputStream);) {
			final String[] headers = scanner.nextLine().split(",");

			for(list = new ArrayList<>(); scanner.hasNextLine();) {
				try {
					final String[] values = scanner.nextLine().split(",");

					if(headers.length != values.length) throw new InvalidDataSetException();

					final T element = clazz.newInstance();
					for(int i = 0; i < headers.length; i++) {
						setValue(element, headers[i], values[i]);
					}

					list.add(element);
				} catch(InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public void write(final List<T> list, final OutputStream outputStream) {
		final PrintWriter writer = new PrintWriter(outputStream);
		// Arrays.asList(clazz.getDeclaredFields()).stream()
		// .map(f ->
		// f.getDeclaredAnnotation(br.com.raphael.javaquerycli.parsing.annotation.Field.class).value())
		// .forEach(h -> {
		// writer.print(h);
		// writer.print(",");
		// nao pode ter essa ultima virgula
		// });
	}

}
