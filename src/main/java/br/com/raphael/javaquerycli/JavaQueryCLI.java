package br.com.raphael.javaquerycli;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import br.com.raphael.javaquerycli.engine.CommandExecutor;
import br.com.raphael.javaquerycli.engine.commands.Command;
import br.com.raphael.javaquerycli.engine.commands.impl.ExitCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.HelpCommand;
import br.com.raphael.javaquerycli.model.City;
import br.com.raphael.javaquerycli.parsing.exception.ParsingException;
import br.com.raphael.javaquerycli.parsing.utils.ObjectIOUtils;
import br.com.raphael.javaquerycli.view.UI;

public class JavaQueryCLI {

	public static void main(final String[] args) throws FileNotFoundException {
		UI.showWelcome();
		Command command;
		final List<City> list = ObjectIOUtils.read(new FileInputStream("data/cidades.csv"), City.class);
		final CommandExecutor<City> commandExecutor = new CommandExecutor<>(list, System.out, City.class);
		do {
			command = UI.askCommand();
			if(command instanceof HelpCommand) {
				UI.showHelp();
			} else {
				try {
					commandExecutor.execute(command);
				} catch(final ParsingException e) {
					UI.showError(e);
				}
			}
		} while(!(command instanceof ExitCommand));
		UI.sayBye();
		System.exit(0);
	}

}
