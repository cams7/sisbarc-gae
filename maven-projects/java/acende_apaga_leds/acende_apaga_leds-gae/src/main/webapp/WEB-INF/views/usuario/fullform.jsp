<%-- 
     Fragmento com o formulario de preenchimento com os dados da usuario.
     Utilizado pela pagina de inclusao e edicao de usuario.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<form:form id="usuarioForm" modelAttribute="usuario"
	method="${param.method}"
	action="${pageContext.request.contextPath}/${param.action}"
	cssClass="form-horizontal">

	<fieldset>
		<legend>
			${label_usuario} <small>${param.sublabel}</small>
		</legend>

		<c:import url="/WEB-INF/views/usuario/form.jsp">
			<c:param name="emailReadonly" value="false" />
		</c:import>

		<div class="control-group">
			<label class="control-label">${label_usuario_authorities}</label>
			<div class="controls">
				<form:checkboxes items="${roles}" path="authorities" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">${label_usuario_enabled}</label>
			<div class="controls">
				<form:radiobutton path="enabled" value="true" />
				${label_sim}
				<form:radiobutton path="enabled" value="false" />
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
		<c:if test="${not empty param.enableRemove}">
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