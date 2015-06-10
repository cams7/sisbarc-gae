<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<spring:message code="label.usuario" var="label_usuario"
	htmlEscape="false" />
<spring:message code="label.incluir" var="label_incluir"
	htmlEscape="false" />

<spring:message code="button.salvar" var="button_salvar"
	htmlEscape="false" />

<form:form method="POST" modelAttribute="usuario"
	action="${pageContext.request.contextPath}/incluir_usuario"
	cssClass="form-horizontal">

	<form:hidden path="enabled" />
	<form:hidden path="authorities" />

	<fieldset>
		<legend>
			${label_usuario} <small>${label_incluir}</small>
		</legend>

		<c:import url="/WEB-INF/views/usuario/form.jsp" />
	</fieldset>

	<div class="control-group">
		<div class="controls">
			<input type="submit" class="btn btn-success" value="${button_salvar}">
		</div>
	</div>
</form:form>