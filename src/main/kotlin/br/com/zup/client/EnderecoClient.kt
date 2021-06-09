package br.com.zup.client

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client


@Client("https://viacep.com.br/ws")
interface EnderecoClient {

    //A URI a ser montada na consulta será por ex: https://viacep.com.br/ws/11111111/json
    @Get("/{cep}/json/")
    fun consulta(cep: String) : HttpResponse<EnderecoResponse>

    @Get("/{cep}/xml/")
    @Consumes(MediaType.APPLICATION_XML)
    fun consultaXml(cep: String) : HttpResponse<EnderecoResponse>

    @Get("/{cep}/xml/", consumes = [MediaType.APPLICATION_XML])
    fun consultaXml2(cep: String) : HttpResponse<EnderecoResponse>
}