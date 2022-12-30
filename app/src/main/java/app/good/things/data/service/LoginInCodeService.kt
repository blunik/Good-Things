package app.good.things.data.service

import app.good.things.data.model.CodeRequest
import app.good.things.data.model.CodeResponse
import kotlinx.coroutines.flow.Flow

/**
 * Service which checks the code
 */
internal interface LoginInCodeService {

    /**
     * Functions which checks the code
     */
    suspend fun checkCode(codeRequest: CodeRequest): Result<CodeResponse>
}