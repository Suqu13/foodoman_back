package garstka.jakub.foodoman.api.controllers

import garstka.jakub.foodoman.api.custom_exceptions.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.io.IOException
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ResourceNotFoundException::class)
    @Throws(IOException::class)
    fun handleNotFound(response: HttpServletResponse) {
        response.sendError(HttpStatus.NOT_FOUND.value())
    }
}