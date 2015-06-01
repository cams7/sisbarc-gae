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
<spring:message code="label.usuario.nome" var="label_usuario_nome"
	htmlEscape="false" />
<spring:message code="label.usuario.autorizacoes"
	var="label_usuario_Autorizacoes" htmlEscape="false" />
<spring:message code="label.usuario.ativo" var="label_usuario_ativo"
	htmlEscape="false" />

<form:form action="${pageContext.request.contextPath}/${param.action}"
	method="${param.method}" commandName="usuario" class="form-horizontal"
	id="frmUsuario">
	<form:hidden path="id" />
	<form:hidden path="senha" />
	<fieldset>
		<legend>
			${label_usuario} <small>${param.sublabel}</small>
		</legend>

		<div class="control-group">
			<label class="control-label">${label_usuario_nome}</label>
			<div class="controls">
				<form:input path="nome" class="input-large" />
				<form:errors path="nome" cssClass="alert alert-error input-alert" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">${label_usuario_Autorizacoes}</label>
			<div class="controls">
				<form:checkboxes items="${autorizacoes}" path="autorizacoes" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">${label_usuario_ativo}</label>
			<div class="controls">
				<form:radiobutton path="ativo" value="true" />
				${label_sim}
				<form:radiobutton path="ativo" value="false" />
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
			$("#frmUsuario").submit();
		});
	});
</script>