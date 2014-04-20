package com.metasieve.shoppingcart

import org.springframework.dao.DataIntegrityViolationException

class QuantityController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [quantityInstanceList: Quantity.list(params), quantityInstanceTotal: Quantity.count()]
    }

    def create() {
        [quantityInstance: new Quantity(params)]
    }

    def save() {
        def quantityInstance = new Quantity(params)
        if (!quantityInstance.save(flush: true)) {
            render(view: "create", model: [quantityInstance: quantityInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'quantity.label', default: 'Quantity'), quantityInstance.id])
        redirect(action: "show", id: quantityInstance.id)
    }

    def show(Long id) {
        def quantityInstance = Quantity.get(id)
        if (!quantityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quantity.label', default: 'Quantity'), id])
            redirect(action: "list")
            return
        }

        [quantityInstance: quantityInstance]
    }

    def edit(Long id) {
        def quantityInstance = Quantity.get(id)
        if (!quantityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quantity.label', default: 'Quantity'), id])
            redirect(action: "list")
            return
        }

        [quantityInstance: quantityInstance]
    }

    def update(Long id, Long version) {
        def quantityInstance = Quantity.get(id)
        if (!quantityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quantity.label', default: 'Quantity'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (quantityInstance.version > version) {
                quantityInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'quantity.label', default: 'Quantity')] as Object[],
                          "Another user has updated this Quantity while you were editing")
                render(view: "edit", model: [quantityInstance: quantityInstance])
                return
            }
        }

        quantityInstance.properties = params

        if (!quantityInstance.save(flush: true)) {
            render(view: "edit", model: [quantityInstance: quantityInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'quantity.label', default: 'Quantity'), quantityInstance.id])
        redirect(action: "show", id: quantityInstance.id)
    }

    def delete(Long id) {
        def quantityInstance = Quantity.get(id)
        if (!quantityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quantity.label', default: 'Quantity'), id])
            redirect(action: "list")
            return
        }

        try {
            quantityInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'quantity.label', default: 'Quantity'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'quantity.label', default: 'Quantity'), id])
            redirect(action: "show", id: id)
        }
    }
}
