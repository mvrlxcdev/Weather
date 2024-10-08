package data.remote.utils

import utils.ResultState

suspend fun <T : Any?> apiCall(apiCall: suspend () -> T): ResultState<T> {
    return try {
        ResultState.Loading

        ResultState.Success(apiCall.invoke())
    } catch (e: Exception) {
        ResultState.Failure(exception = e)
    }
}
