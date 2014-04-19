import bibliojee.Auteur

import bibliojee.Auteur
import bibliojee.Livre
import bibliojee.TypeDocument

class BootStrap {
	def searchableService
    def init = { servletContext ->
		searchableService.stopMirroring()
		
		System.out = new  PrintStream(System.out, true, "UTF-8")
		TypeDocument tAdulte = new TypeDocument(intitule: "Livre adulte")
		new TypeDocument(intitule: "Nouveauté").save(failOnError: true)
			.save(failOnError: true)
		new TypeDocument(intitule: "Livre ado").save(failOnError: true)
			.save(failOnError: true)
		new TypeDocument(intitule: "Bande dessinée adulte").save(failOnError: true)
			.save(failOnError: true)
		tAdulte.save(failOnError: true)
		Auteur a = new Auteur(nom: "Collins", prenom: "Suzanne")
		Livre l = new Livre(titre: "Hunger games [Texte imprime]", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: tAdulte)
		Livre l2 = new Livre(titre: "L\'embrasement", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Livre ado"))
		Livre l3 = new Livre(titre: "La revolte", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Livre ado"))

		a.addToLivres(l)
		a.addToLivres(l2)
		a.addToLivres(l3)
		l.addToAuteurs(a)
		l2.addToAuteurs(a)
		l3.addToAuteurs(a)
		
		a.save(failOnError: true)
		l.save(failOnError: true)
		l2.save(failOnError: true)
		l3.save(failOnError: true)
		
		new Auteur(nom: "Stockett", prenom: "Kathryn")
			.addToLivres(new Livre(titre: "La couleur des sentiments", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Nouveauté")))
			.save(failOnError: true)
		new Auteur(nom: "Carrère", prenom: "Emmanuel")
			.addToLivres(new Livre(titre: "Limonov", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Nouveauté")))
			.save(failOnError: true)
		
		Auteur a2 = new Auteur(nom: "Murakami", prenom: "Haruki")
		Auteur a3 = new Auteur(nom: "Mizkanitimi", prenom: "Yatato")
		l = new Livre(titre: "1984. 1. Avril-juin", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Nouveauté"))
		l2 = new Livre(titre: "1984. 3. Octobre-décembre", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Nouveauté"))
		l3 = new Livre(titre: "1984. 2. Juillet-septembre", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Nouveauté"))
		
		a2.addToLivres(l)
		a2.addToLivres(l2)
		a2.addToLivres(l3)
		l.addToAuteurs(a2)
		l.addToAuteurs(a3)
		l2.addToAuteurs(a2)
		l3.addToAuteurs(a2)
		
		l.save(failOnError: true)
		l2.save(failOnError: true)
		l3.save(failOnError: true)
		a2.save(failOnError: true)
		a3.save(failOnError: true)
		
		new Auteur(nom: "Delacourt", prenom: "Grégoire")
			.addToLivres(new Livre(titre: "La liste de mes envies", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Livre adulte")))
			.save(failOnError: true)
		new Auteur(nom: "Larsson", prenom: "Stieg")
			.addToLivres(new Livre(titre: "La reine dans le palais des courants d'air [Texte imprimé]", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Livre adulte")))
			.save(failOnError: true)
		new Auteur(nom: "Ferrari", prenom: "Jérome")
			.addToLivres(new Livre(titre: "Le sermon sur la chute de Rome", nombreExemplaires: 3, nombreExemplairesDisponibles: 2))
			.save(failOnError: true)
		new Auteur(nom: "Pennac", prenom: "Daniel")
			.addToLivres(new Livre(titre: "Journal d'un corps", nombreExemplaires: 3, nombreExemplairesDisponibles: 2))
			.save(failOnError: true)
		new Auteur(nom: "Dicker", prenom: "Joël")
			.addToLivres(new Livre(titre: "La vérité sur l'affaire Harry Quebert : roman", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Livre adulte")))
			.save(failOnError: true)
		new Auteur(nom: "Jonasson", prenom: "Jonas")
			.addToLivres(new Livre(titre: "Le vieux qui ne voulait pas fêter son anniversaire : roman", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Nouveauté")))
			.save(failOnError: true)
		new Auteur(nom: "Tesson", prenom: "Sylvain")
			.addToLivres(new Livre(titre: "Dans les forêts de Sibérie : févrer-juillet 203", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Nouveauté")))
			.save(failOnError: true)
		new Auteur(nom: "Jenni", prenom: "Alexis")
			.addToLivres(new Livre(titre: "L'art français de la guerre [Texte imprimé]", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Livre adulte")))
			.save(failOnError: true)
		new Auteur(nom: "Deutsch", prenom: "Lorant")
			.addToLivres(new Livre(titre: "Métronome[Texte imprimé] : l'histoire de France au rythme du métro parisien", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Nouveauté")))
			.save(failOnError: true)
		new Auteur(nom: "Astruc", prenom: "Jacques")
			.addToLivres(new Livre(titre: "Demeures de la nuit : nouvelles", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Livre adulte")))
			.save(failOnError: true)
		new Auteur(nom: "Blain", prenom: "Christophe")
			.addToLivres(new Livre(titre: "Quai d'Orsay : chroniques diplomatiques. 1", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Bande dessinée adulte")))
			.save(failOnError: true)
		new Auteur(nom: "Musso", prenom: "Guillaume")
			.addToLivres(new Livre(titre: "7 ans après...", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: TypeDocument.findByIntitule("Livre adulte")))
			.save(failOnError: true)
			
		searchableService.startMirroring()
    }
    def destroy = {
    }
}
