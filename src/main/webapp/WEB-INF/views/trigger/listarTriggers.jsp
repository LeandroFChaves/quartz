<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Lista de Triggers</title>

<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<h4>Triggers</h4>
		
		<div class="table-responsive">
			<table class="table">
				<tr>
					<th>Nome</th>
					<th>Grupo</th>
					<th>Operações</th>
				</tr>
				<c:forEach items="${triggers}" var="trigger">
					<tr>
						<td>${trigger.triggerName}</td>
						<td>${trigger.triggerGroup}</td>

						<td><a
							href="removeTrigger?triggerName=${trigger.triggerName}&triggerGroup=${trigger.triggerGroup}">Remover</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>