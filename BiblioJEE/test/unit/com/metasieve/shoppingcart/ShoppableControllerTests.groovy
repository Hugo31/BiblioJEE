package com.metasieve.shoppingcart



import org.junit.*
import grails.test.mixin.*

@TestFor(ShoppableController)
@Mock(Shoppable)
class ShoppableControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/shoppable/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.shoppableInstanceList.size() == 0
        assert model.shoppableInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.shoppableInstance != null
    }

    void testSave() {
        controller.save()

        assert model.shoppableInstance != null
        assert view == '/shoppable/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/shoppable/show/1'
        assert controller.flash.message != null
        assert Shoppable.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/shoppable/list'

        populateValidParams(params)
        def shoppable = new Shoppable(params)

        assert shoppable.save() != null

        params.id = shoppable.id

        def model = controller.show()

        assert model.shoppableInstance == shoppable
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/shoppable/list'

        populateValidParams(params)
        def shoppable = new Shoppable(params)

        assert shoppable.save() != null

        params.id = shoppable.id

        def model = controller.edit()

        assert model.shoppableInstance == shoppable
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/shoppable/list'

        response.reset()

        populateValidParams(params)
        def shoppable = new Shoppable(params)

        assert shoppable.save() != null

        // test invalid parameters in update
        params.id = shoppable.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/shoppable/edit"
        assert model.shoppableInstance != null

        shoppable.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/shoppable/show/$shoppable.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        shoppable.clearErrors()

        populateValidParams(params)
        params.id = shoppable.id
        params.version = -1
        controller.update()

        assert view == "/shoppable/edit"
        assert model.shoppableInstance != null
        assert model.shoppableInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/shoppable/list'

        response.reset()

        populateValidParams(params)
        def shoppable = new Shoppable(params)

        assert shoppable.save() != null
        assert Shoppable.count() == 1

        params.id = shoppable.id

        controller.delete()

        assert Shoppable.count() == 0
        assert Shoppable.get(shoppable.id) == null
        assert response.redirectedUrl == '/shoppable/list'
    }
}
