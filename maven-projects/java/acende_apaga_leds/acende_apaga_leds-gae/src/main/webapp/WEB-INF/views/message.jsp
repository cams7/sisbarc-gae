<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if
	test="${not empty message_severity and not empty message_summary and not empty message_detail}">
	<div
		class="alert ${message_severity eq 'INFO' ? 'alert-success' : (message_severity eq 'WARN' ? 'alert-warning' : (message_severity eq 'ERROR' ? 'alert-danger' : ''))}"
		role="alert">
		<strong>${message_summary}</strong>&nbsp;${message_detail}
	</div>
</c:if>