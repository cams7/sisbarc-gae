<%-- Pagina de inclusao de usuario. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<spring:message code="label.incluir" var="label_incluir"
		htmlEscape="false" />

	<c:import url="/WEB-INF/views/usuario/fullform.jsp">
		<c:param name="action" value="incluir_usuario" />
		<c:param name="method" value="POST" />
		<c:param name="sublabel" value="${label_incluir}" />
		<c:param name="enableRemove" value="false" />
		<c:param name="isAdmin" value="true" />
	</c:import>
</div>