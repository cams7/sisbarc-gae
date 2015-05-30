<%-- Pagina principal da aplicacao, a listagem de usuarios. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<spring:message code="label.usuarios" var="label_usuarios"
		htmlEscape="false" />
	<spring:message code="label.listagem" var="label_listagem"
		htmlEscape="false" />

	<spring:message code="label.sim" var="label_sim" htmlEscape="false" />
	<spring:message code="label.nao" var="label_nao" htmlEscape="false" />

	<spring:message code="label.usuario.nome" var="label_usuario_nome"
		htmlEscape="false" />
	<spring:message code="label.usuario.ativo" var="label_usuario_ativo"
		htmlEscape="false" />
	<spring:message code="label.editar" var="label_editar"
		htmlEscape="false" />
	<spring:message code="button.atualizar" var="button_atualizar"
		htmlEscape="false" />

	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>${label_usuarios}
			<small> ${label_listagem}</small>
		</h3>
	</div>

	<table class="table table-hover">
		<thead>
			<tr>
				<th>#</th>
				<th>${label_usuario_nome}</th>
				<th>${label_usuario_ativo}</th>
			</tr>
		</thead>
		<c:forEach items="${usuarios}" var="u">
			<tr>
				<td>${u.id}</td>
				<td><spring:url
						value="${pageContext.request.contextPath}/usuario/${u.id}"
						var="edit_url" htmlEscape="true">
						<spring:param name="form" />
					</spring:url> <a href="${edit_url}" title="${label_editar} ${u.nome}">${u.nome}</a>
				</td>
				<td><c:choose>
						<c:when test="${u.ativo}">${label_sim}</c:when>
						<c:otherwise>${label_nao}</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>

	<form:form action="${pageContext.request.contextPath}/usuario/synch"
		method="GET">
		<div class="control-group">
			<div class="controls">
				<input type="submit" class="btn btn-success"
					value="${button_atualizar}" />
			</div>
		</div>
	</form:form>
</div>