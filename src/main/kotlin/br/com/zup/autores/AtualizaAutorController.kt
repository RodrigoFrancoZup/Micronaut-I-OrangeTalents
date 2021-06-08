package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import javax.transaction.Transactional

@Controller("/autores/{id}")
class AtualizaAutorController(val autorRepository: AutorRepository) {

    @Put
    @Transactional
    fun atualiza(@PathVariable id: Long, descricao: String): HttpResponse<Any> {

        val possivelAutor = autorRepository.findById(id)
        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }

        val autor = possivelAutor.get()
        autor.descricao = descricao
       /*
       Agora o método atualiza() está por inteiro dentro de uma transação,
       Logo o objeto autor já está managed pela JPA! E por isso a linha a seguir não é necessária:

        autorRepository.update(autor)
        */
        return HttpResponse.ok(AutorResponse(autor))
    }
}