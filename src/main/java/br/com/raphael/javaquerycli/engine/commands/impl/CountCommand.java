package br.com.raphael.javaquerycli.engine.commands.impl;

import br.com.raphael.javaquerycli.engine.commands.Command;

public abstract class CountCommand extends Command {

	public CountCommand(final Integer expectedArgumentsLength) {
		super("count", expectedArgumentsLength);
	}

}
