<%-- Fragmento com trecho utilizado no cabecalho das paginas. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<spring:message code="menu.inicio" var="menu_inicio" htmlEscape="false" />
<spring:message code="menu.home" var="menu_home" htmlEscape="false" />
<spring:message code="menu.incluir" var="menu_incluir"
	htmlEscape="false" />
<spring:message code="menu.led" var="menu_led" htmlEscape="false" />
<spring:message code="menu.potenciometro" var="menu_potenciometro"
	htmlEscape="false" />
<spring:message code="menu.login" var="menu_login" htmlEscape="false" />
<spring:message code="menu.usuario" var="menu_usuario"
	htmlEscape="false" />
<spring:message code="menu.sair" var="menu_sair" htmlEscape="false" />
<spring:message code="menu.lista" var="menu_lista" htmlEscape="false" />
<spring:message code="menu.usuarios" var="menu_usuarios"
	htmlEscape="false" />
<spring:message code="menu.leds" var="menu_leds" htmlEscape="false" />
<spring:message code="menu.potenciometros" var="menu_potenciometros"
	htmlEscape="false" />
<spring:message code="menu.ajuda" var="menu_ajuda" htmlEscape="false" />
<spring:message code="menu.sobre" var="menu_sobre" htmlEscape="false" />

<spring:url var="banner" value="/resources/img/logo_arduino.png" />
<div class="logo">
	<img src="${banner}" />
</div>

<nav class="navbar navbar-default">
	<ul class="nav navbar-nav">
		<li role="presentation" class="dropdown"><a id="menuInicio"
			class="dropdown-toggle" data-toggle="dropdown" href="#" role="button"
			aria-expanded="false">${menu_inicio}<span class="caret"></span></a>

			<ul class="dropdown-menu" role="menu" aria-labelledby="menuInicio">
				<li role="presentation"
					class="${active eq 'home' ? 'disabled' : ''}"><a
					role="menuitem" href="${pageContext.request.contextPath}/home">${menu_home}</a></li>

				<sec:authorize access="hasAnyRole('ROLE_USER,ROLE_ADMIN')">
					<li class="dropdown-submenu"><a id="submenuIncluir"
						class="dropdown-toggle" data-toggle="dropdown" href="#"
						role="button" aria-expanded="false">${menu_incluir}</a>

						<ul class="dropdown-menu" role="menu"
							aria-labelledby="submenuIncluir">
							<li role="presentation"
								class="${active eq 'incluir_led' ? 'disabled' : ''}"><a
								role="menuitem"
								href="${pageContext.request.contextPath}/led?form">${menu_led}</a></li>
							<li role="presentation"
								class="${active eq 'incluir_potenciometro' ? 'disabled' : ''}"><a
								role="menuitem"
								href="${pageContext.request.contextPath}/potenciometro?form">${menu_potenciometro}</a></li>
						</ul></li>
				</sec:authorize>

				<sec:authorize access="isAnonymous()">
					<li role="presentation" class="divider"></li>
					<c:choose>
						<c:when
							test="${pageContext.request.serverName eq 'acende-apaga-leds.appspot.com'}">
							<spring:url value="https://appengine.google.com/_ah/conflogin"
								var="appengine_url" htmlEscape="true">
								<spring:param name="continue"
									value="https://acende-apaga-leds.appspot.com" />
							</spring:url>
							<spring:url value="https://accounts.google.com/ServiceLogin"
								var="login_url" htmlEscape="true">
								<spring:param name="service" value="ah" />
								<spring:param name="passive" value="true" />
								<spring:param name="continue" value="${appengine_url}" />
							</spring:url>

							<li role="presentation"><a role="menuitem"
								href="${login_url}">${menu_login}</a></li>
						</c:when>
						<c:otherwise>
							<spring:url value="${pageContext.request.contextPath}/_ah/login"
								var="login_url" htmlEscape="true">
								<spring:param name="continue" value="home" />
							</spring:url>
							<li role="presentation"><a role="menuitem"
								href="${login_url}">${menu_login}</a></li>
						</c:otherwise>
					</c:choose>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li role="presentation" class="divider"></li>
					<li role="presentation"
						class="${active eq 'editar_login' ? 'disabled' : ''}"><a
						role="menuitem"
						href="${pageContext.request.contextPath}/editar_login">${menu_usuario}</a></li>
					<li role="presentation" class="divider"></li>
					<li role="presentation"><a role="menuitem"
						href="${pageContext.request.contextPath}/logout">${menu_sair}</a></li>
				</sec:authorize>
			</ul></li>
		<sec:authorize access="hasAnyRole('ROLE_USER,ROLE_ADMIN')">
			<li class="dropdown"><a id="menuLista" class="dropdown-toggle"
				data-toggle="dropdown" href="#" role="button" aria-expanded="false">${menu_lista}<span
					class="caret"></span></a>

				<ul class="dropdown-menu" role="menu" aria-labelledby="menuLista">
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li role="presentation"
							class="${active eq 'listar_usuarios' ? 'disabled' : ''}"><a
							role="menuitem" href="${pageContext.request.contextPath}/usuario">${menu_usuarios}</a></li>
					</sec:authorize>
					<li role="presentation"
						class="${active eq 'listar_leds' ? 'disabled' : ''}"><a
						role="menuitem" href="${pageContext.request.contextPath}/led">${menu_leds}</a></li>
					<li role="presentation"
						class="${active eq 'listar_potenciometros' ? 'disabled' : ''}"><a
						role="menuitem"
						href="${pageContext.request.contextPath}/potenciometro">${menu_potenciometros}</a></li>
				</ul></li>
		</sec:authorize>
		<li class="dropdown"><a id="menuAjuda" class="dropdown-toggle"
			data-toggle="dropdown" href="#" role="button" aria-expanded="false">${menu_ajuda}<span
				class="caret"></span></a>

			<ul class="dropdown-menu" role="menu" aria-labelledby="menuAjuda">
				<li role="presentation"
					class="${active eq 'sobre' ? 'disabled' : ''}"><a
					role="menuitem" href="${pageContext.request.contextPath}/sobre">${menu_sobre}</a></li>
			</ul></li>
	</ul>

	<sec:authorize access="isAuthenticated()">
		<spring:message code="menu.welcome" var="menu_welcome"
			htmlEscape="false"
			arguments="${pageContext.request.userPrincipal.name}" />
		<p class="navbar-text navbar-right">${menu_welcome}</p>
	</sec:authorize>
</nav>