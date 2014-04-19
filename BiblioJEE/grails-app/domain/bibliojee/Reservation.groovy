package bibliojee

import java.util.Date;

class Reservation {
	
	int code
	Date dateReservation
	
	static hasMany = [livres:Livre]

    static constraints = {
    }
}
