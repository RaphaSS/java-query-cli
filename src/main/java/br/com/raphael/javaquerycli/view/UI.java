package br.com.raphael.javaquerycli.view;

import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.raphael.javaquerycli.engine.commands.Command;
import br.com.raphael.javaquerycli.engine.commands.CommandFactory;
import br.com.raphael.javaquerycli.engine.exception.InterpretationException;
import br.com.raphael.javaquerycli.model.City;
import br.com.raphael.javaquerycli.parsing.annotation.Field;
import br.com.raphael.javaquerycli.parsing.utils.ObjectUtils;

public class UI {

	private static final Scanner scanner = new Scanner(System.in);

	private static void say(final String prompt, final Object... args) {
		System.out.println(String.format(prompt, args));
	}

	private static String ask(final String prompt, final Object... args) {
		say(prompt, args);
		System.out.print("> ");
		String line;
		do {
			line = scanner.nextLine();
		} while(line == null || line.isEmpty());
		return line;
	}

	public static void showHelp() {
		say("\n---------------------------------------\nI can understand up to three commands:\n");
		say("-> count *\t\t\t\tCounts all available entries");
		say("-> count distinct [some_property]\tCounts the distinct values of 'some_property'");
		say("-> filter [some_property] [some_value]\tDisplays the entries where 'some_property' equals 'some_value'");
		say("-> help\t\t\t\t\tShows this help screen");
		say("-> exit\t\t\t\t\tSays bye");
		say("---------------------------------------");
		say("Available properties are:\n%s", ObjectUtils.getAnnotatedFields(City.class, Field.class).stream()
			.map(f -> f.getAnnotation(Field.class).value())
			.collect(Collectors.joining(", ")));
	}

	public static void showWelcome() {
		say("Welcome to the Java Query CLI!");
		showHelp();
	}

	public static Command askCommand() {
		final String commandLine = ask("\n---------------------------------------\nWhat are we doing next?");
		try {
			return CommandFactory.getInstance(commandLine);
		} catch(final InterpretationException e) {
			showError(e);
			showHelp();
			return askCommand();
		}
	}

	public static void showError(final Exception e) {
		say("Sorry! I didn't understand!\nDetails: %s", e.getMessage());
	}

	public static void sayBye() {
		say("Bye! :)");
	}

}
