# API Deposito De Bebidas

### Apresentação

Esta é uma API RESTfull criada em resposta a um desafio, que tem como proposta gerir dados de armazenamento e estoque de um depósito de bebidas. Inicialmente a API possui dois tipos de bebidas (alcoólicas e não alcoólicas) e 5 seções, sendo possível acrescentar novos tipos de bebidas e aumentar o numero de seções.

A API é responsável gerenciar:
 - Cadastro e consulta das bebidas armazenadas em cada seção.
 - Consulta do volume total no estoque por cada tipo de bebida.
 - Consulta das seções disponíveis de armazenamento de um determinado volume de bebida.
 - Consulta das seções disponíveis para venda de determinado tipo de bebida.
 - Cadastro de histórico de entrada e saída de bebidas em caso de venda e recebimento.
 - Consulta do histórico de entradas e saídas por tipo de bebida e seção.

A API tem as seguintes regras:
- Uma seção não pode ter dois ou mais tipos diferentes de bebidas.
- Não há entrada ou saída de estoque sem respectivo registro no histórico.
- Registro deve conter horário, tipo, volume, seção e responsável pela entrada ou saída.
- A consulta de histórico de entrada e saída de estoque, deve retornar os resultados ordenados por data e seção, podendo alterar a ordenação via parâmetros.
- Para situações de erro, e necessário que a resposta da requisição seja coerente em exibir uma mensagem condizente com o erro.
- Não é possível armazenar ou manter bebidas alcoólicas e não alcoólicas juntas. 
- Cada seção possui capacidade de armazenamento de 500 litros de bebidas alcoólicas e 400 de não alcoólicas.

### Descrições técnicas
Para o desenvolvimento desta solução, foi utilizado:
 - A plataforma Java, versão 8.
 - Framework Spring Boot, para criação do serviço.
 - Swagger para documentação da API.
 - HSQLDB para persistência, banco de dados em memória.
 - Maven como gerenciador de dependências.
 - Spring Tool Suite como ambiente de desenvolvimento.

### Projeto
    Serviços:
 - **tipobebida**, responsável por cadastrar, consultar e listar os tipos de bebidas.
 - **secao**, para cadastrar, consultar e listar as seções de armazenamento de bebidas.
 - **estoque**, responsável pelo gerenciamento de entrada e saída de bebidas, bem como a consulta de disponibilidade para venda e espaço de armazenamento.
 - **historico**, para consultar as movimentações do deposito.
#
    Modelos para persistencia de dados:
 - **TipoBebidaModel**, com id, nome e capacidade máxima de armazenamento.
 - **SecaoModel**, id, nome, id do tipo de bebida armazenada e volume de bebida armazenada.
 - **HistoricoModel**, id, evento de entrada ou saída do estoque, responsável pelo evento, id da seção, volume do evento, id do tipo bebida, data e hora.
 
As configurações da aplicação, como porta e configurações do banco, estão no arquivo:
`deposito-service/src/main/resources/application.properties`

Após baixar, compilar e iniciar aplicação, a documentação gerada pelo Swagger, pode ser acessada pelo link:
http://localhost:8090/swagger-ui.html