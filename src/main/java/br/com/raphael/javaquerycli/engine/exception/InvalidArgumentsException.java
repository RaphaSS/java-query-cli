package br.com.raphael.javaquerycli.engine.exception;

public class InvalidArgumentsException extends InterpretationException {

	private static final long serialVersionUID = 6988648938291465490L;

	public InvalidArgumentsException() {
		super("Argumentos invalidos!");
	}

}
