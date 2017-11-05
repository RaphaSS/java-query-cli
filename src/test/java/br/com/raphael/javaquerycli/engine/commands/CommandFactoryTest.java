package br.com.raphael.javaquerycli.engine.commands;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import br.com.raphael.javaquerycli.engine.commands.impl.CountAllCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.CountDistinctCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.FilterCommand;
import br.com.raphael.javaquerycli.engine.exception.InterpretationException;

@RunWith(BlockJUnit4ClassRunner.class)
public class CommandFactoryTest {

	@Test
	public void countAll() throws InterpretationException {
		final Command c = CommandFactory.getInstance("count *");
		Assert.assertTrue(c instanceof CountAllCommand);
	}

	@Test
	public void countDistinctName() throws InterpretationException {
		final Command c = CommandFactory.getInstance("count distinct mesoregion");
		Assert.assertTrue(c instanceof CountDistinctCommand);
		Assert.assertEquals("mesoregion", ((CountDistinctCommand) c).getProperty());
	}

	@Test
	public void filterUfRj() throws InterpretationException {
		final Command c = CommandFactory.getInstance("filter uf RJ");
		Assert.assertTrue(c instanceof FilterCommand);
		Assert.assertEquals("uf", ((FilterCommand) c).getProperty());
		Assert.assertEquals("RJ", ((FilterCommand) c).getValue());
	}

	@Test
	public void filterMesoregionGrandeFlorianopolis() throws InterpretationException {
		final Command c = CommandFactory.getInstance("filter mesoregion 'Grande Florianópolis'");
		Assert.assertTrue(c instanceof FilterCommand);
		Assert.assertEquals("mesoregion", ((FilterCommand) c).getProperty());
		Assert.assertEquals("Grande Florianópolis", ((FilterCommand) c).getValue());
	}

}
