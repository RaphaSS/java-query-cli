package br.com.raphael.javaquerycli.parsing.exception;

public class InvalidDataSetException extends ParsingException {

	private static final long serialVersionUID = 935572793055213364L;

	protected InvalidDataSetException() {
		super("Conjunto de dados invalido!");
	}

}
