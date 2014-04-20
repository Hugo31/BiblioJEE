package com.metasieve.shoppingcart



import org.junit.*
import grails.test.mixin.*

@TestFor(ShoppingItemController)
@Mock(ShoppingItem)
class ShoppingItemControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/shoppingItem/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.shoppingItemInstanceList.size() == 0
        assert model.shoppingItemInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.shoppingItemInstance != null
    }

    void testSave() {
        controller.save()

        assert model.shoppingItemInstance != null
        assert view == '/shoppingItem/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/shoppingItem/show/1'
        assert controller.flash.message != null
        assert ShoppingItem.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/shoppingItem/list'

        populateValidParams(params)
        def shoppingItem = new ShoppingItem(params)

        assert shoppingItem.save() != null

        params.id = shoppingItem.id

        def model = controller.show()

        assert model.shoppingItemInstance == shoppingItem
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/shoppingItem/list'

        populateValidParams(params)
        def shoppingItem = new ShoppingItem(params)

        assert shoppingItem.save() != null

        params.id = shoppingItem.id

        def model = controller.edit()

        assert model.shoppingItemInstance == shoppingItem
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/shoppingItem/list'

        response.reset()

        populateValidParams(params)
        def shoppingItem = new ShoppingItem(params)

        assert shoppingItem.save() != null

        // test invalid parameters in update
        params.id = shoppingItem.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/shoppingItem/edit"
        assert model.shoppingItemInstance != null

        shoppingItem.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/shoppingItem/show/$shoppingItem.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        shoppingItem.clearErrors()

        populateValidParams(params)
        params.id = shoppingItem.id
        params.version = -1
        controller.update()

        assert view == "/shoppingItem/edit"
        assert model.shoppingItemInstance != null
        assert model.shoppingItemInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/shoppingItem/list'

        response.reset()

        populateValidParams(params)
        def shoppingItem = new ShoppingItem(params)

        assert shoppingItem.save() != null
        assert ShoppingItem.count() == 1

        params.id = shoppingItem.id

        controller.delete()

        assert ShoppingItem.count() == 0
        assert ShoppingItem.get(shoppingItem.id) == null
        assert response.redirectedUrl == '/shoppingItem/list'
    }
}
