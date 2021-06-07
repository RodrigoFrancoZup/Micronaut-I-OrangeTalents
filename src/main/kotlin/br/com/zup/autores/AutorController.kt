package br.com.zup.autores

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Controller("/autores")
@Validated
class AutorController {

    @Post
    fun cadastra(@Body @Valid request: AutorRequest){
        println(request)
    }
}