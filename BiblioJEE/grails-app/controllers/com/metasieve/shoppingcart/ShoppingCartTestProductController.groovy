package com.metasieve.shoppingcart

import org.springframework.dao.DataIntegrityViolationException

class ShoppingCartTestProductController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [shoppingCartTestProductInstanceList: ShoppingCartTestProduct.list(params), shoppingCartTestProductInstanceTotal: ShoppingCartTestProduct.count()]
    }

    def create() {
        [shoppingCartTestProductInstance: new ShoppingCartTestProduct(params)]
    }

    def save() {
        def shoppingCartTestProductInstance = new ShoppingCartTestProduct(params)
        if (!shoppingCartTestProductInstance.save(flush: true)) {
            render(view: "create", model: [shoppingCartTestProductInstance: shoppingCartTestProductInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'shoppingCartTestProduct.label', default: 'ShoppingCartTestProduct'), shoppingCartTestProductInstance.id])
        redirect(action: "show", id: shoppingCartTestProductInstance.id)
    }

    def show(Long id) {
        def shoppingCartTestProductInstance = ShoppingCartTestProduct.get(id)
        if (!shoppingCartTestProductInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingCartTestProduct.label', default: 'ShoppingCartTestProduct'), id])
            redirect(action: "list")
            return
        }

        [shoppingCartTestProductInstance: shoppingCartTestProductInstance]
    }

    def edit(Long id) {
        def shoppingCartTestProductInstance = ShoppingCartTestProduct.get(id)
        if (!shoppingCartTestProductInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingCartTestProduct.label', default: 'ShoppingCartTestProduct'), id])
            redirect(action: "list")
            return
        }

        [shoppingCartTestProductInstance: shoppingCartTestProductInstance]
    }

    def update(Long id, Long version) {
        def shoppingCartTestProductInstance = ShoppingCartTestProduct.get(id)
        if (!shoppingCartTestProductInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingCartTestProduct.label', default: 'ShoppingCartTestProduct'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (shoppingCartTestProductInstance.version > version) {
                shoppingCartTestProductInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'shoppingCartTestProduct.label', default: 'ShoppingCartTestProduct')] as Object[],
                          "Another user has updated this ShoppingCartTestProduct while you were editing")
                render(view: "edit", model: [shoppingCartTestProductInstance: shoppingCartTestProductInstance])
                return
            }
        }

        shoppingCartTestProductInstance.properties = params

        if (!shoppingCartTestProductInstance.save(flush: true)) {
            render(view: "edit", model: [shoppingCartTestProductInstance: shoppingCartTestProductInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'shoppingCartTestProduct.label', default: 'ShoppingCartTestProduct'), shoppingCartTestProductInstance.id])
        redirect(action: "show", id: shoppingCartTestProductInstance.id)
    }

    def delete(Long id) {
        def shoppingCartTestProductInstance = ShoppingCartTestProduct.get(id)
        if (!shoppingCartTestProductInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingCartTestProduct.label', default: 'ShoppingCartTestProduct'), id])
            redirect(action: "list")
            return
        }

        try {
            shoppingCartTestProductInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'shoppingCartTestProduct.label', default: 'ShoppingCartTestProduct'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'shoppingCartTestProduct.label', default: 'ShoppingCartTestProduct'), id])
            redirect(action: "show", id: id)
        }
    }
}
