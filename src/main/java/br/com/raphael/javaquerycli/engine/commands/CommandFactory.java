package br.com.raphael.javaquerycli.engine.commands;

import java.util.Arrays;

import br.com.raphael.javaquerycli.engine.commands.impl.CountAllCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.CountDistinctCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.FilterCommand;
import br.com.raphael.javaquerycli.engine.exception.CommandNotFoundException;

public class CommandFactory {

	public Command getInstance(final String[] commandLine) {
		final Command command;

		final String action = commandLine[0];
		final String[] arguments = Arrays.copyOfRange(commandLine, 1, commandLine.length);

		switch(action) {
			case "count":
				command = arguments.length == 1 ? new CountAllCommand() : new CountDistinctCommand();
				break;

			case "filter":
				command = new FilterCommand();
				break;

			default:
				throw new CommandNotFoundException();
		}

		command.setArguments(arguments);

		return command;
	}

}
