package com.mk.wordcloud.config

import kotlinx.serialization.json.Json
import org.springframework.amqp.core.*
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.amqp.core.Message as AmqpMessage


@Configuration
class RabbitMQConfig(
    @Value($$"${spring.rabbitmq.queue}") private val queueName: String,
    @Value($$"${spring.rabbitmq.exchange}") private val exchange: String,
    @Value($$"${spring.rabbitmq.routingkey}") private val routingKey: String
) {

    @Bean
    fun queue(): Queue {
        return Queue(queueName, false)
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(exchange)
    }

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey)
    }

    @Bean
    fun jsonMessageConverter(): MessageConverter {
        return object : MessageConverter {
            override fun toMessage(`object`: Any, messageProperties: MessageProperties): AmqpMessage {
                val jsonString = Json.encodeToString(`object` as com.mk.wordcloud.model.Message)
                val bytes = jsonString.toByteArray()
                messageProperties.contentType = MessageProperties.CONTENT_TYPE_JSON
                messageProperties.contentLength = bytes.size.toLong()
                return AmqpMessage(bytes, messageProperties)
            }

            override fun fromMessage(message: AmqpMessage): Any {
                val content = String(message.body)
                return Json.decodeFromString<com.mk.wordcloud.model.Message>(content)
            }
        }
    }
}