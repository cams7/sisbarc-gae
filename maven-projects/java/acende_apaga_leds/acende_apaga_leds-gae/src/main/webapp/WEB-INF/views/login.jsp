<%-- Pagina principal da aplicacao, a listagem de mercadorias. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>Spring Security Login Form (Database Authentication)</h3>
	</div>

	<div class="row-fluid" style="padding-top: 10px; padding-bottom: 10px">
		<div class="span1"></div>

		<div class="span10">
			<h3>Login with Username and Password</h3>

			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>

			<form name='loginForm'
				action="<c:url value='/j_spring_security_check' />" method='POST'>
				<table>
					<tr>
						<td>User:</td>
						<td><input type='text' name='username'></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type='password' name='password' /></td>
					</tr>
					<tr>
						<td colspan='2'><input name="submit" type="submit"
							value="submit" /></td>
					</tr>
				</table>

				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
	</div>
</div>