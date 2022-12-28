package app.good.things.data.service.impl

import app.good.things.data.model.CodeRequest
import app.good.things.data.model.CodeResponse
import app.good.things.data.service.LoginInCodeService
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Service which checks the code
 */
internal class LoginInCodeServiceImpl : LoginInCodeService {

    override suspend fun checkCode(codeRequest: CodeRequest): CodeResponse =
        suspendCoroutine { cont ->
            val database = FirebaseDatabase.getInstance().reference
            val s = database.child("code")
            s.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.hasChild(codeRequest.code)) {
                        cont.resume(CodeResponse(true))
                    } else cont.resume(CodeResponse(false))
                }

                override fun onCancelled(error: DatabaseError) {
                    cont.resume(CodeResponse(false))
                }
            })
        }

}