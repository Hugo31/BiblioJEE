package bibliojee



import junit.framework.TestCase;
import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Auteur)
class AuteurTests extends TestCase {
	
	@Test
	void testToString() {
		Auteur a = new Auteur(nom: "Collins", prenom: "Suzanne")
		assertEquals("COLLINS Suzanne", a.toString())
	}
	
    @Test
	void testAuteur() {
		Auteur a = null
		assertEquals(null, a)
		a = new Auteur(nom: "Collins", prenom: "Suzanne")
		assertEquals("Collins", a.nom)
		assertEquals("Suzanne", a.prenom)
	}
}
