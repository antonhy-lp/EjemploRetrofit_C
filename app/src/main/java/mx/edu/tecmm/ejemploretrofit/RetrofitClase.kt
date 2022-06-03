package mx.edu.tecmm.ejemploretrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClase {
    companion object{
        fun getRetrofit(): Retrofit{
            val retrofit = Retrofit.Builder().baseUrl("http://187.217.234.227/7mo/crud/")
                .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit
        }
    }
}