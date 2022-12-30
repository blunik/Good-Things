package app.good.things.data.model

import com.google.firebase.database.IgnoreExtraProperties

/**
 * Class which gets message from Firbase for showing
 */
@IgnoreExtraProperties
internal data class MessageResponse(val date: String? = null, val text: String? = null, val codeIndividual: String? = null)