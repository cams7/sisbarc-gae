<%-- 
     Fragmento com o formulario de preenchimento com os dados do LED.
     Utilizado pela pagina de inclusao e edicao dos dados do LED.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<spring:message code="label.sim" var="label_sim" htmlEscape="false" />
<spring:message code="label.nao" var="label_nao" htmlEscape="false" />

<spring:message code="button.salvar" var="button_salvar"
	htmlEscape="false" />
<spring:message code="button.cancelar" var="button_cancelar"
	htmlEscape="false" />
<spring:message code="button.excluir" var="button_excluir"
	htmlEscape="false" />

<spring:message code="label.led" var="label_led" htmlEscape="false" />

<spring:message code="label.led.tipo" var="label_led_tipo"
	htmlEscape="false" />
<spring:message code="defaultValue.led.tipo" var="defaultValue_led_tipo"
	htmlEscape="false" />
<spring:message code="label.led.pino" var="label_led_pino"
	htmlEscape="false" />
<spring:message code="label.led.evento" var="label_led_evento"
	htmlEscape="false" />
<spring:message code="defaultValue.led.evento"
	var="defaultValue_led_evento" htmlEscape="false" />
<spring:message code="label.led.alteraEvento"
	var="label_led_alteraEvento" htmlEscape="false" />
<spring:message code="label.led.intervalo" var="label_led_intervalo"
	htmlEscape="false" />
<spring:message code="defaultValue.led.intervalo"
	var="defaultValue_led_intervalo" htmlEscape="false" />
<spring:message code="label.led.alteraIntervalo"
	var="label_led_alteraIntervalo" htmlEscape="false" />
<spring:message code="label.led.cor" var="label_led_cor"
	htmlEscape="false" />
<spring:message code="defaultValue.led.cor" var="defaultValue_led_cor"
	htmlEscape="false" />
<spring:message code="label.led.ativo" var="label_led_ativo"
	htmlEscape="false" />
<spring:message code="label.led.ativadoPorBotao"
	var="label_led_ativadoPorBotao" htmlEscape="false" />

<form:form id="ledForm" modelAttribute="led" method="${param.method}"
	action="${pageContext.request.contextPath}/${param.action}"
	cssClass="form-horizontal">
	<form:hidden path="id" />
	<form:hidden path="alteraEvento" />
	<form:hidden path="alteraIntervalo" />

	<fieldset>
		<legend>
			${label_led} <small>${param.sublabel}</small>
		</legend>

		<c:choose>
			<c:when test="${param.createEntity}">
				<!-- Pino -->
				<div class="control-group">
					<label class="control-label">${label_led_tipo}</label>
					<div class="controls">
						<form:select path="pino.tipo">
							<form:option value="${null}" label="${defaultValue_led_tipo}" />
							<form:options items="${tipos}" />
						</form:select>
						<form:errors path="pino.tipo"
							cssClass="alert alert-error input-alert" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">${label_led_pino}</label>
					<div class="controls">
						<form:input path="pino.codigo" cssClass="input-small" />
						<form:errors path="pino.codigo"
							cssClass="alert alert-error input-alert" />
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="control-group">
					<label class="control-label">${label_led_pino}</label>
					<div class="controls">${led.pino.tipo}&nbsp;${led.pino.codigo}</div>

					<form:hidden path="pino.tipo" />
					<form:hidden path="pino.codigo" />
				</div>
			</c:otherwise>
		</c:choose>

		<!-- Evento -->
		<div class="control-group">
			<label class="control-label">${label_led_evento}</label>
			<div class="controls">
				<c:choose>
					<c:when test="${led.alteraEvento}">
						<form:select path="evento">
							<form:option value="${null}" label="${defaultValue_led_evento}" />
							<form:options items="${eventos}" />
						</form:select>
						<form:errors path="evento"
							cssClass="alert alert-error input-alert" />
					</c:when>
					<c:otherwise>
						<span>${led.evento}</span>

						<form:hidden path="evento" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>


		<!-- Intervalo -->
		<div class="control-group">
			<label class="control-label">${label_led_intervalo}</label>
			<div class="controls">
				<c:choose>
					<c:when test="${led.alteraIntervalo}">
						<form:select path="intervalo">
							<form:option value="${null}"
								label="${defaultValue_led_intervalo}" />
							<form:options items="${intervalos}" />
						</form:select>
						<form:errors path="intervalo"
							cssClass="alert alert-error input-alert" />
					</c:when>
					<c:otherwise>
						<span>${led.intervalo}</span>

						<form:hidden path="intervalo" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<!-- Cor -->
		<div class="control-group">
			<label class="control-label">${label_led_cor}</label>
			<div class="controls">
				<c:choose>
					<c:when test="${param.createEntity}">
						<form:select path="cor">
							<form:option value="${null}" label="${defaultValue_led_cor}" />
							<form:options items="${cores}" />
						</form:select>
						<form:errors path="cor" cssClass="alert alert-error input-alert" />
					</c:when>
					<c:otherwise>
						<span>${led.cor}</span>

						<form:hidden path="cor" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<!-- Ativo -->
		<div class="control-group">
			<label class="control-label">${label_led_ativo}</label>
			<div class="controls">
				<form:radiobutton path="ativo" value="true"
					disabled="${not led.ativadoPorBotao}" />${label_sim}
				<form:radiobutton path="ativo" value="false"
					disabled="${not led.ativadoPorBotao}" />${label_nao}

				<c:if test="${not led.ativadoPorBotao}">
					<form:hidden path="ativo" />
				</c:if>
			</div>
		</div>

		<!-- Ativado por botao -->
		<div class="control-group">
			<label class="control-label">${label_led_ativadoPorBotao}</label>
			<div class="controls">
				<form:radiobutton path="ativadoPorBotao" value="true"
					disabled="${not param.createEntity}" />${label_sim}
				<form:radiobutton path="ativadoPorBotao" value="false"
					disabled="${not param.createEntity}" />${label_nao}

				<c:if test="${not param.createEntity}">
					<form:hidden path="ativadoPorBotao" />
				</c:if>
			</div>
		</div>
	</fieldset>
</form:form>

<div class="control-group form-horizontal">
	<div class="controls">
		<button id="btnSalvar" class="btn btn-success">${button_salvar}</button>
		<a href="${pageContext.request.contextPath}/led"><button
				class="btn">${button_cancelar}</button></a>
		<c:if test="${not param.createEntity}">
			<button id="btnExcluir" class="btn btn-danger">${button_excluir}</button>
		</c:if>
	</div>
</div>

<script>
	$(document).ready(function() {
		$("#btnSalvar").click(function() {
			$("#ledForm").submit();
		});
	});
</script>