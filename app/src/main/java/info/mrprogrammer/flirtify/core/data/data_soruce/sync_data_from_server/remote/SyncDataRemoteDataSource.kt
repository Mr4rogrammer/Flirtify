package info.mrprogrammer.flirtify.core.data.data_soruce.sync_data_from_server.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import info.mrprogrammer.flirtify.core.data.data_soruce.sync_data_from_server.SyncDataDataSource
import info.mrprogrammer.flirtify.core.utils.FirebaseHelper.firebaseClearString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SyncDataRemoteDataSource @Inject constructor() :
    SyncDataDataSource {
    override suspend fun syncData(): Flow<Boolean> {
        val sharedFlow = MutableSharedFlow<Boolean>()
        val key = "data"
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val url = currentUser.email.firebaseClearString() + "/pillsData"
            val ref = FirebaseDatabase.getInstance().getReference(key).child(url)
            ref.addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val map = snapshot.value as MutableMap<String, Any>
                    map["id"] = snapshot.key ?: ""
                    if (map.containsKey("takenStatus")) {
                        val takenStatusList =
                            (map["takenStatus"] as HashMap<String, Any>).toList().map { it.second }
                        map.remove("takenStatus")
                        map.put("takenStatus", takenStatusList)
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        sharedFlow.emit(true)
                        println()
                    }
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    val map = snapshot.value as MutableMap<String, Any>
                    map["id"] = snapshot.key ?: ""
                    if (map.containsKey("takenStatus")) {
                        val takenStatusList =
                            (map["takenStatus"] as HashMap<String, Any>).toList().map { it.second }
                        map.remove("takenStatus")
                        map.put("takenStatus", takenStatusList)
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        sharedFlow.emit(true)
                        println()
                    }
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                    CoroutineScope(Dispatchers.IO).launch {
                        sharedFlow.emit(true)
                    }
                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

                }

                override fun onCancelled(error: DatabaseError) {

                }


            })
        }
        return sharedFlow.asSharedFlow()
    }
}