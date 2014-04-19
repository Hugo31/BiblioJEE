<%@ page import="bibliojee.Livre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'livre.label', default: 'Livre')}" />
		<title>Mon panier</title>
		<style type="text/css" media="screen">
			.marge {
				margin-left: 5em;
			}
		</style>
	</head>
	<body>
		<a href="#show-livre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="livre" action="search">Search</g:link></li>
			</ul>
		</div>
		<div id="show-livre" class="content scaffold-show" role="main">
			
			<table>
				<tr>
		         <td>
		            <h1>Livres</h1>
		         </td>
		         <td>
		            <h1>Exemplaires disponibles</h1>
		         </td>
		         <td>
		            &nbsp;
		         </td>
			    </tr>
			   <sc:each>
			      <tr>
			         <td>
			            ${com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it['item'])}
			         </td>
			         <td>
			            ${com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it['item']).nombreExemplairesDisponibles}
			         </td>
			         <td>
			            <g:form url='[controller: "livre", action: "supprimerpanier"]' id="searchableForm" name="searchableForm" method="get">
	        				<g:textField name="q" hidden="true" value="${com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it['item']).titre}" /> <input type="submit" value="Supprimer du panier" />
	  					</g:form>
			         </td>
			      </tr>
			   </sc:each>
			</table>
			
		  <g:if test="${flash.message}">
	  		 <div class="message" style="display: block">${flash.message}</div>
  		  </g:if>
  		  
		  	<g:if test="${flash.confirmer}">
	  			<g:form action="confirmerreservation" >
					<fieldset class="buttons">
	 		 				${flash.confirmer}
					</fieldset>
				</g:form>
			</g:if>
			
			<g:if test="${flash.annuler}">
				<g:form action="annulerreservation" >
					<fieldset class="buttons">
  		 				${flash.annuler}
					</fieldset>
				</g:form>
			</g:if>
			<g:else>
				<g:form action="reservationpanier" >
					<fieldset class="buttons">
						<g:submitButton name="res" class="res" value="Réserver panier" />
					</fieldset>
				</g:form>
			</g:else>
		</div>
	</body>
</html>