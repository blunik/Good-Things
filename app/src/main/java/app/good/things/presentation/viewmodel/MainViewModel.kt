package app.good.things.presentation.viewmodel

import androidx.lifecycle.ViewModel
import app.good.things.domain.model.Message
import app.good.things.presentation.prefs
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query

/**
 * ViewModel for message receiving screen
 */
internal class MainViewModel : ViewModel() {

    /**
     * Load data for recyclerView
     */
    fun loadData(): FirebaseRecyclerOptions<Message> {
        val query: Query = FirebaseDatabase.getInstance()
            .reference
            .child("messages").child(prefs.individualCode.toString())
        val options = FirebaseRecyclerOptions.Builder<Message>()
            .setQuery(
                query
            ) { snapshot ->
                Message(
                    snapshot.child("date").value.toString(),
                    snapshot.child("text").value.toString(),
                    snapshot.child("codeIndividual").value.toString()
                )
            }
            .build()
        return options
    }

}