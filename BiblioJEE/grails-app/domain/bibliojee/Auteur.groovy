package bibliojee

class Auteur {
	
	String nom
	String prenom
	
	static hasMany = [livres:Livre]
	
	static searchable = {
		root false
		//except = ["prenom"]
	}
	
    static constraints = {
    }
	
	public String toString(){
		"${nom.toUpperCase()}" + " " + prenom
	}
}
