<html>
<head>
	<title>Criar Trigger</title>
	
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.css">
</head>
<body>       
	<div class="container">
	  <h4>Criar Trigger</h4>
	  
	  <form action="saveTrigger" method="post">
		  <div class="form-group">
		    <label for="triggerName" class="control-label">Nome:</label>
		    <input type="text" class="form-control" id="triggerName" name="triggerName" style="min-width:10px;" placeholder="Digite o nome da Trigger">
		  </div>
		  
		  <div class="form-group">
		    <label for="triggerGroup" class="control-label">Grupo:</label>
		    <input type="text" class="form-control" id="triggerGroup" name="triggerGroup" placeholder="Digite o grupo da Trigger">
		  </div>
		  
		  <div class="form-group">
		    <label for="triggerDescription" class="control-label">Descrição:</label>
		    <textarea class="form-control" name="triggerDescription" rows="5" cols="100" placeholder="Digite uma descrição para a Trigger"></textarea><br>
		  </div>
		  
		  <div class="form-group">
		    <label for="paramJson" class="control-label">jSON Parâmetros:</label>
		    <textarea class="form-control" name="paramJson" rows="5" cols="100" placeholder="Digite os parametros para a Trigger"></textarea><br>
		  </div>		  		   

		  <div class="form-group">
		    <label for="jobName" class="control-label">Job:</label>
		    <input type="text" class="form-control" id="jobName" name="jobName" style="min-width:10px;" placeholder="Digite o nome do Job o qual a trigger será vinculada">
		  </div>
		  
		  <div class="form-group">
		    <label for="jobGroup" class="control-label">Grupo:</label>
		    <input type="text" class="form-control" id="jobGroup" name="jobGroup" placeholder="Digite o grupo do Job citado anteriormente">
		  </div>
		  		  
		  <button type="submit" class="btn btn-primary">Adicionar</button>
	  </form>
	</div>    
</body>
</html>