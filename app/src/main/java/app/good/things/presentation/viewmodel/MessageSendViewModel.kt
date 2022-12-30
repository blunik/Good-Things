package app.good.things.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.good.things.data.model.ResultRequest
import app.good.things.domain.repositories.LoginInCodeRepository
import app.good.things.domain.repositories.MessagesRepository
import app.good.things.domain.repositories.impl.LoginInCodeRepositoryImpl
import app.good.things.domain.repositories.impl.MessagesRepositoryImpl
import app.good.things.presentation.model.CodeStatus
import app.good.things.presentation.prefs
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for message sending screen
 */
internal class MessageSendViewModel : ViewModel() {

    private val repository: MessagesRepository = MessagesRepositoryImpl()
    private var _messageStatus: MutableSharedFlow<ResultRequest> = MutableSharedFlow()

    /**
     * Status code in backend
     */
    val messageStatus: SharedFlow<ResultRequest> = _messageStatus

    /**
     * Function send message
     */
    fun sendMessage(codeReceiver: String, message: String) {
        viewModelScope.launch {
            _messageStatus.emit(repository.sendMessage(prefs.individualCode.toString(), codeReceiver, message))
        }
    }
}