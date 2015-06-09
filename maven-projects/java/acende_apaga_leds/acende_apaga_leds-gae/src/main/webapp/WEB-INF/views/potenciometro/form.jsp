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

<spring:message code="label.potenciometro" var="label_potenciometro"
	htmlEscape="false" />

<spring:message code="label.potenciometro.tipo"
	var="label_potenciometro_tipo" htmlEscape="false" />
<spring:message code="defaultValue.potenciometro.tipo"
	var="defaultValue_potenciometro_tipo" htmlEscape="false" />
<spring:message code="label.potenciometro.pino"
	var="label_potenciometro_pino" htmlEscape="false" />
<spring:message code="label.potenciometro.evento"
	var="label_potenciometro_evento" htmlEscape="false" />
<spring:message code="defaultValue.potenciometro.evento"
	var="defaultValue_potenciometro_evento" htmlEscape="false" />
<spring:message code="label.potenciometro.intervalo"
	var="label_potenciometro_intervalo" htmlEscape="false" />
<spring:message code="defaultValue.potenciometro.intervalo"
	var="defaultValue_potenciometro_intervalo" htmlEscape="false" />

<c:import url="/WEB-INF/views/message.jsp" />

<form:form id="potenciometroForm" modelAttribute="potenciometro"
	method="${param.method}"
	action="${pageContext.request.contextPath}/${param.action}"
	cssClass="form-horizontal">

	<form:hidden path="id" />
	<form:hidden path="alteraEvento" />
	<form:hidden path="alteraIntervalo" />

	<input type="hidden" name="userId" value="${potenciometro.user.key.id}">

	<fieldset>
		<legend>
			${label_potenciometro} <small>${param.sublabel}</small>
		</legend>

		<c:choose>
			<c:when test="${param.createEntity}">
				<!-- Pino -->
				<div class="control-group">
					<label class="control-label">${label_potenciometro_tipo}</label>
					<div class="controls">
						<form:select path="pino.tipo">
							<form:option value="${null}"
								label="${defaultValue_potenciometro_tipo}..." />
							<form:options items="${tipos}" />
						</form:select>
						<form:errors path="pino.tipo"
							cssClass="alert alert-error input-alert" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">${label_potenciometro_pino}</label>
					<div class="controls">
						<form:input path="pino.codigo" cssClass="input-small" />
						<form:errors path="pino.codigo"
							cssClass="alert alert-error input-alert" />
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="control-group">
					<label class="control-label">${label_potenciometro_pino}</label>
					<div class="controls">${potenciometro.pino.tipo}&nbsp;${potenciometro.pino.codigo}</div>

					<form:hidden path="pino.tipo" />
					<form:hidden path="pino.codigo" />
				</div>
			</c:otherwise>
		</c:choose>

		<!-- Evento -->
		<div class="control-group">
			<label class="control-label">${label_potenciometro_evento}</label>
			<div class="controls">
				<c:choose>
					<c:when test="${potenciometro.alteraEvento}">
						<form:select path="evento">
							<form:option value="${null}"
								label="${defaultValue_potenciometro_evento}..." />
							<form:options items="${eventos}" />
						</form:select>
						<form:errors path="evento"
							cssClass="alert alert-error input-alert" />
					</c:when>
					<c:otherwise>
						<span>${potenciometro.evento}</span>

						<form:hidden path="evento" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>


		<!-- Intervalo -->
		<div class="control-group">
			<label class="control-label">${label_potenciometro_intervalo}</label>
			<div class="controls">
				<c:choose>
					<c:when test="${potenciometro.alteraIntervalo}">
						<form:select path="intervalo">
							<form:option value="${null}"
								label="${defaultValue_potenciometro_intervalo}..." />
							<form:options items="${intervalos}" />
						</form:select>
						<form:errors path="intervalo"
							cssClass="alert alert-error input-alert" />
					</c:when>
					<c:otherwise>
						<span>${potenciometro.intervalo}</span>

						<form:hidden path="intervalo" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</fieldset>
</form:form>

<div class="control-group form-horizontal">
	<div class="controls">
		<button id="btnSalvar" class="btn btn-success">${button_salvar}</button>
		<a href="${pageContext.request.contextPath}/potenciometro"><button
				class="btn">${button_cancelar}</button></a>
		<c:if test="${not param.createEntity}">
			<button id="btnExcluir" class="btn btn-danger">${button_excluir}</button>
		</c:if>
	</div>
</div>

<script>
	$(document).ready(function() {
		$("#btnSalvar").click(function() {
			$("#potenciometroForm").submit();
		});
	});
</script>