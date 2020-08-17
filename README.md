# java-query-cli

Command line interface for searches in Java.


## Compiling the application

1. I order to compile the application, you must have [Apache Maven](https://maven.apache.org/install.html) installed and configured;
2. In the source route, execute the command `mvn package`;
3. The step 2 will produce a runnable JAR inside the `/target` folder.


## Usage

To start the application, you must run the jar, supllying it with a compatible data source, such as below:
```
java -jar target/java-query-cli.jar data/cidades.csv
```

Once the application is up and running, you can perform 3 queries:
* `count *` → Counts all the entries in the data source;
* `count distinct PROPERTY` → Counts the entries in the data source, distincting them by the specified property;
   * Ex: `count distinct name`
* `filter PROPERTY VALUE` → Filters the entries in a data source where `PROPERTY` equals `VALUE`;
   * Ex: `filter uf SC`
   * Ex: `filter mesoregion 'Grande Florianópolis'`


## Limitations

Currently, the only data source supported type are CSV formatted files. The file must be formatted following the specifications below:
- Value separator: `,`
- No value delimiter
- Fields names, order and type:
  1. `ibge_id: int`
  2. `uf: string`
  3. `name: string`
  4. `capital: boolean` (_true_ or _false_)
  5. `lon: number`
  6. `lat: number`
  7. `no_accents: string`
  8. `alternative_names: string`
  9. `microregion: string`
  10. `mesoregion: string`
