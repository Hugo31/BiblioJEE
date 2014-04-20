package bibliojee



import grails.test.mixin.*
import junit.framework.TestCase
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TypeDocument)
class TypeDocumentTests extends TestCase {

	@Test
    void testToString() {
		TypeDocument t = new TypeDocument(intitule: "Nouveauté")
		assertEquals("Nouveauté", t.toString())
	}
	
	@Test
	void testTypeDocument() {
		TypeDocument t = null
		assertEquals(null, t)
		t = new TypeDocument(intitule: "Nouveauté")
		assertEquals("Nouveauté", t.toString())
	}
}
