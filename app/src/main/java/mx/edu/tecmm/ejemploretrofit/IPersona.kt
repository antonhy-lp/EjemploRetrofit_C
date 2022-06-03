package mx.edu.tecmm.ejemploretrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IPersona {
    @GET("wsLeer.php")
    fun getListaPersona(): Call<List<Persona>>

    @POST("agrega.php")
    fun agregar(@Body persona: Persona): Call<Resultado>
}


data class Resultado (val estado:String)