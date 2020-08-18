package br.com.raphael.javaquerycli.engine.commands;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import br.com.raphael.javaquerycli.engine.commands.impl.CountAllCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.CountDistinctCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.FilterCommand;
import br.com.raphael.javaquerycli.engine.exception.InterpretationException;

public class CommandFactoryTest {

    @Test
    public void countAll() throws InterpretationException {
        final Command c = CommandFactory.getInstance("count *");
        assertThat(c).isInstanceOf(CountAllCommand.class);
    }

    @Test
    public void countDistinctName() throws InterpretationException {
        final Command c = CommandFactory.getInstance("count distinct mesoregion");
        assertThat(c).isInstanceOf(CountDistinctCommand.class);

        CountDistinctCommand count = (CountDistinctCommand) c;
        assertThat(count.getProperty()).isEqualTo("mesoregion");
    }

    @Test
    public void filterUfRj() throws InterpretationException {
        final Command c = CommandFactory.getInstance("filter uf RJ");
        assertThat(c).isInstanceOf(FilterCommand.class);

        FilterCommand filter = (FilterCommand) c;
        assertThat(filter.getProperty()).isEqualTo("uf");
        assertThat(filter.getValue()).isEqualTo("RJ");
    }

    @Test
    public void filterMesoregionGrandeFlorianopolis() throws InterpretationException {
        final Command c = CommandFactory.getInstance("filter mesoregion 'Grande Florianópolis'");
        assertThat(c).isInstanceOf(FilterCommand.class);

        FilterCommand filter = (FilterCommand) c;
        assertThat(filter.getProperty()).isEqualTo("mesoregion");
        assertThat(filter.getValue()).isEqualTo("Grande Florianópolis");
    }

}
