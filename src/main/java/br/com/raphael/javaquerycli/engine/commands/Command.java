package br.com.raphael.javaquerycli.engine.commands;

import java.util.Arrays;

public abstract class Command {

	private final String action;
	private final Integer expectedArgumentsLength;

	private String[] arguments;

	public Command(final String action, final Integer expectedArgumentsLength) {
		this.action = action;
		this.expectedArgumentsLength = expectedArgumentsLength;
	}

	public String getAction() {
		return action;
	}

	public Integer getExpectedArgumentsLength() {
		return expectedArgumentsLength;
	}

	public String[] getArguments() {
		return arguments;
	}

	void setArguments(final String[] arguments) {
		this.arguments = Arrays.copyOf(arguments, expectedArgumentsLength);
	}

}
