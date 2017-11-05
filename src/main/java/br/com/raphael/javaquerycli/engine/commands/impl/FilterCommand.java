package br.com.raphael.javaquerycli.engine.commands.impl;

import br.com.raphael.javaquerycli.engine.commands.Command;

public class FilterCommand extends Command {

	public FilterCommand() {
		super("filter", 2);
	}

	public String getProperty() {
		return getArguments()[0];
	}

	public String getValue() {
		return getArguments()[1];
	}

}
