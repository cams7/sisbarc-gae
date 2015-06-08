<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="msg.erro.conta.desativada"
	var="msg_conta_desativada" htmlEscape="false" />

<div class="alert alert-warning" role="alert">${msg_conta_desativada}...</div>