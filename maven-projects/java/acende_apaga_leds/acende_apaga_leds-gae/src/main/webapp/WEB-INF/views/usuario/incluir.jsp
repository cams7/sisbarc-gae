<%-- Pagina de inclusao de usuario. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<spring:message code="label.incluir" var="label_incluir"
		htmlEscape="false" />

	<c:import url="/WEB-INF/views/usuario/formulario.jsp">
		<c:param name="action" value="incluirUsuario" />
		<c:param name="method" value="POST" />
		<c:param name="sublabel" value="${label_incluir}" />
	</c:import>
</div>