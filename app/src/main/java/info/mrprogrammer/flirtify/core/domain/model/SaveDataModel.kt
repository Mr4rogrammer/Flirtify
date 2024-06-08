package info.mrprogrammer.flirtify.core.domain.model

data class SaveDataModel(var key:String? = null,var endKey:String? = null , var type:String = "set", var value: HashMap<String, Any?>? = hashMapOf())