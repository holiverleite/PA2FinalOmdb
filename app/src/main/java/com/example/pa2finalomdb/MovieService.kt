package com.example.pa2finalomdb

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieService {
    // Cliente Http que conterá o interceptador de requisição para cabeçalhos dinâmicos
    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    // Instanciando o cliente HTTP
    init {
        // Adiciona um interceptador que é um objeto de uma classe anônima
        okHttpClientBuilder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {

                // Resgatando requisição interceptada
                val reqInterceptada: Request = chain.request()

                val url = reqInterceptada.url().newBuilder().addQueryParameter("apikey", Constantes.API_KEY_OMDB).build()

                // Criando nova requisição a partir da interceptada e adicionando campos de cabeçalho
                val novaReq: Request = reqInterceptada.newBuilder()
                    .url(url)
                    .method(reqInterceptada.method(), reqInterceptada.body())
                    .build()

//                val novaReq: Request = reqInterceptada.newBuilder()
//                    .header(APP_ID_FIELD, APP_ID_VALUE)
//                    .header(APP_KEY_FIELD, APP_KEY_VALUE)
//                    .method(reqInterceptada.method(), reqInterceptada.body())
//                    .build()

                return chain.proceed(novaReq)
            }
        })
    }

    // Novo objeto Retrofit usando a URL base e o HttpClient com interceptador
    val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constantes.URL_BASE)
        .client(okHttpClientBuilder.build())
        .build()

    // Cria um objeto, a partir da Interface Retrofit, que contém as funções de requisição
    val chamadaApi: ChamadasOmdbAPI = retrofit.create(ChamadasOmdbAPI::class.java)

    fun buscarFilmeComOTitulo(titulo: String) {

        chamadaApi.requestFilmByTitle("teste").enqueue(
            // Implementar requisicao com callback
        )
    }
}