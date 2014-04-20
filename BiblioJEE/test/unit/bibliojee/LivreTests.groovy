package bibliojee



import junit.framework.TestCase;
import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Livre)
class LivreTests extends TestCase {

	TypeDocument t = new TypeDocument(intitule: "Nouveauté")
	
    @Test
    void testToString() {
		Livre l = new Livre(titre: "Hunger games [Texte imprimé]", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: t)
		assertEquals("Hunger games [Texte imprimé]", l.toString())
	}
	
	@Test
	void testLivre() {
		Livre l = null
		assertEquals(null, l)
		l =new Livre(titre: "Hunger games [Texte imprimé]", nombreExemplaires: 3, nombreExemplairesDisponibles: 2, type: t)
		assertEquals("Hunger games [Texte imprimé]", l.toString())
	}
}
