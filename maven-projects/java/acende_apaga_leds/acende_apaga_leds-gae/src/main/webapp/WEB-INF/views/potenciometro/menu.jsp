<%-- Fragmento com trecho utilizado no menu de navegacao. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="label.potenciometros" var="label_potenciometros"
	htmlEscape="false" />
<spring:message code="menu.lista" var="menu_lista" htmlEscape="false" />
<spring:message code="menu.incluir" var="menu_incluir"
	htmlEscape="false" />
<spring:message code="menu.sair" var="menu_sair" htmlEscape="false" />

<div>
	<ul class="nav nav-list" style="padding-top: 15px;">
		<li><em>${label_potenciometros}</em></li>

		<li class="${active eq 'listar_potenciometros' ? 'active' : ''}"><a
			href="${pageContext.request.contextPath}/potenciometro">${menu_lista}</a></li>
		<li class="${active eq 'incluir_potenciometro' ? 'active' : ''}"><a
			href="${pageContext.request.contextPath}/potenciometro?form">${menu_incluir}</a></li>
		<li class="divider" />
		<li><a href="${pageContext.request.contextPath}/logout">${menu_sair}</a></li>
	</ul>
</div>
