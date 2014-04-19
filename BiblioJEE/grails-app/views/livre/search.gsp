<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
		<meta name="layout" content="main"/>
		<title>Recherche de livres</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
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
		</style>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="livre" action="panier">Panier</g:link></li>
			</ul>
  		</div>
  	
	  	<body onload="focusQueryInput();">
	
	  	<div class="body">
	  		<div id="header">
	    		<h1>Recherche de livres</h1>
	  		</div>
	  		<div style="clear: both; display: none;" class="hint">See <a href="http://lucene.apache.org/java/docs/queryparsersyntax.html">Lucene query syntax</a> for advanced queries</div>
	
		  	<div class="nav">
		  		<g:form url='[controller: "livre", action: "search"]' id="searchableForm" name="searchableForm" method="get">
	        		<g:textField name="q" value="${params.q}" /> <input type="submit" value="Search" />
			  	</g:form>
		  	</div>
	  
	  		<g:if test="${flash.message}">
		 		<div class="message" style="display: block">${flash.message}</div>
	  		</g:if>
    		<g:set var="haveQuery" value="${params.q?.trim()}" />
    		<g:set var="haveResults" value="${searchResult?.results}" />
    
			<g:if test="${haveResults}">
	    		<div class="list">
		          	<table>
						<thead>
							<tr>
			                    <td><strong>Titre</strong></td>
			                    <td><strong>Exemplaires Disponibles</strong></td>
			                    <td><strong>Type</strong></td>
			                    <td><strong>Auteur(s)</strong></td>
								<td><strong>&nbsp;</strong></td>
							</tr>
						</thead>
						<tbody>
							
							<g:each in="${searchResult.results}" status="i" var="book">
								<tr>
									<td>${book.titre}</td>
						        	<td>${book.nombreExemplairesDisponibles}</td>
									<td>${book.type}</td>
									<td>${book.auteurs}</td>
									<td class="actionButtons">
										<g:form url='[controller: "livre", action: "ajoutpanier"]' id="searchableForm" name="searchableForm" method="get">
					        				<g:textField name="q" hidden="true" value="${book.titre}" /> <input type="submit" value="Ajouter au panier" />
					  					</g:form>
									</td>           
								</tr>
							</g:each>
						</tbody>
			     	</table>
	       		</div>
	       		
	          	
   			</g:if>

  			<br/>
   			<div class="title">
      			<span>
        			<g:if test="${haveQuery && haveResults}">
          				Showing <strong>${searchResult.offset + 1}</strong> - <strong>${searchResult.results.size() + searchResult.offset}</strong> of <strong>${searchResult.total}</strong>
          				results for <strong>${params.q}</strong>
       				</g:if>
        			<g:else>
        				&nbsp;
        			</g:else>
      			</span>
    		</div>
    		<br/>

		    <g:if test="${haveQuery && !haveResults && !parseException}">
		      <p>Nothing matched your query - <strong>${params.q}</strong></p>
		    </g:if>
		    <g:if test="${searchResult?.suggestedQuery}">
		      <p>Did you mean <g:link controller="searchable" action="index" params="[q: searchResult.suggestedQuery]">${StringQueryUtils.highlightTermDiffs(params.q.trim(), searchResult.suggestedQuery)}</g:link>?</p>
		    </g:if>

		    <g:if test="${parseException}">
		      <p>Your query - <strong>${params.q}</strong> - is not valid.</p>
		      <p>Suggestions:</p>
		      <ul>
		        <li>Fix the query: see <a href="http://lucene.apache.org/java/docs/queryparsersyntax.html">Lucene query syntax</a> for examples</li>
		        <g:if test="${LuceneUtils.queryHasSpecialCharacters(params.q)}">
		          <li>Remove special characters like <strong>" - [ ]</strong>, before searching, eg, <em><strong>${LuceneUtils.cleanQuery(params.q)}</strong></em><br />
		              <em>Use the Searchable Plugin's <strong>LuceneUtils#cleanQuery</strong> helper method for this: <g:link controller="searchable" action="index" params="[q: LuceneUtils.cleanQuery(params.q)]">Search again with special characters removed</g:link></em>
		          </li>
		          <li>Escape special characters like <strong>" - [ ]</strong> with <strong>\</strong>, eg, <em><strong>${LuceneUtils.escapeQuery(params.q)}</strong></em><br />
		              <em>Use the Searchable Plugin's <strong>LuceneUtils#escapeQuery</strong> helper method for this: <g:link controller="searchable" action="index" params="[q: LuceneUtils.escapeQuery(params.q)]">Search again with special characters escaped</g:link></em><br />
		              <em>Or use the Searchable Plugin's <strong>escape</strong> option: <g:link controller="searchable" action="index" params="[q: params.q, escape: true]">Search again with the <strong>escape</strong> option enabled</g:link></em>
		          </li>
		        </g:if>
		      </ul>
		    </g:if>
    
		    <div id="status" role="complementary">
		  		<g:link controller="livre" action="panier"><h1>Panier</h1></g:link>
		  		<ul>
		  			<sc:each>
					    <li>${com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it['item'])}</li>
				   </sc:each>
				</ul>
		  	</div>
  
 		 </div>
	</body>
</html>