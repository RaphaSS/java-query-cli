# java-query-cli
Interface de linha de comandos para pesquisas em Java.


## Compilando o projeto
1. Para compilar o projeto, é necessário ter o [Apache Maven](https://maven.apache.org/install.html) configurado;
2. Na raiz do projeto, executar o comando `mvn package`:
   * Nesse momento os testes unitários já serão executados. Caso deseje-se executá-los novamente, basta executar o comando `mvn test`
3. O comando executado no item 2 terá gerado um JAR executável dentro da pasta _target_.


## Modo de uso
Para iniciar o programa, partindo-se da pasta do projeto, basta iniciá-lo com o seguinte comando:
```
java -jar target/java-query-cli.jar data/cidades.csv
```

:exclamation: O arquivo fonte de dados não precisa ser o mesmo já presente no projeto. Caso deseje-se, pode ser utilizado um outro arquivo no mesmo formato.

Com o programa iniciado, é possível realizar 3 consultas:
* __count *__ → Conta todos os elementos contidos na fonte de dados.
* __count distinct [propriedade]__ → Conta os elementos contidos na fonte de dados fazendo distinção da propriedade especificada.
   * _Ex: `count distinct name`_
* __filter [propriedade] [valor]__ → Filtra os elementos contidos na fonte de dados onde a propriedade especificada seja igual ao valor especificado (caso o valor tenha mais de uma palavra, é preciso cercá-lo com aspas simples).
   * _Ex: `filter uf SC`_
   * _Ex: `filter mesoregion 'Grande Florianópolis'`_


## Limitações
Atualmente, o único tipo de fonte de dados suportado são arquivos no formato _CSV_.
