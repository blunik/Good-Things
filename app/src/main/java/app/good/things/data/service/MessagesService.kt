package app.good.things.data.service

import app.good.things.data.model.*
import app.good.things.data.model.CodeRequest
import app.good.things.data.model.MessageResponse
import app.good.things.domain.model.Message

/**
 * Service which answers for messages
 */
internal interface MessagesService {

    /**
     * Functions which gets messages
     */
    suspend fun getMessage(codeRequest: CodeRequest): Result<List<MessageResponse>>

    /**
     * Functions which sends messages
     */
    suspend fun sendMessage(messageRequest: Message): ResultRequest
}