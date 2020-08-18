package br.com.raphael.javaquerycli.collections;

import static br.com.raphael.javaquerycli.JavaQueryCLITest.FILE_CONTENT;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.junit.Test;

import br.com.raphael.javaquerycli.model.City;
import br.com.raphael.javaquerycli.parsing.utils.ObjectIOUtils;

public class CollectionUtilsTest {

	@Test
	public void countAll() {
		final List<City> readCities = ObjectIOUtils.read(new ByteArrayInputStream(FILE_CONTENT.getBytes()), City.class);

		assertThat(readCities).hasSize(4);
	}

	@Test
	public void countDistinctMesoregion() {
		final List<String> distinctFields = asList("Leste Rondoniense", "Agreste Paraibano", "Madeira-Guaporé");

		final List<City> readCities = ObjectIOUtils.read(new ByteArrayInputStream(FILE_CONTENT.getBytes()), City.class);
		final List<String> distinct = CollectionUtils.distinct(readCities, "mesoregion");

		assertThat(distinct).containsExactlyInAnyOrderElementsOf(distinctFields);
	}

	@Test
	public void filterFromRO() {
		final List<City> cities = asList(
			new City((long) 1100015, "RO", "Alta Floresta D'Oeste", false, -61.9998238963, -11.9355403048,
				"Alta Floresta D'Oeste", "", "Cacoal", "Leste Rondoniense"),
			new City((long) 1100023, "RO", "Ariquemes", false, -63.033269278, -9.9084628666, "Ariquemes", "",
				"Ariquemes", "Leste Rondoniense"),
			new City((long) 1100205, "RO", "Porto Velho", true, -63.8314456544, -8.76889179, "Porto Velho", "",
				"Porto Velho", "Madeira-Guaporé"));

		List<City> filteredCities = ObjectIOUtils.read(new ByteArrayInputStream(FILE_CONTENT.getBytes()), City.class);
		filteredCities = CollectionUtils.filter(filteredCities, "uf", "RO");

		assertThat(filteredCities).isEqualTo(cities);
	}

}
