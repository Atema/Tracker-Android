package nl.expeditiegrensland.tracker.backend.types

import android.util.Log
import nl.expeditiegrensland.tracker.types.Expeditie

sealed class Result<out V> {

    class Value<out V>(val value: V) : Result<V>()
    class Error(val error: BackendException) : Result<Nothing>()

    fun <R> runIfValue(action: (V) -> R) =
            if (this is Result.Value) action(value)
            else null

    fun <R> runIfError(action: (BackendException) -> R) =
            if (this is Result.Error) action(error)
            else null

    companion object {
        fun <T> catchError(action: () -> T): Result<T> =
                try {
                    Result.Value(action())
                } catch (error: BackendException) {
                    Log.e("BackendException", error.message)
                    Log.e("BackendException", Log.getStackTraceString(error))
                    Result.Error(error)
                }
    }

}

class BackendException(val code: Int,
                       error: String = "Unspecified")
    : Exception("$code: $error")


typealias AuthResult = Result<AuthResponse>

data class AuthResponse(val token: String = "",
                        val name: String = "")


typealias ExpeditiesResult = Result<List<Expeditie>>