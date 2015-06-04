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
<spring:message code="label.usuario.username"
	var="label_usuario_username" htmlEscape="false" />
<spring:message code="label.usuario.email" var="label_usuario_email"
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

	<form:hidden path="id" />
	<form:hidden path="enabled" />
	<form:hidden path="authorities" />

	<fieldset>
		<legend>
			${label_usuario} <small>${label_incluir}</small>
		</legend>

		<div class="control-group">
			<label class="control-label">${label_usuario_username}</label>
			<div class="controls">
				<form:input path="username" cssClass="input-large" />
				<form:errors path="username"
					cssClass="alert alert-error input-alert" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">${label_usuario_email}</label>
			<div class="controls">
				<form:input path="email" cssClass="input-large" />
				<form:errors path="email" cssClass="alert alert-error input-alert" />
			</div>
		</div>
	</fieldset>

	<div class="control-group controls">
		<input type="submit" value="${button_salvar}">
	</div>
</form:form>