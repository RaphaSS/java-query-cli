# java-query-cli
Interface de linha de comandos para pesquisas em Java


## Modo de uso
Com o programa iniciado, é possível realizar 3 consultas:
* __count *__ → Conta todos os elementos contidos na fonte de dados.
* __count distinct [propriedade]__ → Conta os elementos contidos na fonte de dados fazendo distinção da propriedade especificada.
* __filter [propriedade] [valor]__ → Filtra os elementos contidos na fonte de dados onde a propriedade especificada seja igual ao valor especificado.


## Limitações
Atualmente, o único tipo de fonte de dados suportado são arquivos no formato _CSV_.
