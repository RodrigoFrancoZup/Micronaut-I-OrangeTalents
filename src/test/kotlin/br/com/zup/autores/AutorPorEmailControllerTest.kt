package br.com.zup.autores

import br.com.zup.client.EnderecoResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class AutorPorEmailControllerTest {

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setUp() {
        val enderecoResponse =
            EnderecoResponse(logradouro = "Rua das Laranjeiras", localidade = "Rio de Janeiro", uf = "RJ")
        val endereco = Endereco(enderecoResponse = enderecoResponse, numero = "55")
        autor = Autor(
            nome = "Rafael Ponte",
            email = "rafael.ponte@zup.com.br",
            descricao = "O mentor da OT4",
            endereco = endereco
        )

        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        autorRepository.delete(autor)
    }

    @Test
    internal fun deveRetonarOsDetalhesDeUmAutor() {

        val response =
            client.toBlocking().exchange("/autor/email?email=${autor.email}", AutorResponse::class.java)
        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.email, response.body()!!.email)
        assertEquals(autor.descricao, response.body()!!.descricao)
    }
}