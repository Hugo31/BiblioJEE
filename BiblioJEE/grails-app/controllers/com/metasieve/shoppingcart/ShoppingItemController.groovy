package com.metasieve.shoppingcart

import org.springframework.dao.DataIntegrityViolationException

class ShoppingItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [shoppingItemInstanceList: ShoppingItem.list(params), shoppingItemInstanceTotal: ShoppingItem.count()]
    }

    def create() {
        [shoppingItemInstance: new ShoppingItem(params)]
    }

    def save() {
        def shoppingItemInstance = new ShoppingItem(params)
        if (!shoppingItemInstance.save(flush: true)) {
            render(view: "create", model: [shoppingItemInstance: shoppingItemInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'shoppingItem.label', default: 'ShoppingItem'), shoppingItemInstance.id])
        redirect(action: "show", id: shoppingItemInstance.id)
    }

    def show(Long id) {
        def shoppingItemInstance = ShoppingItem.get(id)
        if (!shoppingItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingItem.label', default: 'ShoppingItem'), id])
            redirect(action: "list")
            return
        }

        [shoppingItemInstance: shoppingItemInstance]
    }

    def edit(Long id) {
        def shoppingItemInstance = ShoppingItem.get(id)
        if (!shoppingItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingItem.label', default: 'ShoppingItem'), id])
            redirect(action: "list")
            return
        }

        [shoppingItemInstance: shoppingItemInstance]
    }

    def update(Long id, Long version) {
        def shoppingItemInstance = ShoppingItem.get(id)
        if (!shoppingItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingItem.label', default: 'ShoppingItem'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (shoppingItemInstance.version > version) {
                shoppingItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'shoppingItem.label', default: 'ShoppingItem')] as Object[],
                          "Another user has updated this ShoppingItem while you were editing")
                render(view: "edit", model: [shoppingItemInstance: shoppingItemInstance])
                return
            }
        }

        shoppingItemInstance.properties = params

        if (!shoppingItemInstance.save(flush: true)) {
            render(view: "edit", model: [shoppingItemInstance: shoppingItemInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'shoppingItem.label', default: 'ShoppingItem'), shoppingItemInstance.id])
        redirect(action: "show", id: shoppingItemInstance.id)
    }

    def delete(Long id) {
        def shoppingItemInstance = ShoppingItem.get(id)
        if (!shoppingItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingItem.label', default: 'ShoppingItem'), id])
            redirect(action: "list")
            return
        }

        try {
            shoppingItemInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'shoppingItem.label', default: 'ShoppingItem'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'shoppingItem.label', default: 'ShoppingItem'), id])
            redirect(action: "show", id: id)
        }
    }
}
