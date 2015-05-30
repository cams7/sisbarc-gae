<%-- Fragmento com trecho utilizado no rodape das paginas. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="footer">
	<spring:message code="label.detalhes.projeto"
		var="label_detalhes_projeto" htmlEscape="false" />
	<spring:message code="label.cams7" var="label_cams7" htmlEscape="false" />
	<spring:message code="label.saiba.mais" var="label_saiba_mais"
		htmlEscape="false" />

	<div style="border-top: 1px solid #E5E5E5; padding-top: 10px">
		<a href="http://www.yaw.com.br" title="${label_cams7}" target="open">${label_cams7}</a>
		| <small>${label_detalhes_projeto} (<a
			href="http://www.yaw.com.br/open/projetos/springmvc-gae/"
			title="${label_saiba_mais}" target="open">${label_saiba_mais}</a>)
		</small>
	</div>
</div>
