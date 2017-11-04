package br.com.raphael.javaquerycli;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import br.com.raphael.javaquerycli.filtering.Filter;
import br.com.raphael.javaquerycli.model.Cidade;
import br.com.raphael.javaquerycli.parsing.impl.CidadeParser;
import br.com.raphael.javaquerycli.utils.StringOutputStream;
import junitx.framework.ListAssert;

@RunWith(BlockJUnit4ClassRunner.class)
public class JavaQueryCLITest {

	private final CidadeParser parser = new CidadeParser();
	private final Filter<Cidade> filterer = new Filter<>();

	private final List<Cidade> allCities = new ArrayList<>(Arrays.asList(
		new Cidade((long) 1100015, "RO", "Alta Floresta D'Oeste", false, -61.9998238963, -11.9355403048,
			"Alta Floresta D'Oeste", "", "Cacoal", "Leste Rondoniense"),
		new Cidade((long) 1100023, "RO", "Ariquemes", false, -63.033269278, -9.9084628666, "Ariquemes",
			"", "Ariquemes", "Leste Rondoniense"),
		new Cidade((long) 2516409, "PB", "Tacima", false, -35.6388366565, -6.4889169425, "Tacima",
			"Campo de Santana", "Curimataú Oriental", "Agreste Paraibano"),
		new Cidade((long) 1100205, "RO", "Porto Velho", true, -63.8314456544, -8.76889179, "Porto Velho",
			"", "Porto Velho", "Madeira-Guaporé")));

	private final String fileContent = new StringBuilder()
		.append("ibge_id,uf,name,capital,lon,lat,no_accents,alternative_names,microregion,mesoregion\n")
		.append("1100015,RO,Alta Floresta D'Oeste,,-61.9998238963,-11.9355403048,Alta Floresta D'Oeste,,Cacoal,Leste Rondoniense\n")
		.append("1100023,RO,Ariquemes,,-63.033269278,-9.9084628666,Ariquemes,,Ariquemes,Leste Rondoniense\n")
		.append("2516409,PB,Tacima,,-35.6388366565,-6.4889169425,Tacima,Campo de Santana,Curimataú Oriental,Agreste Paraibano\n")
		.append("1100205,RO,Porto Velho,true,-63.8314456544,-8.76889179,Porto Velho,,Porto Velho,Madeira-Guaporé")
		.toString();

	@Test
	public void leArquivo() {
		final File file = new File("data/test.csv");
		try(InputStream inputStream = new FileInputStream(file)) {
			final List<Cidade> readCities = parser.read(inputStream);

			ListAssert.assertEquals(allCities, readCities);
		} catch(final IOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void escreveLista() {
		final OutputStream outputStream = new StringOutputStream();
		parser.write(allCities, outputStream);
		final String actual = outputStream.toString();

		Assert.assertEquals(fileContent, actual);
	}

	@Test
	public void countAll() {
		final List<Cidade> cidades = parser.read(new ByteArrayInputStream(fileContent.getBytes()));

		Assert.assertEquals(4, cidades.size());
	}

	@Test
	public void countDistinctMesoregion() {
		final List<String> distinctFields = Arrays.asList("Leste Rondoniense", "Leste Rondoniense", "Madeira-Guaporé");

		final List<Cidade> readCities = parser.read(new ByteArrayInputStream(fileContent.getBytes()));
		final List<?> distinct = filterer.distinct(readCities, "mesoregion");

		ListAssert.assertEquals(distinctFields, distinct);
	}

	@Test
	public void filterFromRO() {
		final List<Cidade> cidades = Arrays.asList(
			new Cidade((long) 1100015, "RO", "Alta Floresta D'Oeste", false, -61.9998238963, -11.9355403048,
				"Alta Floresta D'Oeste", "", "Cacoal", "Leste Rondoniense"),
			new Cidade((long) 1100023, "RO", "Ariquemes", false, -63.033269278, -9.9084628666, "Ariquemes", "",
				"Ariquemes", "Leste Rondoniense"),
			new Cidade((long) 1100205, "RO", "Porto Velho", true, -63.8314456544, -8.76889179, "Porto Velho", "",
				"Porto Velho", "Madeira-Guaporé"));

		List<Cidade> filteredCities = parser.read(new ByteArrayInputStream(fileContent.getBytes()));
		filteredCities = filterer.filter(filteredCities, "uf", "RO");

		ListAssert.assertEquals(cidades, filteredCities);
	}

}
