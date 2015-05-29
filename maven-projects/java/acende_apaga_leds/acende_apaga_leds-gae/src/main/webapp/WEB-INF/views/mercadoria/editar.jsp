<%-- Pagina de edicao de mercadoria. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<spring:message code="label.editar" var="label_editar"
		htmlEscape="false" />
	<spring:message code="msg.erro.mercadoria.inexistente"
		var="mercadoria_inexistente" htmlEscape="false" />

	<c:choose>
		<c:when test="${mercadoria != null}">
			<c:import url="/WEB-INF/views/mercadoria/formulario.jsp">
				<c:param name="action" value="editarMercadoria" />
				<c:param name="method" value="PUT" />
				<c:param name="sublabel" value="${label_editar}" />
				<c:param name="enableRemove" value="true" />
			</c:import>

			<form:form id="formExcluir"
				action="${pageContext.request.contextPath}/mercadoria/${mercadoria.id}"
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
			<h3>${mercadoria_inexistente}</h3>
		</c:otherwise>
	</c:choose>
</div>