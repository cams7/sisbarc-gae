<%-- Pagina de edicao de usuario. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:message code="label.editar" var="label_editar"
	htmlEscape="false" />
<spring:message code="msg.erro.usuario.inexistente"
	var="usuario_inexistente" htmlEscape="false" />

<spring:message code="button.salvar" var="button_salvar"
	htmlEscape="false" />
<spring:message code="button.cancelar" var="button_cancelar"
	htmlEscape="false" />
<spring:message code="button.excluir" var="button_excluir"
	htmlEscape="false" />

<spring:message code="label.sim" var="label_sim" htmlEscape="false" />
<spring:message code="label.nao" var="label_nao" htmlEscape="false" />

<spring:message code="label.usuario" var="label_usuario"
	htmlEscape="false" />

<spring:message code="label.usuario.authorities"
	var="label_usuario_authorities" htmlEscape="false" />
<spring:message code="label.usuario.enabled" var="label_usuario_enabled"
	htmlEscape="false" />

<div>
	<c:choose>
		<c:when test="${usuario != null}">
			<c:import url="/WEB-INF/views/message.jsp" />

			<form:form id="usuarioForm" modelAttribute="usuario"
				method="${isLoginPage?'POST':'PUT'}"
				action="${pageContext.request.contextPath}/editar_usuario"
				cssClass="form-horizontal">

				<fieldset>
					<legend>
						${label_usuario} <small>"${label_editar}</small>
					</legend>

					<c:import url="/WEB-INF/views/usuario/form.jsp" />


					<div class="control-group">
						<label class="control-label">${label_usuario_authorities}</label>
						<div class="controls">
							<c:if test="${isLoginPage}" var="authoritiesDisabled">
								<form:hidden path="authorities" />
							</c:if>
							<form:checkboxes items="${roles}" path="authorities"
								disabled="${authoritiesDisabled}" />
						</div>
					</div>


					<div class="control-group">
						<label class="control-label">${label_usuario_enabled}</label>
						<div class="controls">
							<c:if test="${isLoginPage}" var="enabledDisabled">
								<form:hidden path="enabled" />
							</c:if>

							<form:radiobutton path="enabled" value="true"
								disabled="${enabledDisabled}" />
							${label_sim}
							<form:radiobutton path="enabled" value="false"
								disabled="${enabledDisabled}" />
							${label_nao}
						</div>
					</div>
				</fieldset>
			</form:form>

			<div class="control-group form-horizontal">
				<div class="controls">
					<button id="btnSalvar" class="btn btn-success">${button_salvar}</button>
					<a href="${pageContext.request.contextPath}/usuario"><button
							class="btn">${button_cancelar}</button></a>
					<c:if test="${not isLoginPage}">
						<button id="btnExcluir" class="btn btn-danger">${button_excluir}</button>
					</c:if>
				</div>
			</div>

			<script>
				$(document).ready(function() {
					$("#btnSalvar").click(function() {
						$("#usuarioForm").submit();
					});
				});
			</script>

			<c:if test="${not isLoginPage}">
				<form:form id="formExcluir"
					action="${pageContext.request.contextPath}/usuario/${usuario.id}"
					method="DELETE" />

				<script>
					$(document).ready(function() {
						$("#btnExcluir").click(function() {
							$("#formExcluir").submit();
						});
					});
				</script>
			</c:if>
		</c:when>
		<c:otherwise>
			<div class="alert alert-warning" role="alert">${usuario_inexistente}</div>
		</c:otherwise>
	</c:choose>
</div>