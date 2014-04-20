package bibliojee



import grails.test.mixin.*
import junit.framework.TestCase

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Reservation)
class ReservationTests extends TestCase {
	
	Livre l = new Livre(titre: "Hunger games [Texte imprime]", nombreExemplaires: 3, nombreExemplairesDisponibles: 2)
	
	@Test
	void testReservation() {
		Reservation r = null
		Date date = new Date(01,12,14)
		assertEquals(null, r)
		r = new Reservation(code: 123, dateReservation: date, livres: l)
		assertEquals(123, r.code)
		assertEquals(date, r.dateReservation)
	}
}
