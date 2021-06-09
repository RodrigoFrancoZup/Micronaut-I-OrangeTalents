package br.com.zup.autores

import br.com.zup.client.EnderecoClient
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Controller("/autores")
@Validated
class CadastraAutorController(
    val autorRepository: AutorRepository,
    val enderecoClient: EnderecoClient
) {

    @Post
    fun cadastra(@Body @Valid request: AutorRequest): HttpResponse<Any> {

        val enderecoResponse = enderecoClient.consulta(request.cep)
        val autor = request.converteAutorRequestParaAutor(enderecoResponse.body()!!)
        autorRepository.save(autor)

        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", autor.id)))
        return HttpResponse.created(uri)
    }

}