<%-- Fragmento com trecho utilizado no menu de navegacao. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="label.leds" var="label_leds" htmlEscape="false" />
<spring:message code="menu.lista" var="menu_lista" htmlEscape="false" />
<spring:message code="menu.incluir" var="menu_incluir"
	htmlEscape="false" />
<spring:message code="menu.sair" var="menu_sair" htmlEscape="false" />

<div>
	<ul class="nav nav-list" style="padding-top: 15px;">
		<li><em>${label_leds}</em></li>

		<li class="${active == 'listar_leds' ? 'active' : ''}"><a
			href="${pageContext.request.contextPath}/led">${menu_lista}</a></li>
		<li class="${active == 'incluir_led' ? 'active' : ''}"><a
			href="${pageContext.request.contextPath}/led?form">${menu_incluir}</a></li>
		<li class="divider" />
		<li><a href="${pageContext.request.contextPath}/logout">${menu_sair}</a></li>
	</ul>
</div>
