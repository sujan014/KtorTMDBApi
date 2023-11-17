package com.example.ktortmdbapi.data.network

import android.util.Log
import com.example.ktortmdbapi.secret.TmdbApiKeys
import kotlinx.serialization.json.Json
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*

import javax.inject.Inject

class TmdbHttpClient @Inject constructor(){

    // will return an Http client
    fun getHttpClient(): HttpClient {
        return HttpClient(Android){
            // inside this scope ,we configure our HTTP client

            // install json feature
            install(JsonFeature){
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json{// use kotlinx.serialization.json
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(Logging){
                logger = object: Logger{
                    override fun log(message: String) {
                        Log.i(TAG_KTOR_LOGGER, message)
                    }
                }
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.i(TAG_HTTP_STATUS_LOGGER, "${response.status.value}")
                    // get Http status response code
                }
            }

            // pass API key as query parameter
            install(DefaultRequest){
                // define header and pass 2 parameters
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                parameter("api_key", TmdbApiKeys.API_KEY)
            }

            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }
    }

    companion object {
        private const val TIME_OUT = 10_000
        private const val TAG_KTOR_LOGGER = "ktor_logger:"
        private const val TAG_HTTP_STATUS_LOGGER = "http_status:"
    }
}