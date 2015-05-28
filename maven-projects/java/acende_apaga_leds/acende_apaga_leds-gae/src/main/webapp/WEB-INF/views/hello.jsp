<%-- Pagina principal da aplicacao, a listagem de mercadorias. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div>
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>Title : ${title}</h3>
		<h3>Message : ${message}</h3>
	</div>

	<div class="row-fluid" style="padding-top: 10px; padding-bottom: 10px">
		<div class="span1"></div>

		<div class="span10">
			<sec:authorize access="hasRole('ROLE_USER')">
				<!-- For login user -->
				<c:url value="/j_spring_security_logout" var="logoutUrl" />

				<form action="${logoutUrl}" method="post" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>

				<script>
					function formSubmit() {
						document.getElementById("logoutForm").submit();
					}
				</script>

				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<h2>
						User : ${pageContext.request.userPrincipal.name} | <a
							href="javascript:formSubmit()"> Logout</a>
					</h2>
				</c:if>
			</sec:authorize>
		</div>
	</div>
</div>