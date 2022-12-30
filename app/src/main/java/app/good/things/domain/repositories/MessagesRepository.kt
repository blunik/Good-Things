package app.good.things.domain.repositories

import app.good.things.data.model.ResultRequest

/**
 * Repository which answers for messages
 */
internal interface MessagesRepository {

    /**
     * Functions which sends messages
     */
    suspend fun sendMessage(individualCode: String, codeReceiver: String, message: String): ResultRequest
}