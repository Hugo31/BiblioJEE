package com.metasieve.shoppingcart

import org.springframework.dao.DataIntegrityViolationException

class ShoppableController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [shoppableInstanceList: Shoppable.list(params), shoppableInstanceTotal: Shoppable.count()]
    }

    def create() {
        [shoppableInstance: new Shoppable(params)]
    }

    def save() {
        def shoppableInstance = new Shoppable(params)
        if (!shoppableInstance.save(flush: true)) {
            render(view: "create", model: [shoppableInstance: shoppableInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'shoppable.label', default: 'Shoppable'), shoppableInstance.id])
        redirect(action: "show", id: shoppableInstance.id)
    }

    def show(Long id) {
        def shoppableInstance = Shoppable.get(id)
        if (!shoppableInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppable.label', default: 'Shoppable'), id])
            redirect(action: "list")
            return
        }

        [shoppableInstance: shoppableInstance]
    }

    def edit(Long id) {
        def shoppableInstance = Shoppable.get(id)
        if (!shoppableInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppable.label', default: 'Shoppable'), id])
            redirect(action: "list")
            return
        }

        [shoppableInstance: shoppableInstance]
    }

    def update(Long id, Long version) {
        def shoppableInstance = Shoppable.get(id)
        if (!shoppableInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppable.label', default: 'Shoppable'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (shoppableInstance.version > version) {
                shoppableInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'shoppable.label', default: 'Shoppable')] as Object[],
                          "Another user has updated this Shoppable while you were editing")
                render(view: "edit", model: [shoppableInstance: shoppableInstance])
                return
            }
        }

        shoppableInstance.properties = params

        if (!shoppableInstance.save(flush: true)) {
            render(view: "edit", model: [shoppableInstance: shoppableInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'shoppable.label', default: 'Shoppable'), shoppableInstance.id])
        redirect(action: "show", id: shoppableInstance.id)
    }

    def delete(Long id) {
        def shoppableInstance = Shoppable.get(id)
        if (!shoppableInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppable.label', default: 'Shoppable'), id])
            redirect(action: "list")
            return
        }

        try {
            shoppableInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'shoppable.label', default: 'Shoppable'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'shoppable.label', default: 'Shoppable'), id])
            redirect(action: "show", id: id)
        }
    }
}
