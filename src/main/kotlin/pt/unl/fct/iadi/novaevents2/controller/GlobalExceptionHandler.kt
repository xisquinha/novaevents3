package pt.unl.fct.iadi.novaevents2.controller

import org.springframework.http.HttpStatus
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(ex: NoSuchElementException): String{
        return "error/404"
    }

    @ExceptionHandler(EventNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleEventNotFound(ex: EventNotFoundException): String{
        return "error/404"
    }

    @ExceptionHandler(EventBadlyCreatedException::class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    fun handleInvalidEvent(ex: NoSuchElementException): String{
        return "error/404"
    }


    @ExceptionHandler(DuplicateEventNameException::class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    fun handleDupEventName(ex: RuntimeException): String{
        return "error/403"
    }
}