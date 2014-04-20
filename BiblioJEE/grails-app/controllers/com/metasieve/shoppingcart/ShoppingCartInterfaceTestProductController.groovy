package com.metasieve.shoppingcart

import org.springframework.dao.DataIntegrityViolationException

class ShoppingCartInterfaceTestProductController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [shoppingCartInterfaceTestProductInstanceList: ShoppingCartInterfaceTestProduct.list(params), shoppingCartInterfaceTestProductInstanceTotal: ShoppingCartInterfaceTestProduct.count()]
    }

    def create() {
        [shoppingCartInterfaceTestProductInstance: new ShoppingCartInterfaceTestProduct(params)]
    }

    def save() {
        def shoppingCartInterfaceTestProductInstance = new ShoppingCartInterfaceTestProduct(params)
        if (!shoppingCartInterfaceTestProductInstance.save(flush: true)) {
            render(view: "create", model: [shoppingCartInterfaceTestProductInstance: shoppingCartInterfaceTestProductInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'shoppingCartInterfaceTestProduct.label', default: 'ShoppingCartInterfaceTestProduct'), shoppingCartInterfaceTestProductInstance.id])
        redirect(action: "show", id: shoppingCartInterfaceTestProductInstance.id)
    }

    def show(Long id) {
        def shoppingCartInterfaceTestProductInstance = ShoppingCartInterfaceTestProduct.get(id)
        if (!shoppingCartInterfaceTestProductInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingCartInterfaceTestProduct.label', default: 'ShoppingCartInterfaceTestProduct'), id])
            redirect(action: "list")
            return
        }

        [shoppingCartInterfaceTestProductInstance: shoppingCartInterfaceTestProductInstance]
    }

    def edit(Long id) {
        def shoppingCartInterfaceTestProductInstance = ShoppingCartInterfaceTestProduct.get(id)
        if (!shoppingCartInterfaceTestProductInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingCartInterfaceTestProduct.label', default: 'ShoppingCartInterfaceTestProduct'), id])
            redirect(action: "list")
            return
        }

        [shoppingCartInterfaceTestProductInstance: shoppingCartInterfaceTestProductInstance]
    }

    def update(Long id, Long version) {
        def shoppingCartInterfaceTestProductInstance = ShoppingCartInterfaceTestProduct.get(id)
        if (!shoppingCartInterfaceTestProductInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingCartInterfaceTestProduct.label', default: 'ShoppingCartInterfaceTestProduct'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (shoppingCartInterfaceTestProductInstance.version > version) {
                shoppingCartInterfaceTestProductInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'shoppingCartInterfaceTestProduct.label', default: 'ShoppingCartInterfaceTestProduct')] as Object[],
                          "Another user has updated this ShoppingCartInterfaceTestProduct while you were editing")
                render(view: "edit", model: [shoppingCartInterfaceTestProductInstance: shoppingCartInterfaceTestProductInstance])
                return
            }
        }

        shoppingCartInterfaceTestProductInstance.properties = params

        if (!shoppingCartInterfaceTestProductInstance.save(flush: true)) {
            render(view: "edit", model: [shoppingCartInterfaceTestProductInstance: shoppingCartInterfaceTestProductInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'shoppingCartInterfaceTestProduct.label', default: 'ShoppingCartInterfaceTestProduct'), shoppingCartInterfaceTestProductInstance.id])
        redirect(action: "show", id: shoppingCartInterfaceTestProductInstance.id)
    }

    def delete(Long id) {
        def shoppingCartInterfaceTestProductInstance = ShoppingCartInterfaceTestProduct.get(id)
        if (!shoppingCartInterfaceTestProductInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingCartInterfaceTestProduct.label', default: 'ShoppingCartInterfaceTestProduct'), id])
            redirect(action: "list")
            return
        }

        try {
            shoppingCartInterfaceTestProductInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'shoppingCartInterfaceTestProduct.label', default: 'ShoppingCartInterfaceTestProduct'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'shoppingCartInterfaceTestProduct.label', default: 'ShoppingCartInterfaceTestProduct'), id])
            redirect(action: "show", id: id)
        }
    }
}
