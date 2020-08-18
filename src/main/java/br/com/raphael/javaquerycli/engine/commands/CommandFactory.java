package br.com.raphael.javaquerycli.engine.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.raphael.javaquerycli.engine.commands.impl.CountAllCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.CountDistinctCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.ExitCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.FilterCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.HelpCommand;
import br.com.raphael.javaquerycli.engine.exception.CommandNotFoundException;
import br.com.raphael.javaquerycli.engine.exception.InterpretationException;
import br.com.raphael.javaquerycli.engine.exception.InvalidArgumentsException;

public class CommandFactory {

	public static final String COUNT = "count";
	public static final String FILTER = "filter";
	public static final String HELP = "help";
	public static final String EXIT = "exit";

	private static String[] parseCommandLine(final String rawCommandLine) {
		final List<String> matches = new ArrayList<>();
		final Matcher matcher = Pattern.compile("([a-zA-Zá-ź]+|'([a-zA-Zá-ź ]+)'|\\*)").matcher(rawCommandLine);
		while(matcher.find()) {
			matches.add(matcher.group().replaceAll("'", ""));
		}
		return matches.toArray(new String[0]);
	}

	public static Command getInstance(final String rawCommandLine) throws InterpretationException {
		final Command command;

		final String[] commandLine = parseCommandLine(rawCommandLine);

		final String action = commandLine[0];
		final String[] arguments = Arrays.copyOfRange(commandLine, 1, commandLine.length);

		switch(action) {
			case COUNT:
				if(arguments.length == 1) {
					if("*".equalsIgnoreCase(arguments[0])) {
						command = new CountAllCommand();
					} else
						throw new InvalidArgumentsException();
				} else {
					command = new CountDistinctCommand();
				}
				break;

			case FILTER:
				command = new FilterCommand();
				break;

			case HELP:
				command = new HelpCommand();
				break;

			case EXIT:
				command = new ExitCommand();
				break;

			default:
				throw new CommandNotFoundException();
		}

		if(arguments.length != command.getExpectedArgumentsLength())
			throw new InvalidArgumentsException();

		command.setArguments(arguments);

		return command;
	}

}
