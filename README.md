#Quartz

Exemplo da utiliza��o do framework quartz com banco de dados SQLServer utilizando agendamento de tarefas(jobs) de forma din�mica.

1 - Rodar os scripts de cria��o das tabelas do Quartz correspondente ao banco que estiver usando. Nesse exemplo estamos utilizando o SQLServer, por�m os scripts para os outros bancos se encontra na pasta correspondente.

2 - Na cria��o do job o nome deve ser o mesmo nome da classe que foi desenvolvida, pois � realizada uma convers�o em um dos m�todos onde utiliza esse nome. Caso tenha alguma d�vida observe o m�todo criaEditaJob.
OBS.: Para cada novo job que for criado deve ser desenvolvida uma classe correspondente na package br.com.quartz.jobs e uma classe com os seus par�metros br.com.quartz.jobs.params

3 - Na estrutura que criamos nesse projeto todos os jobs desenvolvidos devem ficar dentro do pacote br.com.quartz.jobs

4 - Na cria��o das triggers todos os par�metros necess�rios para execu��o da trigger devem ser passados via jSON na estrutura chave:valor no campo jSON Par�metros. Nesse projeto existe um .doc com prints de como os campos devem ser preenchidos.

As tecnologias usadas foram:
	Backend   - Java, Spring MVC, Quartz, Hibernate, Jackson
	FrontEnd  - HTML, CSS, Bootstrap
	
Nesse projeto cont�m um exemplo de job que realiza o envio de emails e um que imprime um valor no console. 

OBS.: O exemplo est� simples focando apenas na utiliza��o do framework QUARTZ.

Link da documenta��o do framework -> http://www.quartz-scheduler.org/documentation/