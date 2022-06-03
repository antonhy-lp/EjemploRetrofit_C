package mx.edu.tecmm.ejemploretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var  recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.personas_rv)
    }

    override fun onResume() {
        super.onResume()

        val retrofit = RetrofitClase.getRetrofit()
        val retrofitService = retrofit.create(IPersona::class.java)
        val peticion: Call<List<Persona>> = retrofitService.getListaPersona()

        peticion.enqueue(object: Callback<List<Persona>>{
            override fun onResponse(call: Call<List<Persona>>, response: Response<List<Persona>>) {
                val lista = response.body();
                llenarRecycler(lista!!)
            }

            override fun onFailure(call: Call<List<Persona>>, t: Throwable) {
               Log.e("RETROFIT", "OCURRIO UN ERROR${t.message}")
            }

        })


    }
    fun llenarRecycler(datos:List<Persona>){
        val adapter = AdaptadorPersona(this, datos)
        recycler.adapter = adapter
    }

    fun click_boton(v: View){
      estoDebeIrBotonGuardar()
    }

    fun estoDebeIrBotonGuardar(){
        val persona = Persona ("Isaac", "Rivers", "4989", "20")
        val retrofit = RetrofitClase.getRetrofit()
        val retrofitService = retrofit.create(IPersona::class.java)
        val peticion: Call<Resultado> = retrofitService.agregar(persona)

        peticion.enqueue(object: Callback<Resultado>{
            override fun onResponse(call: Call<Resultado>, response: Response<Resultado>) {
             val resultado = response.body()
                mandaralerta(resultado)

            }

            override fun onFailure(call: Call<Resultado>, t: Throwable) {
                Log.e("RETROFIT", "OCURRIO UN ERROR${t.message}")
            }


        })
    }
    private fun mandaralerta(resultado: Resultado?){

        if (resultado!!.estado.equals("OK")){
            Toast.makeText(this, "Grabado correctamente", Toast.LENGTH_LONG).show();
            finish()
        }else{
            Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_LONG).show();
        }
    }
    fun ventanaAgregarShow(v: View){
        val intent= Intent(this, activity_agregar:: class.java);
        startActivity(intent);
    }
}