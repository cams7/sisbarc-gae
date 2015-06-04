<%-- Pagina de edicao de usuario. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<spring:message code="label.editar" var="label_editar"
		htmlEscape="false" />
	<spring:message code="msg.erro.usuario.inexistente"
		var="usuario_inexistente" htmlEscape="false" />

	<c:choose>
		<c:when test="${usuario != null}">
			<c:import url="/WEB-INF/views/usuario/fullform.jsp">
				<c:param name="action" value="editarUsuario" />
				<c:param name="method" value="PUT" />
				<c:param name="sublabel" value="${label_editar}" />
				<c:param name="enableRemove" value="true" />
			</c:import>

			<form:form id="formExcluir"
				action="${pageContext.request.contextPath}/usuario/${usuario.id}"
				method="DELETE" />

			<script>
				$(document).ready(function() {
					$("#btnExcluir").click(function() {
						$("#formExcluir").submit();
					});
				});
			</script>
		</c:when>
		<c:otherwise>
			<h3>${usuario_inexistente}</h3>
		</c:otherwise>
	</c:choose>
</div>