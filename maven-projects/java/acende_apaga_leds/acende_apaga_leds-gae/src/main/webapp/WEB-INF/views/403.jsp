<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="msg.erro.sem.permissao" var="msg_sem_permissao"
	htmlEscape="false" />

<div class="alert alert-warning" role="alert">${msg_sem_permissao}!</div>