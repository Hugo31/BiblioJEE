<%@ page import="bibliojee.Livre" %>



<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'shoppingItem', 'error')} ">
	<label for="shoppingItem">
		<g:message code="livre.shoppingItem.label" default="Shopping Item" />
		
	</label>
	<g:select id="shoppingItem" name="shoppingItem.id" from="${com.metasieve.shoppingcart.ShoppingItem.list()}" optionKey="id" value="${livreInstance?.shoppingItem?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="livre.type.label" default="Type" />
		
	</label>
	<g:select id="type" name="type.id" from="${bibliojee.TypeDocument.list()}" optionKey="id" value="${livreInstance?.type?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'auteurs', 'error')} ">
	<label for="auteurs">
		<g:message code="livre.auteurs.label" default="Auteurs" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'nombreExemplaires', 'error')} required">
	<label for="nombreExemplaires">
		<g:message code="livre.nombreExemplaires.label" default="Nombre Exemplaires" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nombreExemplaires" type="number" value="${livreInstance.nombreExemplaires}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'nombreExemplairesDisponibles', 'error')} required">
	<label for="nombreExemplairesDisponibles">
		<g:message code="livre.nombreExemplairesDisponibles.label" default="Nombre Exemplaires Disponibles" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nombreExemplairesDisponibles" type="number" value="${livreInstance.nombreExemplairesDisponibles}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'reservations', 'error')} ">
	<label for="reservations">
		<g:message code="livre.reservations.label" default="Reservations" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'titre', 'error')} ">
	<label for="titre">
		<g:message code="livre.titre.label" default="Titre" />
		
	</label>
	<g:textField name="titre" value="${livreInstance?.titre}"/>
</div>

