package app.good.things.domain.repositories

/**
 * Repository which checks the code
 */
internal interface LoginInCodeRepository {

    /**
     * Functions which checks the code
     */
    suspend fun checkCode(code: String): Boolean
}