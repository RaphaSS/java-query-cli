package br.com.raphael.javaquerycli.engine.exception;

public class CommandNotFoundException extends InterpretationException {

	private static final long serialVersionUID = -1626057629318042374L;

	public CommandNotFoundException() {
		super("Comando nao encontrado!");
	}

}
