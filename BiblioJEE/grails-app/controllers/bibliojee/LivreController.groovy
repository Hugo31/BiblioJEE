package bibliojee

import org.springframework.dao.DataIntegrityViolationException

class LivreController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [livreInstanceList: Livre.list(params), livreInstanceTotal: Livre.count()]
    }

    def create() {
        [livreInstance: new Livre(params)]
    }

    def save() {
        def livreInstance = new Livre(params)
        if (!livreInstance.save(flush: true)) {
            render(view: "create", model: [livreInstance: livreInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'livre.label', default: 'Livre'), livreInstance.id])
        redirect(action: "show", id: livreInstance.id)
    }

    def show(Long id) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        [livreInstance: livreInstance]
    }

    def edit(Long id) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        [livreInstance: livreInstance]
    }

    def update(Long id, Long version) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (livreInstance.version > version) {
                livreInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'livre.label', default: 'Livre')] as Object[],
                          "Another user has updated this Livre while you were editing")
                render(view: "edit", model: [livreInstance: livreInstance])
                return
            }
        }

        livreInstance.properties = params

        if (!livreInstance.save(flush: true)) {
            render(view: "edit", model: [livreInstance: livreInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'livre.label', default: 'Livre'), livreInstance.id])
        redirect(action: "show", id: livreInstance.id)
    }

    def delete(Long id) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        try {
            livreInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def scaffold = Livre
	
		def searchableService
	
		def shoppingCartService
	
		def query
		def srchResults
	
		def listLivre() {
			[livre: Livre.list(params), livresCount: Livre.count()]
		}
	
		def search = {
		   query = params.q
		   if(query){
			   if(params.offset == null){
				   params.offset = 0
				   params.max = 5
			   }
			   srchResults = searchableService.search(query, params)
	
			   render(view: "search",
					  model: [searchResult: srchResults])
		   }
		   else{
			   String uri = "/livre/search?q=*"
			   redirect(uri: uri)
		   }
		}
	
		private validerreservationpanier(){
			Random rand = new Random()
			int code = rand.nextInt(1000000)
			Date date = new Date()
			Reservation res = new Reservation(code: code, dateReservation: date)
			def livres = shoppingCartService.checkOut()
			livres.each {
				res.addToLivres(Livre.findByTitre(com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it['item']).titre))
				Livre.findByTitre(com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it['item']).titre).nombreExemplairesDisponibles -= 1
			}
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 1);
			date = c.getTime();
			flash.message = "RÃ©servation effectuÃ©e avec succÃ¨s. </br><span class='marge'>Code la rÃ©servation : " + code + ".</span></br><span class='marge'>Date limite de rÃ©cuperation des livres : " + date + "</span>"
			res.save(failOnError: true)
			redirect(uri:'/')
		}
	
		def reservationpanier = {
			if (!shoppingCartService.items.empty){//test si panier non vide
				boolean livresNonDisponible = false
				int nombreLivresNonDisponibles = 0
				def items = shoppingCartService.items
				items.each {
					if(com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it).nombreExemplairesDisponibles < 1){
						livresNonDisponible = true
						nombreLivresNonDisponibles ++
					}
				}
				if(livresNonDisponible){
					if(nombreLivresNonDisponibles == items.size()){
						flash.message = "Aucun livre n'est disponible, votre rÃ©servation ne peut pas aboutir"
					}
					else{
						def message = "Les livres suivants ne sont plus disponibles :"
						items.each {
							if(com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it).nombreExemplairesDisponibles < 1){
								message += "</br><span class='marge'>" + com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it).titre + "</span>"
							}
						}
						flash.message = message
						flash.confirmer = '<input id="res" class="res" type="submit" value="Confirmer reservation" name="res"></input>'
						flash.annuler = '<input id="res" class="res" type="submit" value="Annuler reservation" name="res"></input>'
					}
					redirect(uri:'/livre/panier')
				}
				else{
					validerreservationpanier()
				}
			}
			else{
				flash.message = "Impossible : panier vide"
				redirect(uri:'/livre/panier')
			}
	
		}
		def confirmerreservation = {
			Livre livre
			def items = shoppingCartService.items
			items.each {//suppression des livres dont aucun exemplaire n'est disponible
				if( com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it).nombreExemplairesDisponibles < 1){
					livre = Livre.findByTitre(com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it).titre)
					shoppingCartService.removeFromShoppingCart(livre)
				}
			}
			validerreservationpanier()
		}
	
		def annulerreservation = {
			redirect(uri:'/livre/panier')
		}
	
		def ajoutpanier = {
			def livre = Livre.findByTitre(params.q)
	
			if(livre.nombreExemplairesDisponibles < 1){
				flash.message = "Ajout impossible, aucun &nbsp;exemplaire n'est disponible."
			}
			else{
				boolean testExiste = false //utilisï¿½ pour tester si le livre est dï¿½jï¿½ dans le panier
	
				def items = shoppingCartService.items
				items.each {
					if( com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it).titre == params.q){//test dï¿½jï¿½ dans panier
						testExiste = true
					}
				}
				if(!testExiste){
					livre.addToShoppingCart()
					flash.message = "Livre ajoutÃ© avec succÃ¨s a votre panier"
				}
				else{
					flash.message = "Ajout impossible, ce livre est dÃ©jÃ  dans votre panier."
				}
			}
	
			String uri = "/livre/search?q=*"
			redirect(uri: uri)
		}
	
		def supprimerpanier = {
			def livre = Livre.findByTitre(params.q)
			livre.removeFromShoppingCart()
			render(view: "panier")
		}
	
		def panier = {
			render(view: "panier")
		}
}
