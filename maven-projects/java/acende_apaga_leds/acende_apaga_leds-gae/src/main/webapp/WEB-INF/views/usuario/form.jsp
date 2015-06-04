<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:message code="label.usuario.username"
	var="label_usuario_username" htmlEscape="false" />
<spring:message code="label.usuario.email" var="label_usuario_email"
	htmlEscape="false" />
<spring:message code="label.usuario.ip" var="label_usuario_ip"
	htmlEscape="false" />
<spring:message code="label.usuario.port" var="label_usuario_port"
	htmlEscape="false" />
	
<form:hidden path="id" />

<div class="control-group">
	<label class="control-label">${label_usuario_username}</label>
	<div class="controls">
		<form:input path="username" cssClass="input-large" />
		<form:errors path="username" cssClass="alert alert-error input-alert" />
	</div>
</div>

<div class="control-group">
	<label class="control-label">${label_usuario_email}</label>
	<div class="controls">
		<form:input path="email" cssClass="input-large"
			readonly="${param.emailReadonly}"/>
		<form:errors path="email" cssClass="alert alert-error input-alert" />
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