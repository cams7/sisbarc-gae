<%-- Pagina principal da aplicacao, a listagem de mercadorias. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:url value="/logout" var="logoutUrl" />

<div>
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>Title : ${title}</h3>
		<h3>Message : ${message}</h3>
	</div>

	<div class="row-fluid" style="padding-top: 10px; padding-bottom: 10px">
		<div class="span1"></div>

		<div class="span10">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<h2>
					User : ${pageContext.request.userPrincipal.name} | <a
						href="${logoutUrl}"> Logout</a>

				</h2>
			</c:if>
		</div>
	</div>
</div>