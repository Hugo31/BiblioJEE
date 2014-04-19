
<%@ page import="bibliojee.Livre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'livre.label', default: 'Livre')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-livre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-livre" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list livre">
			
				<g:if test="${livreInstance?.shoppingItem}">
				<li class="fieldcontain">
					<span id="shoppingItem-label" class="property-label"><g:message code="livre.shoppingItem.label" default="Shopping Item" /></span>
					
						<span class="property-value" aria-labelledby="shoppingItem-label"><g:link controller="shoppingItem" action="show" id="${livreInstance?.shoppingItem?.id}">${livreInstance?.shoppingItem?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${livreInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="livre.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:link controller="typeDocument" action="show" id="${livreInstance?.type?.id}">${livreInstance?.type?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${livreInstance?.auteurs}">
				<li class="fieldcontain">
					<span id="auteurs-label" class="property-label"><g:message code="livre.auteurs.label" default="Auteurs" /></span>
					
						<g:each in="${livreInstance.auteurs}" var="a">
						<span class="property-value" aria-labelledby="auteurs-label"><g:link controller="auteur" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${livreInstance?.nombreExemplaires}">
				<li class="fieldcontain">
					<span id="nombreExemplaires-label" class="property-label"><g:message code="livre.nombreExemplaires.label" default="Nombre Exemplaires" /></span>
					
						<span class="property-value" aria-labelledby="nombreExemplaires-label"><g:fieldValue bean="${livreInstance}" field="nombreExemplaires"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${livreInstance?.nombreExemplairesDisponibles}">
				<li class="fieldcontain">
					<span id="nombreExemplairesDisponibles-label" class="property-label"><g:message code="livre.nombreExemplairesDisponibles.label" default="Nombre Exemplaires Disponibles" /></span>
					
						<span class="property-value" aria-labelledby="nombreExemplairesDisponibles-label"><g:fieldValue bean="${livreInstance}" field="nombreExemplairesDisponibles"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${livreInstance?.reservations}">
				<li class="fieldcontain">
					<span id="reservations-label" class="property-label"><g:message code="livre.reservations.label" default="Reservations" /></span>
					
						<g:each in="${livreInstance.reservations}" var="r">
						<span class="property-value" aria-labelledby="reservations-label"><g:link controller="reservation" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${livreInstance?.titre}">
				<li class="fieldcontain">
					<span id="titre-label" class="property-label"><g:message code="livre.titre.label" default="Titre" /></span>
					
						<span class="property-value" aria-labelledby="titre-label"><g:fieldValue bean="${livreInstance}" field="titre"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${livreInstance?.id}" />
					<g:link class="edit" action="edit" id="${livreInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
