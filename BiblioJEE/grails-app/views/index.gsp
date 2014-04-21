<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Grails - BiblioJ</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
			
			.marge {
				margin-left: 5em;
			}
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="status" role="complementary">
			<h1>Marche à suivre</h1>
			<ul>
				<li>1. Choississez un livre parmis notre catalogue</li>
				<li>2. Ajouter le livre dans votre panier (un seul exemplaire)</li>
				<li>2.2 Ajouter d'autres livres si vous le souhaitez</li>
				<li>3. Validez votre panier</li>
				<li>3.2 Si un exemplaire n'est plus disponible, vous ne pouvez pas le réserver
				mais vous pouvez quand même effectuer votre commande (sans le livre manquant)</li>
				<li>4. Un code de réservation vous sera attribué</li>
				<li>5. Vous avez 24h pour récupérer votre commande grace à votre code de réservation</li>
				<li>5.2 Dépasser ce délais, votre commande sera annulé</li>
			</ul>
			<h1>Bonne lecture !</h1>
		</div>
		<div id="page-body" role="main">
		
			<g:if test="${flash.message}">
			  <div class="message" style="display: block">${flash.message}</div>
			</g:if>
			
			<h1>Accueil</h1>

			<p>Bienvenue sur le site de réservation en ligne BiblioJ.<br />
			Vous avez la possibilité d'effectuer des réservation en ligne sur les livres
			que vous souhaitez emprunter sans vous soucier si ce dernier est encore disponible<br/>
			BiblioJ vous permet de faire vos réservations de manière simple et rapide<br />
			Vous pouvez choisir les livres de notre catalogue. Essayez dès maintenant !</p>
			
			<div id=usage-list">
				<h1>Faire une <g:link controller="livre" action="search">recherche de livres</g:link></h1>
				<h1>Voir <g:link controller="livre" action="panier">mon panier</g:link></h1>
			</div>
		</div>
		Ce site a été développé par GUIGNARD Hugo et LODOVICI Bénédicte
	</body>
</html>
