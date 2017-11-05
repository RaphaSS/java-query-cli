package br.com.raphael.javaquerycli.engine.commands.impl;

public class CountDistinctCommand extends CountCommand {

	public CountDistinctCommand() {
		super(2);
	}

	public String getProperty() {
		return getArguments()[1];
	}

}
