package app.good.things.domain.repositories.impl

import app.good.things.data.model.CodeRequest
import app.good.things.data.model.ResultRequest
import app.good.things.data.service.LoginInCodeService
import app.good.things.data.service.MessagesService
import app.good.things.data.service.impl.LoginInCodeServiceImpl
import app.good.things.data.service.impl.MessagesServiceImpl
import app.good.things.domain.model.Message
import app.good.things.domain.repositories.MessagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository which checks the code
 */
internal class MessagesRepositoryImpl : MessagesRepository {

    private val serviceMessage: MessagesService = MessagesServiceImpl()
    private val serviceLogin: LoginInCodeService = LoginInCodeServiceImpl()

    override suspend fun sendMessage(
        individualCode: String,
        codeReceiver: String,
        message: String
    ): ResultRequest =
        withContext(Dispatchers.IO) {
            val result = serviceLogin.checkCode(CodeRequest(codeReceiver)).getOrNull()
            if (result != null && result.isActive){
                serviceMessage.sendMessage(Message(System.currentTimeMillis().toString(), message, codeReceiver))
            } else {
                ResultRequest.NOT_FOUND_USER
            }
        }
}