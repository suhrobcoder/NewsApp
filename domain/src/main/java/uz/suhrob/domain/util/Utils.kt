package uz.suhrob.domain.util

inline fun <T> safeCall(action: () -> Resource<T>): Resource<T> {
    return try {
        action()
    } catch(e: Exception) {
        Resource.Error(e.message ?: "Something went wrong")
    }
}

fun Int.toTimeString(): String {
    val hour = this / 60
    val minute = this % 60
    return (if (hour > 0) "$hour h " else "") + (if (minute > 0) "$minute min" else "")
}