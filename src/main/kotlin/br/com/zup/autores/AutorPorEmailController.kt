package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue


@Controller("/autor/email")
class AutorPorEmailController(val autorRepository: AutorRepository) {

    @Get
    fun buscaPorEmail(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        if (email.isBlank()) {
            val autores = autorRepository.findAll()
            val autoresResponse = autores.map { autor -> AutorResponse(autor) }
            return HttpResponse.ok(autoresResponse)
        }
        val possivelAutor = autorRepository.findByEmail(email)
        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        } else {
            val autorResponse = AutorResponse(possivelAutor.get())
            return HttpResponse.ok(autorResponse)
        }

    }
}

