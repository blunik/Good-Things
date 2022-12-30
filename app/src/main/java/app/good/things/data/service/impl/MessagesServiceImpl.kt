package app.good.things.data.service.impl

import app.good.things.data.model.CodeRequest
import app.good.things.data.model.MessageResponse
import app.good.things.data.model.ResultRequest
import app.good.things.data.service.MessagesService
import app.good.things.domain.model.Message
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


/**
 * Service which checks the code
 */
internal class MessagesServiceImpl : MessagesService {

    override suspend fun getMessage(codeRequest: CodeRequest): Result<List<MessageResponse>> =
        suspendCoroutine { cont ->
            val database = FirebaseDatabase.getInstance().reference
            val s = database.child("messages").child(codeRequest.code)
            s.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                }

                override fun onCancelled(error: DatabaseError) {
                    cont.resume(Result.failure(error.toException()))
                }
            })
        }

    override suspend fun sendMessage(messageRequest: Message): ResultRequest =
        suspendCoroutine { cont ->
            val database = FirebaseDatabase.getInstance().reference
            database.child("messages").child(messageRequest.codeIndividual)
                .child(messageRequest.date).setValue(messageRequest)
                .addOnSuccessListener {
                    cont.resume(ResultRequest.SUCCESS)
                }.addOnCanceledListener {
                    cont.resume(ResultRequest.ERROR)
                }
        }

}