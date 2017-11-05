package br.com.raphael.javaquerycli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.raphael.javaquerycli.model.City;

public class JavaQueryCLITest {

	public static final List<City> ALL_CITIES = new ArrayList<>(Arrays.asList(
		new City((long) 1100015, "RO", "Alta Floresta D'Oeste", false, -61.9998238963, -11.9355403048,
			"Alta Floresta D'Oeste", "", "Cacoal", "Leste Rondoniense"),
		new City((long) 1100023, "RO", "Ariquemes", false, -63.033269278, -9.9084628666, "Ariquemes",
			"", "Ariquemes", "Leste Rondoniense"),
		new City((long) 2516409, "PB", "Tacima", false, -35.6388366565, -6.4889169425, "Tacima",
			"Campo de Santana", "Curimataú Oriental", "Agreste Paraibano"),
		new City((long) 1100205, "RO", "Porto Velho", true, -63.8314456544, -8.76889179, "Porto Velho",
			"", "Porto Velho", "Madeira-Guaporé")));

	public static final String FILE_CONTENT = new StringBuilder()
		.append("ibge_id,uf,name,capital,lon,lat,no_accents,alternative_names,microregion,mesoregion\n")
		.append("1100015,RO,Alta Floresta D'Oeste,,-61.9998238963,-11.9355403048,Alta Floresta D'Oeste,,Cacoal,Leste Rondoniense\n")
		.append("1100023,RO,Ariquemes,,-63.033269278,-9.9084628666,Ariquemes,,Ariquemes,Leste Rondoniense\n")
		.append("2516409,PB,Tacima,,-35.6388366565,-6.4889169425,Tacima,Campo de Santana,Curimataú Oriental,Agreste Paraibano\n")
		.append("1100205,RO,Porto Velho,true,-63.8314456544,-8.76889179,Porto Velho,,Porto Velho,Madeira-Guaporé")
		.toString();

}
