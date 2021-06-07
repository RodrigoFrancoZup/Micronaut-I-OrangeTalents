package br.com.zup.autores

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/autores")
class AutorController {

    @Post
    fun cadastra(@Body request: AutorRequest){
        println(request)
    }
}