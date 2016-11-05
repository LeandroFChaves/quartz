<html>
<head>
	<title>Criar Job</title>
	
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.css">
</head>
<body>  
  <div class="container">
  	  <h4>Criar Job</h4>
  	  
	  <form action="saveJob" method="post">
		  <div class="form-group">
		    <label for="jobName" class="control-label">Nome:</label>
		    <input type="text" class="form-control" id="jobName" name="jobName" style="min-width:10px;" placeholder="Digite o nome do Job">
		  </div>
		  
		  <div class="form-group">
		    <label for="jobGroup" class="control-label">Grupo:</label>
		    <input type="text" class="form-control" id="jobGroup" name="jobGroup" placeholder="Digite o grupo do Job">
		  </div>
		  
		  <div class="form-group">
		    <label for="description" class="control-label">Descrição:</label>
		    <textarea class="form-control" name="description" rows="5" cols="100" placeholder="Digite uma descrição para o Job"></textarea><br>
		  </div>		  
		  
		  <button type="submit" class="btn btn-primary">Adicionar</button>
	  </form>
	</div>
</body>
</html>