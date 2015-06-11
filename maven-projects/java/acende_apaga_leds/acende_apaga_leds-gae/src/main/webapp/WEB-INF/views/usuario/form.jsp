<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:message code="label.usuario.googleId"
	var="label_usuario_googleId" htmlEscape="false" />
<spring:message code="label.usuario.email" var="label_usuario_email"
	htmlEscape="false" />
<spring:message code="label.usuario.ip" var="label_usuario_ip"
	htmlEscape="false" />
<spring:message code="label.usuario.port" var="label_usuario_port"
	htmlEscape="false" />

<form:hidden path="id" />

<div class="control-group">
	<label class="control-label">${label_usuario_googleId}</label>
	<div class="controls">
		<form:input path="googleId" cssClass="input-large" readonly="true" />
	</div>
</div>

<div class="control-group">
	<label class="control-label">${label_usuario_email}</label>
	<div class="controls">
		<form:input path="email" cssClass="input-large" readonly="true" />
	</div>
</div>

<div class="control-group">
	<label class="control-label">${label_usuario_ip}</label>
	<div class="controls">
		<form:input path="ip" cssClass="input-large" />
		<form:errors path="ip" cssClass="alert alert-error input-alert" />
	</div>
</div>

<div class="control-group">
	<label class="control-label">${label_usuario_port}</label>
	<div class="controls">
		<form:input path="port" cssClass="input-small" />
		<form:errors path="port" cssClass="alert alert-error input-alert" />
	</div>
</div>