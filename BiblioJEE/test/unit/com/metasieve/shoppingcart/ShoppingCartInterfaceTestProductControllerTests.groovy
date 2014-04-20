package com.metasieve.shoppingcart



import org.junit.*
import grails.test.mixin.*

@TestFor(ShoppingCartInterfaceTestProductController)
@Mock(ShoppingCartInterfaceTestProduct)
class ShoppingCartInterfaceTestProductControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/shoppingCartInterfaceTestProduct/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.shoppingCartInterfaceTestProductInstanceList.size() == 0
        assert model.shoppingCartInterfaceTestProductInstanceTotal == 0
		
		def model2 = controller.list()
		
		assert model2.shoppingCartInterfaceTestProductInstanceList.size() == 10
		assert model2.shoppingCartInterfaceTestProductInstanceTotal == 10
    }

    void testCreate() {
        def model = controller.create()

        assert model.shoppingCartInterfaceTestProductInstance != null
    }

    void testSave() {
        controller.save()

        assert model.shoppingCartInterfaceTestProductInstance != null
        assert view == '/shoppingCartInterfaceTestProduct/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/shoppingCartInterfaceTestProduct/show/1'
        assert controller.flash.message != null
        assert ShoppingCartInterfaceTestProduct.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/shoppingCartInterfaceTestProduct/list'

        populateValidParams(params)
        def shoppingCartInterfaceTestProduct = new ShoppingCartInterfaceTestProduct(params)

        assert shoppingCartInterfaceTestProduct.save() != null

        params.id = shoppingCartInterfaceTestProduct.id

        def model = controller.show()

        assert model.shoppingCartInterfaceTestProductInstance == shoppingCartInterfaceTestProduct
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/shoppingCartInterfaceTestProduct/list'

        populateValidParams(params)
        def shoppingCartInterfaceTestProduct = new ShoppingCartInterfaceTestProduct(params)

        assert shoppingCartInterfaceTestProduct.save() != null

        params.id = shoppingCartInterfaceTestProduct.id

        def model = controller.edit()

        assert model.shoppingCartInterfaceTestProductInstance == shoppingCartInterfaceTestProduct
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/shoppingCartInterfaceTestProduct/list'

        response.reset()

        populateValidParams(params)
        def shoppingCartInterfaceTestProduct = new ShoppingCartInterfaceTestProduct(params)

        assert shoppingCartInterfaceTestProduct.save() != null

        // test invalid parameters in update
        params.id = shoppingCartInterfaceTestProduct.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/shoppingCartInterfaceTestProduct/edit"
        assert model.shoppingCartInterfaceTestProductInstance != null

        shoppingCartInterfaceTestProduct.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/shoppingCartInterfaceTestProduct/show/$shoppingCartInterfaceTestProduct.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        shoppingCartInterfaceTestProduct.clearErrors()

        populateValidParams(params)
        params.id = shoppingCartInterfaceTestProduct.id
        params.version = -1
        controller.update()

        assert view == "/shoppingCartInterfaceTestProduct/edit"
        assert model.shoppingCartInterfaceTestProductInstance != null
        assert model.shoppingCartInterfaceTestProductInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/shoppingCartInterfaceTestProduct/list'

        response.reset()

        populateValidParams(params)
        def shoppingCartInterfaceTestProduct = new ShoppingCartInterfaceTestProduct(params)

        assert shoppingCartInterfaceTestProduct.save() != null
        assert ShoppingCartInterfaceTestProduct.count() == 1

        params.id = shoppingCartInterfaceTestProduct.id

        controller.delete()

        assert ShoppingCartInterfaceTestProduct.count() == 0
        assert ShoppingCartInterfaceTestProduct.get(shoppingCartInterfaceTestProduct.id) == null
        assert response.redirectedUrl == '/shoppingCartInterfaceTestProduct/list'
    }
}
