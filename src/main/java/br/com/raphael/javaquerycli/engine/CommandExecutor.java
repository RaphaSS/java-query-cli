package br.com.raphael.javaquerycli.engine;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import br.com.raphael.javaquerycli.collections.CollectionUtils;
import br.com.raphael.javaquerycli.engine.commands.Command;
import br.com.raphael.javaquerycli.engine.commands.impl.CountAllCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.CountCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.CountDistinctCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.FilterCommand;
import br.com.raphael.javaquerycli.parsing.exception.ParsingException;
import br.com.raphael.javaquerycli.parsing.utils.ObjectIOUtils;

public class CommandExecutor<T> {

	private final List<T> list;
	private final OutputStream outputStream;

	private final Class<T> clazz;

	public CommandExecutor(final List<T> list, final OutputStream outputStream, final Class<T> clazz) {
		this.list = list;
		this.outputStream = outputStream;
		this.clazz = clazz;
	}

	public void execute(final Command command) throws ParsingException {
		final PrintWriter writer = new PrintWriter(outputStream);
		if(command instanceof CountCommand) {
			if(command instanceof CountAllCommand) {
				writer.format("Found %d entries\n", list.size()).flush();
			} else if(command instanceof CountDistinctCommand) {
				final CountDistinctCommand c = (CountDistinctCommand) command;
				final String property = c.getProperty();
				final List<?> distinct = CollectionUtils.distinct(list, property);
				writer.format("Found %d distinct entries for property [%s]\n", distinct.size(), property).flush();
			}
		} else if(command instanceof FilterCommand) {
			final FilterCommand c = (FilterCommand) command;
			final String property = c.getProperty();
			final String value = c.getValue();
			final List<T> filter = CollectionUtils.filter(list, property, value);
			writer.format("Displaying %d filtered entries filtering by property [%s] of value [%s]\n", filter.size(), property, value).flush();
			ObjectIOUtils.write(filter, outputStream, clazz);
		}
	}

}
