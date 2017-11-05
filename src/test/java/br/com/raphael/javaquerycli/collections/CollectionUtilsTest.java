package br.com.raphael.javaquerycli.collections;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import br.com.raphael.javaquerycli.JavaQueryCLITest;
import br.com.raphael.javaquerycli.model.City;
import br.com.raphael.javaquerycli.parsing.utils.ObjectIOUtils;
import junitx.framework.ListAssert;

@RunWith(BlockJUnit4ClassRunner.class)
public class CollectionUtilsTest {

	@Test
	public void countAll() {
		final List<City> readCities = ObjectIOUtils.read(new ByteArrayInputStream(JavaQueryCLITest.FILE_CONTENT.getBytes()), City.class);

		Assert.assertEquals(4, readCities.size());
	}

	@Test
	public void countDistinctMesoregion() {
		final List<String> distinctFields = Arrays.asList("Leste Rondoniense", "Leste Rondoniense", "Madeira-Guaporé");

		final List<City> readCities = ObjectIOUtils.read(new ByteArrayInputStream(JavaQueryCLITest.FILE_CONTENT.getBytes()), City.class);
		final List<?> distinct = CollectionUtils.distinct(readCities, "mesoregion");

		ListAssert.assertEquals(distinctFields, distinct);
	}

	@Test
	public void filterFromRO() {
		final List<City> cities = Arrays.asList(
			new City((long) 1100015, "RO", "Alta Floresta D'Oeste", false, -61.9998238963, -11.9355403048,
				"Alta Floresta D'Oeste", "", "Cacoal", "Leste Rondoniense"),
			new City((long) 1100023, "RO", "Ariquemes", false, -63.033269278, -9.9084628666, "Ariquemes", "",
				"Ariquemes", "Leste Rondoniense"),
			new City((long) 1100205, "RO", "Porto Velho", true, -63.8314456544, -8.76889179, "Porto Velho", "",
				"Porto Velho", "Madeira-Guaporé"));

		List<City> filteredCities = ObjectIOUtils.read(new ByteArrayInputStream(JavaQueryCLITest.FILE_CONTENT.getBytes()), City.class);
		filteredCities = CollectionUtils.filter(filteredCities, "uf", "RO");

		ListAssert.assertEquals(cities, filteredCities);
	}

}
