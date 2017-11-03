package br.com.raphael.javaquerycli.filtering.exception;

public class PropertyNotFoundException extends FilteringException {

	private static final long serialVersionUID = -2774181350188743232L;

	protected PropertyNotFoundException() {
		super("Propriedade nao encontrada!");
	}

}
