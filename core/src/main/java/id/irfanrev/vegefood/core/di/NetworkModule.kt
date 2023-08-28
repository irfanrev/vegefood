package id.irfanrev.vegefood.core.di

import id.irfanrev.vegefood.core.data.source.remote.service.ApiService
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        createOkHttp()
    }

    single {
        createRetrofit(get())
    }

    single {
        createApiService(get())
    }
}

fun createOkHttp(): OkHttpClient {
    val hostname = "themealdb.com"
    val certificatePinner = CertificatePinner.Builder()
        .add(hostname, "sha256/fE7WRZKFF4GE4LkOvZFU9iN5JSXg6roVAxA/iyO3ABw=")
        .add(hostname, "sha256/81Wf12bcLlFHQAfJluxnzZ6Frg+oJ9PWY/Wrwur8viQ=")
        .add(hostname, "sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc=")
        .build()
    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .certificatePinner(certificatePinner)
        .build()
}

fun createRetrofit(
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun createApiService(
    retrofit: Retrofit
): ApiService {
    return retrofit.create(ApiService::class.java)
}