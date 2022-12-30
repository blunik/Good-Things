package app.good.things.domain.repositories

import app.good.things.data.model.CodeResponse

/**
 * Repository which checks the code
 */
internal interface LoginInCodeRepository {

    /**
     * Functions which checks the code
     */
    suspend fun checkCode(code: String): Result<CodeResponse>
}