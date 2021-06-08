package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Controller("/autores")
@Validated
class CadastraAutorController (val autorRepository: AutorRepository)  {

    @Post
    fun cadastra(@Body @Valid request: AutorRequest) : HttpResponse<Any>{
        val autor = request.converteAutorRequestParaAutor()
        autorRepository.save(autor)

        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.created(uri)
    }

}