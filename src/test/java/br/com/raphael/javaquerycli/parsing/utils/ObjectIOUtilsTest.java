package br.com.raphael.javaquerycli.parsing.utils;

import static br.com.raphael.javaquerycli.JavaQueryCLITest.ALL_CITIES;
import static br.com.raphael.javaquerycli.JavaQueryCLITest.FILE_CONTENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.raphael.javaquerycli.model.City;

public class ObjectIOUtilsTest {

    private static File file;

    @BeforeClass
    public static void setUp() {
        final String datasetFile = "data/test.csv";
        file = new File(datasetFile);
    }

    @Test
    public void leArquivo() {
        try(InputStream inputStream = new FileInputStream(file)) {
            final List<City> readCities = ObjectIOUtils.read(inputStream, City.class);

            assertThat(readCities).isEqualTo(ALL_CITIES);
        } catch(final IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void escreveLista() {
        final OutputStream outputStream = new ByteArrayOutputStream();
        ObjectIOUtils.write(ALL_CITIES, outputStream, City.class);
        final String actual = outputStream.toString();

        assertThat(actual).isEqualTo(FILE_CONTENT);
    }

}
