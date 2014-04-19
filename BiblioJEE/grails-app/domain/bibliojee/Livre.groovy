package bibliojee

import bibliojee.TypeDocument;

class Livre extends com.metasieve.shoppingcart.Shoppable{
	
	String titre
	int nombreExemplaires, nombreExemplairesDisponibles
	TypeDocument type
	
	//static hasMany = [reservations:Reservation]
	static hasMany = [auteurs:Auteur, reservations:Reservation]
	static belongsTo = [bibliojee.Auteur, bibliojee.Reservation]
	
	static searchable = {
        //except = ["nombreExemplaires", "nombreExemplairesDisponibles"]
		type component: true
        auteurs component: true
    }

    static constraints = {
		type(nullable:true)
    }
	
	public String toString(){
		titre
	}
	
}
