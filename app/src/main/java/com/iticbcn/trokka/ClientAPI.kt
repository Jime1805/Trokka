package com.iticbcn.trokka

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class ClientAPI {
    companion object {
        private var mProducteAPI: ProducteService? = null
        private var mUsuariAPI: UsuariService? = null

        @Synchronized
        fun ProducteAPI(): ProducteService {
            if (mProducteAPI == null) {

                val gsondateformat = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create()

                // Client HTTP insegur (només per desenvolupament)
                val unsafeOkHttpClient = getUnsafeOkHttpClient()

                mProducteAPI = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gsondateformat))
                    .baseUrl("http://150.136.106.185:8080/")
                    .client(unsafeOkHttpClient) // Afegeix el client
                    .build()
                    .create(ProducteService::class.java)
            }
            return mProducteAPI!!
        }

        @Synchronized
        fun UsuariAPI(): UsuariService {
            if (mUsuariAPI == null) {

                val gsondateformat = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create()

                // Client HTTP insegur (només per desenvolupament)
                val unsafeOkHttpClient = getUnsafeOkHttpClient()

                mUsuariAPI = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gsondateformat))
                    .baseUrl("http://150.136.106.185:8080/")
                    .client(unsafeOkHttpClient) // Afegeix el client
                    .build()
                    .create(UsuariService::class.java)
            }
            return mUsuariAPI!!
        }

        private fun getUnsafeOkHttpClient(): OkHttpClient {
            try {
                // Crea un trust manager que NO valida certificats
                val trustAllCerts = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
                        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
                        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                    }
                )

                // Instal·la el trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())
                val sslSocketFactory = sslContext.socketFactory

                return OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                    .hostnameVerifier { _, _ -> true } // Accepta qualsevol hostname
                    .build()

            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}