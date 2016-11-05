<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Lista de Jobs</title>

<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<h4>Jobs</h4>
		
		<div class="table-responsive">
			<table class="table">
				<tr>
					<th>Nome</th>
					<th>Grupo</th>
					<th>Operações</th>
				</tr>
				<c:forEach items="${jobs}" var="job">
					<tr>
						<td>${job.jobName}</td>
						<td>${job.jobGroup}</td>

						<td><a href="removeJob?jobName=${job.jobName}&jobGroup=${job.jobGroup}">Remover</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>