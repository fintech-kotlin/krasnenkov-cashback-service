package ru.tinkoff.fintech.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import ru.tinkoff.fintech.model.Card

@Service
class CardServiceClientImpl(
    private val restTemplate: RestTemplate,
    @Value("\${services.url.card}")
    private val url: String
) : CardServiceClient {

    override fun getCard(cardNumber: String): Card =
        restTemplate.getForObject("$url$cardNumber", Card::class.java, mapOf("id" to cardNumber))!!

}