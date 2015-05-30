<%-- Fragmento com trecho utilizado no menu de navegacao. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div>
	<spring:message code="label.usuarios" var="label_usuarios"
		htmlEscape="false" />
	<spring:message code="menu.lista" var="menu_lista" htmlEscape="false" />
	<spring:message code="menu.incluir" var="menu_incluir"
		htmlEscape="false" />
	<spring:message code="menu.sobre" var="menu_sobre" htmlEscape="false" />

	<ul class="nav nav-list" style="padding-top: 15px;">
		<li><em>${label_usuarios}</em></li>

		<li class="${empty active || active == 'lista' ? 'active' : ''}">
			<a href="${pageContext.request.contextPath}/usuario">${menu_lista}</a>
		</li>
		<li class="${active == 'incluirUsuario' ? 'active' : ''}"><a
			href="${pageContext.request.contextPath}/usuario?form">${menu_incluir}</a></li>
		<li class="divider" />
		<li class="${active == 'sobre' ? 'active' : ''}"><a
			href="${pageContext.request.contextPath}/sobre">${menu_sobre}</a></li>
	</ul>
</div>
