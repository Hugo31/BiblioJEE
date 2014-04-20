package com.metasieve.shoppingcart



import org.junit.*
import grails.test.mixin.*

@TestFor(ShoppingCartTestProductController)
@Mock(ShoppingCartTestProduct)
class ShoppingCartTestProductControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/shoppingCartTestProduct/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.shoppingCartTestProductInstanceList.size() == 0
        assert model.shoppingCartTestProductInstanceTotal == 0
		
		def model2 = controller.list(10)
		
		assert model2.shoppingCartTestProductInstanceList.size() == 10
		assert model2.shoppingCartTestProductInstanceTotal == 10
    }

    void testCreate() {
        def model = controller.create()

        assert model.shoppingCartTestProductInstance != null
    }

    void testSave() {
        controller.save()

        assert model.shoppingCartTestProductInstance != null
        assert view == '/shoppingCartTestProduct/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/shoppingCartTestProduct/show/1'
        assert controller.flash.message != null
        assert ShoppingCartTestProduct.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/shoppingCartTestProduct/list'

        populateValidParams(params)
        def shoppingCartTestProduct = new ShoppingCartTestProduct(params)

        assert shoppingCartTestProduct.save() != null

        params.id = shoppingCartTestProduct.id

        def model = controller.show()

        assert model.shoppingCartTestProductInstance == shoppingCartTestProduct
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/shoppingCartTestProduct/list'

        populateValidParams(params)
        def shoppingCartTestProduct = new ShoppingCartTestProduct(params)

        assert shoppingCartTestProduct.save() != null

        params.id = shoppingCartTestProduct.id

        def model = controller.edit()

        assert model.shoppingCartTestProductInstance == shoppingCartTestProduct
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/shoppingCartTestProduct/list'

        response.reset()

        populateValidParams(params)
        def shoppingCartTestProduct = new ShoppingCartTestProduct(params)

        assert shoppingCartTestProduct.save() != null

        // test invalid parameters in update
        params.id = shoppingCartTestProduct.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/shoppingCartTestProduct/edit"
        assert model.shoppingCartTestProductInstance != null

        shoppingCartTestProduct.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/shoppingCartTestProduct/show/$shoppingCartTestProduct.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        shoppingCartTestProduct.clearErrors()

        populateValidParams(params)
        params.id = shoppingCartTestProduct.id
        params.version = -1
        controller.update()

        assert view == "/shoppingCartTestProduct/edit"
        assert model.shoppingCartTestProductInstance != null
        assert model.shoppingCartTestProductInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/shoppingCartTestProduct/list'

        response.reset()

        populateValidParams(params)
        def shoppingCartTestProduct = new ShoppingCartTestProduct(params)

        assert shoppingCartTestProduct.save() != null
        assert ShoppingCartTestProduct.count() == 1

        params.id = shoppingCartTestProduct.id

        controller.delete()

        assert ShoppingCartTestProduct.count() == 0
        assert ShoppingCartTestProduct.get(shoppingCartTestProduct.id) == null
        assert response.redirectedUrl == '/shoppingCartTestProduct/list'
    }
}
