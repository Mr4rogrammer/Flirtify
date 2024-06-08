package info.mrprogrammer.flirtify.core.utils

object FirebaseHelper {
    fun String?.firebaseClearString(): String? {
        var aString = this
        aString = aString?.replace("@", "")
        aString = aString?.replace(".", "")
        aString = aString?.replace("#", "")
        aString = aString?.replace("$", "")
        aString = aString?.replace("[", "")
        aString = aString?.replace("]", "")
        return aString
    }
}