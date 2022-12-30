package app.good.things.domain.repositories.impl

import app.good.things.data.model.CodeRequest
import app.good.things.data.model.CodeResponse
import app.good.things.data.service.LoginInCodeService
import app.good.things.data.service.impl.LoginInCodeServiceImpl
import app.good.things.domain.repositories.LoginInCodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository which checks the code
 */
internal class LoginInCodeRepositoryImpl : LoginInCodeRepository {

    private val service: LoginInCodeService = LoginInCodeServiceImpl()

    override suspend fun checkCode(code: String): Result<CodeResponse> =
        withContext(Dispatchers.IO) {
            service.checkCode(CodeRequest(code))
        }
}