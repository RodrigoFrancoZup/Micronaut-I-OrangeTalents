package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces

@Controller("/autores")
class BuscaAutoresController(val autorRepository: AutorRepository) {

    @Get
    @Produces(MediaType.APPLICATION_XML)
    fun lista(): HttpResponse<List<AutorResponse>>? {
        val autores = autorRepository.findAll()
        val listaAutoresResponse = autores.map { autor -> AutorResponse(autor) }
        return HttpResponse.ok(listaAutoresResponse)
    }
}