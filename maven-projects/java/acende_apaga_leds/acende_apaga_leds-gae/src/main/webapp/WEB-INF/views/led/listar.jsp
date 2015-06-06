<%-- Pagina principal da aplicacao, a listagem de LEDs. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="label.sim" var="label_sim" htmlEscape="false" />
<spring:message code="label.nao" var="label_nao" htmlEscape="false" />

<spring:message code="label.leds" var="label_leds" htmlEscape="false" />
<spring:message code="label.listagem" var="label_listagem"
	htmlEscape="false" />

<spring:message code="label.led.tipo" var="label_led_tipo"
	htmlEscape="false" />
<spring:message code="label.led.pino" var="label_led_pino"
	htmlEscape="false" />
<spring:message code="label.led.evento" var="label_led_evento"
	htmlEscape="false" />
<spring:message code="label.led.intervalo" var="label_led_intervalo"
	htmlEscape="false" />
<spring:message code="label.led.cor" var="label_led_cor"
	htmlEscape="false" />
<spring:message code="label.led.ativo" var="label_led_ativo"
	htmlEscape="false" />
<spring:message code="label.led.ativadoPorBotao"
	var="label_led_ativadoPorBotao" htmlEscape="false" />

<spring:message code="label.editar" var="label_editar"
	htmlEscape="false" />
<spring:message code="button.atualizar" var="button_atualizar"
	htmlEscape="false" />


<div>
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>${label_leds}
			<small> ${label_listagem}</small>
		</h3>
	</div>

	<table class="table table-hover">
		<thead>
			<tr>
				<th>#</th>
				<th>${label_led_tipo}</th>
				<th>${label_led_pino}</th>
				<th>${label_led_evento}</th>
				<th>${label_led_intervalo}</th>
				<th>${label_led_cor}</th>
				<th>${label_led_ativo}</th>
				<th>${label_led_ativadoPorBotao}</th>
			</tr>
		</thead>
		<c:forEach items="${leds}" var="l">
			<tr>
				<td><spring:url
						value="${pageContext.request.contextPath}/led/${l.id}"
						var="edit_url" htmlEscape="true">
						<spring:param name="form" />
					</spring:url> <a href="${edit_url}"
					title="${label_editar} ${l.pino.tipo} ${l.pino.codigo}">${l.id}</a>
				</td>
				<td>${l.pino.tipo}</td>
				<td>${l.pino.codigo}</td>
				<td>${l.evento}</td>
				<td>${l.intervalo}</td>
				<td>${l.cor}</td>
				<td><c:choose>
						<c:when test="${l.ativo}">${label_sim}</c:when>
						<c:otherwise>${label_nao}</c:otherwise>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${l.ativadoPorBotao}">${label_sim}</c:when>
						<c:otherwise>${label_nao}</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>

	<form:form id="atualizaLEDs"
		action="${pageContext.request.contextPath}/led/synch" method="GET">
		<div class="control-group">
			<div class="controls">
				<button id="salvar" class="btn btn-success">${button_atualizar}</button>
			</div>
		</div>
	</form:form>
</div>