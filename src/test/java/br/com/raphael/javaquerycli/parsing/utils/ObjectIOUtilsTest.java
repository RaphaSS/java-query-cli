package br.com.raphael.javaquerycli.parsing.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import br.com.raphael.javaquerycli.JavaQueryCLITest;
import br.com.raphael.javaquerycli.model.City;
import br.com.raphael.javaquerycli.utils.StringOutputStream;
import junitx.framework.ListAssert;

@RunWith(BlockJUnit4ClassRunner.class)
public class ObjectIOUtilsTest {

	private static File file;

	@BeforeClass
	public static void setUp() {
		final String datasetFile = System.getProperty("datasetfile");
		file = new File(datasetFile);
	}

	@Test
	public void leArquivo() {
		try(InputStream inputStream = new FileInputStream(file)) {
			final List<City> readCities = ObjectIOUtils.read(inputStream, City.class);

			ListAssert.assertEquals(JavaQueryCLITest.ALL_CITIES, readCities);
		} catch(final IOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void escreveLista() {
		final OutputStream outputStream = new StringOutputStream();
		ObjectIOUtils.write(JavaQueryCLITest.ALL_CITIES, outputStream, City.class);
		final String actual = outputStream.toString();

		Assert.assertEquals(JavaQueryCLITest.FILE_CONTENT, actual);
	}

}
