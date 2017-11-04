package br.com.raphael.javaquerycli.engine;

import java.io.OutputStream;
import java.util.List;

import br.com.raphael.javaquerycli.engine.commands.Command;
import br.com.raphael.javaquerycli.engine.commands.impl.CountCommand;

public class CommandExecutor {

	private final List<?> list;
	private final OutputStream outputStream;

	public CommandExecutor(final List<?> list, final OutputStream outputStream) {
		this.list = list;
		this.outputStream = outputStream;
	}

	public void execute(final Command command) {
		if(command instanceof CountCommand) {

		}
	}

}
