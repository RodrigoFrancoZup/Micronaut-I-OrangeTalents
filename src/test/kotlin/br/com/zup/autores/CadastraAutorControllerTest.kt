package br.com.zup.autores

import br.com.zup.client.EnderecoClient
import br.com.zup.client.EnderecoResponse
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.annotation.TransactionMode
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject

@MicronautTest(
    rollback = false, //Padrão é true. Configura se cada função dará rollback após ser executada
    transactionMode = TransactionMode.SINGLE_TRANSACTION, // Padrao é SEPARETE_TRANSACTION. Configura se cada função terá sua própria transação
    transactional = true //O Padão já é true, mas aqui poderiamos desativar a transação! Não haveria transação na execução das funções
)
internal class CadastraAutorControllerTest {

    //Lateinit indica que será inicializado no futuro!
    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    //Já defenimos no método enderecoMock, que esse objeto será mockado
    //Criamos uma referencia agora, apenas para podermos configurar suas ações!
    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @Test
    internal fun cadastrarUmNovoAutor() {

        //Cenario
        val autorRequest = AutorRequest("Rafael Pont", "rafael.ponte@zup.com.br", "O mentor", "123456", "1")
        val enderecoResponse = EnderecoResponse(logradouro = "Rua das Laranjeiras", localidade = "Rio de Janeiro", uf = "RJ")

        //Aqui estamos simulando: quando o método consulta do enderecoCliente for executado a resposta será HttpResponse.ok com Body de enderecoResponse já criado por nós!
        Mockito.`when`(enderecoClient.consulta(autorRequest.cep)).thenReturn(HttpResponse.ok(enderecoResponse))

        val request = HttpRequest.POST("/autores", autorRequest)

        //Açao
        val response = client.toBlocking().exchange(request, Any::class.java)

        //Verificacao
        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location")!!.matches("/autores/\\d".toRegex()))

    }

    @MockBean(EnderecoClient::class)
    fun enderecoMock() : EnderecoClient{
        return Mockito.mock(EnderecoClient::class.java)
    }
}