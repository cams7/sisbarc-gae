<%-- Pagina de edicao dos dados do LED. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:message code="label.editar" var="label_editar"
	htmlEscape="false" />
<spring:message code="msg.erro.led.inexistente" var="led_inexistente"
	htmlEscape="false" />

<div>
	<c:choose>
		<c:when test="${led != null}">
			<c:import url="/WEB-INF/views/led/form.jsp">
				<c:param name="action" value="editar_led" />
				<c:param name="method" value="PUT" />
				<c:param name="sublabel" value="${label_editar}" />
				<c:param name="createEntity" value="false" />
			</c:import>

			<form:form id="formExcluir"
				action="${pageContext.request.contextPath}/led/${led.id}"
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
			<h3>${led_inexistente}</h3>
		</c:otherwise>
	</c:choose>
</div>