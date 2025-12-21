package com.mk.wordcloud.service

import com.mk.wordcloud.model.Message
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class MessageProducer(
    private val rabbitTemplate: RabbitTemplate,
    @Value($$"${spring.rabbitmq.exchange}") private val exchange: String,
    @Value($$"${spring.rabbitmq.routingkey}") private val routingKey: String
) {

    fun sendMessage(message: Message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message)
    }
}