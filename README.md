#Quartz

Exemplo da utilização do framework quartz com banco de dados SQLServer utilizando agendamento de tarefas(jobs) de forma dinâmica.

1 - Rodar os scripts de criação das tabelas do Quartz correspondente ao banco que estiver usando. Nesse exemplo estamos utilizando o SQLServer, porém os scripts para os outros bancos se encontra na pasta correspondente.

2 - Na criação do job o nome deve ser o mesmo nome da classe que foi desenvolvida, pois é realizada uma conversão em um dos métodos onde utiliza esse nome. Caso tenha alguma dúvida observe o método criaEditaJob.
OBS.: Para cada novo job que for criado deve ser desenvolvida uma classe correspondente na package br.com.quartz.jobs e uma classe com os seus parâmetros br.com.quartz.jobs.params

3 - Na estrutura que criamos nesse projeto todos os jobs desenvolvidos devem ficar dentro do pacote br.com.quartz.jobs

4 - Na criação das triggers todos os parâmetros necessários para execução da trigger devem ser passados via jSON na estrutura chave:valor no campo jSON Parâmetros. Nesse projeto existe um .doc com prints de como os campos devem ser preenchidos.

As tecnologias usadas foram:
	Backend   - Java, Spring MVC, Quartz, Hibernate, Jackson
	FrontEnd  - HTML, CSS, Bootstrap
	
Nesse projeto contém um exemplo de job que realiza o envio de emails e um que imprime um valor no console. 

OBS.: O exemplo está simples focando apenas na utilização do framework QUARTZ.

Link da documentação do framework -> http://www.quartz-scheduler.org/documentation/