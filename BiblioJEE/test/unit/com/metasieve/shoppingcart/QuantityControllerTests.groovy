package com.metasieve.shoppingcart



import org.junit.*
import grails.test.mixin.*

@TestFor(QuantityController)
@Mock(Quantity)
class QuantityControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/quantity/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.quantityInstanceList.size() == 0
        assert model.quantityInstanceTotal == 0
		
		def model2 = controller.list()
		
		assert model2.quantityInstanceList.size() == 10
		assert model2.quantityInstanceTotal == 10
    }

    void testCreate() {
        def model = controller.create()

        assert model.quantityInstance != null
    }

    void testSave() {
        controller.save()

        assert model.quantityInstance != null
        assert view == '/quantity/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/quantity/show/1'
        assert controller.flash.message != null
        assert Quantity.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/quantity/list'

        populateValidParams(params)
        def quantity = new Quantity(params)

        assert quantity.save() != null

        params.id = quantity.id

        def model = controller.show()

        assert model.quantityInstance == quantity
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/quantity/list'

        populateValidParams(params)
        def quantity = new Quantity(params)

        assert quantity.save() != null

        params.id = quantity.id

        def model = controller.edit()

        assert model.quantityInstance == quantity
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/quantity/list'

        response.reset()

        populateValidParams(params)
        def quantity = new Quantity(params)

        assert quantity.save() != null

        // test invalid parameters in update
        params.id = quantity.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/quantity/edit"
        assert model.quantityInstance != null

        quantity.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/quantity/show/$quantity.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        quantity.clearErrors()

        populateValidParams(params)
        params.id = quantity.id
        params.version = -1
        controller.update()

        assert view == "/quantity/edit"
        assert model.quantityInstance != null
        assert model.quantityInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/quantity/list'

        response.reset()

        populateValidParams(params)
        def quantity = new Quantity(params)

        assert quantity.save() != null
        assert Quantity.count() == 1

        params.id = quantity.id

        controller.delete()

        assert Quantity.count() == 0
        assert Quantity.get(quantity.id) == null
        assert response.redirectedUrl == '/quantity/list'
    }
}
