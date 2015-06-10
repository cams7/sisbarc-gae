<%-- Pagina de edicao dos dados do LED. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:message code="label.editar" var="label_editar"
	htmlEscape="false" />
<spring:message code="msg.erro.potenciometro.inexistente"
	var="potenciometro_inexistente" htmlEscape="false" />

<div>
	<c:choose>
		<c:when test="${potenciometro != null}">
			<c:import url="/WEB-INF/views/potenciometro/form.jsp">
				<c:param name="action" value="editar_potenciometro" />
				<c:param name="method" value="PUT" />
				<c:param name="sublabel" value="${label_editar}" />
				<c:param name="createEntity" value="false" />
			</c:import>

			<form:form id="formExcluir"
				action="${pageContext.request.contextPath}/potenciometro/${potenciometro.id}"
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
			<div class="alert alert-warning" role="alert">${potenciometro_inexistente}</div>
		</c:otherwise>
	</c:choose>
</div>