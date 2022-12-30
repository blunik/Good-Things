package app.good.things.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.good.things.domain.repositories.LoginInCodeRepository
import app.good.things.domain.repositories.impl.LoginInCodeRepositoryImpl
import app.good.things.presentation.model.CodeStatus
import app.good.things.presentation.prefs
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for Login screen
 */
internal class LoginViewModel : ViewModel() {

    private val repository: LoginInCodeRepository = LoginInCodeRepositoryImpl()

    private var _codeStatus: MutableSharedFlow<CodeStatus> = MutableSharedFlow()

    /**
     * Status code in backend
     */
    val codeStatus: SharedFlow<CodeStatus> = _codeStatus

    /**
     * Function with check code
     */
    fun checkCode(code: String) {
        viewModelScope.launch {
            _codeStatus.emit(
                repository.checkCode(code).fold(
                    onSuccess = {
                        when (it.isActive) {
                            true -> {
                                prefs.individualCode = code
                                CodeStatus.VALID_CODE
                            }
                            false -> CodeStatus.INVALID_CODE
                        }

                    },
                    onFailure = {
                        CodeStatus.ERROR
                    }
                ))
        }
    }
}