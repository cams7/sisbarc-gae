<%-- Pagina erro 403 - Acesso negado. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>HTTP Status 403 - Access is denied</h3>
	</div>

	<div class="row-fluid" style="padding-top: 10px; padding-bottom: 10px">
		<div class="span1"></div>

		<div class="span10">
			<c:choose>
				<c:when test="${empty username}">
					<h2>You do not have permission to access this page!</h2>
				</c:when>
				<c:otherwise>
					<h2>
						Username : ${username} <br />You do not have permission to access
						this page!
					</h2>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>