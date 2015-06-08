<%-- Pagina principal da aplicacao, a listagem de LEDs. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="label.sim" var="label_sim" htmlEscape="false" />
<spring:message code="label.nao" var="label_nao" htmlEscape="false" />

<spring:message code="label.potenciometros" var="label_potenciometros"
	htmlEscape="false" />
<spring:message code="label.listagem" var="label_listagem"
	htmlEscape="false" />

<spring:message code="label.potenciometro.tipo"
	var="label_potenciometro_tipo" htmlEscape="false" />
<spring:message code="label.potenciometro.pino"
	var="label_potenciometro_pino" htmlEscape="false" />
<spring:message code="label.potenciometro.evento"
	var="label_potenciometro_evento" htmlEscape="false" />
<spring:message code="label.potenciometro.intervalo"
	var="label_potenciometro_intervalo" htmlEscape="false" />

<spring:message code="label.editar" var="label_editar"
	htmlEscape="false" />

<spring:message code="button.atualizar" var="button_atualizar"
	htmlEscape="false" />
<spring:message code="button.arduino.update" var="button_arduino_update"
	htmlEscape="false" />
<spring:message code="button.arduino.synchronize"
	var="button_arduino_synchronize" htmlEscape="false" />


<c:import url="/WEB-INF/views/message.jsp" />

<div>
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>${label_potenciometros}
			<small> ${label_listagem}</small>
		</h3>
	</div>

	<table class="table table-hover">
		<thead>
			<tr>
				<th>#</th>
				<th>${label_potenciometro_tipo}</th>
				<th>${label_potenciometro_pino}</th>
				<th>${label_potenciometro_evento}</th>
				<th>${label_potenciometro_intervalo}</th>
			</tr>
		</thead>
		<c:forEach items="${potenciometros}" var="p">
			<tr>
				<td><spring:url
						value="${pageContext.request.contextPath}/potenciometro/${p.id}"
						var="edit_url" htmlEscape="true">
						<spring:param name="form" />
					</spring:url> <a href="${edit_url}"
					title="${label_editar} ${p.pino.tipo} ${p.pino.codigo}">${p.id}</a>
				</td>
				<td>${p.pino.tipo}</td>
				<td>${p.pino.codigo}</td>
				<td>${p.evento}</td>
				<td>${p.intervalo}</td>
			</tr>
		</c:forEach>
	</table>

	<div class="control-group">
		<div class="controls">
			<button id="btnSynch" class="btn btn-inverse">${button_atualizar}</button>
			<button id="btnAtualiza" class="btn btn-success">${button_arduino_update}</button>
			<button id="btnSincroniza" class="btn btn-success">${button_arduino_synchronize}</button>
		</div>
	</div>

	<form:form id="formSynch"
		action="${pageContext.request.contextPath}/potenciometro/synch"
		method="GET" />
	<form:form id="formAtualiza"
		action="${pageContext.request.contextPath}/atualizar_potenciometros"
		method="PUT" />
	<form:form id="formSincroniza"
		action="${pageContext.request.contextPath}/sincronizar_potenciometros"
		method="PUT" />

	<script>
		$(document).ready(function() {
			$("#btnSynch").click(function() {
				$("#formSynch").submit();
			});
			$("#btnAtualiza").click(function() {
				$("#formAtualiza").submit();
			});
			$("#btnSincroniza").click(function() {
				$("#formSincroniza").submit();
			});
		});
	</script>

</div>