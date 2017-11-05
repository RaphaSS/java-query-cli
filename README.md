# java-query-cli
Interface de linha de comandos para pesquisas em Java.


## Modo de uso
Para iniciar o programa, basta iniciá-lo com o seguinte comando:
```
java -jar java-query-cli.jar /path/to/file.csv
```
Com o programa iniciado, é possível realizar 3 consultas:
* __count *__ → Conta todos os elementos contidos na fonte de dados.
* __count distinct [propriedade]__ → Conta os elementos contidos na fonte de dados fazendo distinção da propriedade especificada.
* __filter [propriedade] [valor]__ → Filtra os elementos contidos na fonte de dados onde a propriedade especificada seja igual ao valor especificado.


## Limitações
Atualmente, o único tipo de fonte de dados suportado são arquivos no formato _CSV_.

## Aviso sobre os testes unitários
O teste de escrita dos elementos apresenta falha na execução devido a problemas com codificação de caracteres. Ao imprimir os dados no console, tudo ocorre normalmente.
