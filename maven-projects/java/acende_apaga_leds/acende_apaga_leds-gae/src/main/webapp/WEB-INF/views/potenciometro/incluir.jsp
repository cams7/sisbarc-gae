<%-- Pagina de inclusao do LED. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:message code="label.incluir" var="label_incluir"
	htmlEscape="false" />

<div>
	<c:import url="/WEB-INF/views/potenciometro/form.jsp">
		<c:param name="action" value="incluir_potenciometro" />
		<c:param name="method" value="POST" />
		<c:param name="sublabel" value="${label_incluir}" />
		<c:param name="createEntity" value="true" />
	</c:import>
</div>