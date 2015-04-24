<%-- Pagina de edicao de mercadoria. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<spring:message code="label.editar" var="label_editar"
		htmlEscape="false" />
	<spring:message code="msg.erro.mercadoria.inexistente"
		var="mercadoria_inexistente" htmlEscape="false" />

	<c:if test="${funcionario != null}" var="temFuncionario">
		<c:import url="/WEB-INF/views/formFuncionario.jsp">
			<c:param name="action" value="editar" />
			<c:param name="method" value="POST" />
			<c:param name="sublabel" value="${label_editar}" />
			<c:param name="enableRemove" value="true" />
		</c:import>

		<form:form id="deleteFuncionario" action="" method="DELETE"></form:form>

		<script>
			$(document).ready(function() {
				$("#excluir").click(function() {
					$("#deleteFuncionario").submit();
				});
			});
		</script>
	</c:if>
	<c:if test="${!temFuncionario}">
		<h3>${mercadoria_inexistente}</h3>
	</c:if>

</div>