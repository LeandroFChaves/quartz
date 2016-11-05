<html>
<head>
	<title>Exemplo Quartz</title>
	
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/pagPrincipal.css">
</head>
<body>

	<!-- Menu -->
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
		    	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		      		<span class="sr-only">Toggle navigation</span>
		      		<span class="icon-bar"></span>
		      		<span class="icon-bar"></span>
		    	</button>
		    	<a class="navbar-brand" href="#">Quartz</a>
			</div>
		
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Jobs<span class="caret"></span></a>
		        	<ul class="dropdown-menu">
		                <li><a href="/quartz/newJob" target="interno">Criar Job</a></li> 
		                <li><a href="/quartz/listJobs" target="interno">Listar Jobs</a></li>
		          	</ul>
				</ul>
				
				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Triggers<span class="caret"></span></a>
		        	<ul class="dropdown-menu">
		                <li><a href="/quartz/newTrigger" target="interno">Criar Trigger</a></li>
		                <li><a href="/quartz/listTriggers" target="interno">Listar Triggers</a></li>
		          	</ul>
				</ul>
			</div>
		</div>
	</nav>
		
	<h4>Exemplo da Utilização do Quartz</h4>

	<div>
		<iframe name="interno"
				seamless></iframe>
	</div>
	
	<script src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/js/bootstrap.js"></script>
</body>
</html>