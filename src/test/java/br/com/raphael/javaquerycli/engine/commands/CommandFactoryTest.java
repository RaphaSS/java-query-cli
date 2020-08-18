package br.com.raphael.javaquerycli.engine.commands;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.Test;

import br.com.raphael.javaquerycli.engine.commands.impl.CountAllCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.CountDistinctCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.ExitCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.FilterCommand;
import br.com.raphael.javaquerycli.engine.commands.impl.HelpCommand;
import br.com.raphael.javaquerycli.engine.exception.CommandNotFoundException;
import br.com.raphael.javaquerycli.engine.exception.InterpretationException;

public class CommandFactoryTest {

    @Test
    public void shouldCreate_countAllCommand() throws InterpretationException {
        final Command c = CommandFactory.getInstance("count *");
        assertThat(c).isInstanceOf(CountAllCommand.class);
    }

    @Test
    public void shouldCreate_countDistinctNameCommand() throws InterpretationException {
        final Command c = CommandFactory.getInstance("count distinct mesoregion");
        assertThat(c).isInstanceOf(CountDistinctCommand.class);

        CountDistinctCommand count = (CountDistinctCommand) c;
        assertThat(count.getProperty()).isEqualTo("mesoregion");
    }

    @Test
    public void shouldCreate_filterUfRjCommand() throws InterpretationException {
        final Command c = CommandFactory.getInstance("filter uf RJ");
        assertThat(c).isInstanceOf(FilterCommand.class);

        FilterCommand filter = (FilterCommand) c;
        assertThat(filter.getProperty()).isEqualTo("uf");
        assertThat(filter.getValue()).isEqualTo("RJ");
    }

    @Test
    public void shouldCreate_filterMesoregionGrandeFlorianopolisCommand() throws InterpretationException {
        final Command c = CommandFactory.getInstance("filter mesoregion 'Grande Florianópolis'");
        assertThat(c).isInstanceOf(FilterCommand.class);

        FilterCommand filter = (FilterCommand) c;
        assertThat(filter.getProperty()).isEqualTo("mesoregion");
        assertThat(filter.getValue()).isEqualTo("Grande Florianópolis");
    }

    @Test
    public void shouldCreate_helpCommand() throws InterpretationException {
        Command c = CommandFactory.getInstance("help");
        assertThat(c).isInstanceOf(HelpCommand.class);
    }

    @Test
    public void shouldCreate_exitCommand() throws InterpretationException {
        Command c = CommandFactory.getInstance("exit");
        assertThat(c).isInstanceOf(ExitCommand.class);
    }

    @Test
    public void shouldThrow_whenCommandNotFound() {
        assertThatCode(() -> CommandFactory.getInstance("unknown command")).isInstanceOf(CommandNotFoundException.class);
        assertThatCode(() -> CommandFactory.getInstance("abcde")).isInstanceOf(CommandNotFoundException.class);
        assertThatCode(() -> CommandFactory.getInstance("test 123")).isInstanceOf(CommandNotFoundException.class);
    }

}
