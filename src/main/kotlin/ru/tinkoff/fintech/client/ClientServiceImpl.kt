package ru.tinkoff.fintech.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import ru.tinkoff.fintech.model.Client

@Service
class ClientServiceImpl(
    private val restTemplate: RestTemplate,
    @Value("\${services.url.client}")
    private val url: String
) : ClientService {

    override fun getClient(id: String): Client =
        restTemplate.getForObject("$url$id", Client::class.java, mapOf("id" to id))!!

}