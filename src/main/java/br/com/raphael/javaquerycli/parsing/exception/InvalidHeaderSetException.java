package br.com.raphael.javaquerycli.parsing.exception;

public class InvalidHeaderSetException extends ParsingException {

	private static final long serialVersionUID = 6412637378415702435L;

	public InvalidHeaderSetException() {
		super("Conjunto de cabecalhos invalido!");
	}

}
