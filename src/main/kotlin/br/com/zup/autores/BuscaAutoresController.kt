package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import javax.transaction.Transactional

@Controller("/autores")
class BuscaAutoresController(val autorRepository: AutorRepository) {

    @Get
    fun lista(): HttpResponse<List<AutorResponse>>? {
        val autores = autorRepository.findAll()
        val listaAutoresResponse = autores.map { autor -> AutorResponse(autor) }
        return HttpResponse.ok(listaAutoresResponse)
    }
}