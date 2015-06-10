<%-- Pagina de edicao de usuario. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<spring:message code="label.editar" var="label_editar"
		htmlEscape="false" />
	<spring:message code="msg.erro.usuario.inexistente"
		var="usuario_inexistente" htmlEscape="false" />

	<c:choose>
		<c:when test="${usuario != null}">
			<c:import url="/WEB-INF/views/usuario/fullform.jsp">
				<c:param name="action" value="editar_login" />
				<c:param name="method" value="PUT" />
				<c:param name="sublabel" value="${label_editar}" />
				<c:param name="enableRemove" value="false" />
				<c:param name="isAdmin" value="false" />
			</c:import>			
		</c:when>
		<c:otherwise>
			<h3>${usuario_inexistente}</h3>
		</c:otherwise>
	</c:choose>
</div>