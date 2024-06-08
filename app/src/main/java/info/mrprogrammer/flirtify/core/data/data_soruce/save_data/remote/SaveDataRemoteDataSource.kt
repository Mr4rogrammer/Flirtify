package info.mrprogrammer.flirtify.core.data.data_soruce.save_data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import info.mrprogrammer.flirtify.core.data.data_soruce.save_data.SaveDataDataSource
import info.mrprogrammer.flirtify.core.domain.model.SaveDataModel
import info.mrprogrammer.flirtify.core.utils.FirebaseHelper.firebaseClearString
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SaveDataRemoteDataSource: SaveDataDataSource {
    override suspend fun saveData(data: SaveDataModel): Boolean {
        val firebaseUser = FirebaseAuth.getInstance().currentUser ?: return false
        val userKey = firebaseUser.email.firebaseClearString()
        val baseKey = data.key
        var dataKey = "$baseKey/$userKey"
        if (data.endKey != null) {
            dataKey = "$baseKey/$userKey/${data.endKey}"
        }
        val dataRef = FirebaseDatabase.getInstance().getReference(dataKey)
        return suspendCoroutine { continuation ->
            if(data.type == "set") {
                dataRef.setValue(data.value).addOnCompleteListener {
                    if (it.isSuccessful) {
                        continuation.resume(true)
                    } else {
                        continuation.resume(false)
                    }
                }
            } else {
                val localRef = dataRef.push()
                localRef.setValue(data.value).addOnCompleteListener {
                    if (it.isSuccessful) {
                        continuation.resume(true)
                    } else {
                        continuation.resume(false)
                    }
                }
            }
        }
    }
}