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

<p>
	Bem vindo,
	<sec:authentication property="principal.email" />
</p>

<form:form method="POST" modelAttribute="usuario"
	action="${pageContext.request.contextPath}/cadastrarLogin"
	cssClass="form-horizontal">

	<form:hidden path="enabled" />
	<form:hidden path="authorities" />

	<fieldset>
		<legend>
			${label_usuario} <small>${label_incluir}</small>
		</legend>

		<c:import url="/WEB-INF/views/usuario/form.jsp">
			<c:param name="emailReadonly" value="true" />
		</c:import>
	</fieldset>

	<div class="control-group controls">
		<input type="submit" value="${button_salvar}">
	</div>
</form:form>