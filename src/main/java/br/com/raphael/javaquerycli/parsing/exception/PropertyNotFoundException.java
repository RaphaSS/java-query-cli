package br.com.raphael.javaquerycli.parsing.exception;

public class PropertyNotFoundException extends ParsingException {

	private static final long serialVersionUID = -1223915125620188030L;

	public PropertyNotFoundException() {
		super("Propriedade nao encontrada!");
	}

}
