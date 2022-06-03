package mx.edu.tecmm.ejemploretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class activity_agregar : AppCompatActivity() {
    lateinit var txtId: EditText
    lateinit var txtNombre: EditText
    lateinit var txtApellido: EditText
    lateinit var txtEdad: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)


        txtId= findViewById(R.id.edId)
        txtNombre= findViewById(R.id.edNombre)
        txtApellido= findViewById(R.id.edApellido)
        txtEdad= findViewById(R.id.edEdad)
    }
    fun estoDebeIrBotonGuardar(v: View){

        val persona= Persona(txtNombre.text.toString(),
            txtApellido.text.toString(),txtId.text.toString(),
            txtEdad.text.toString());

        val retrofit = RetrofitClase.getRetrofit()
        val retrofitService = retrofit.create(IPersona::class.java)
        val peticion: Call<Resultado> = retrofitService.agregar(persona)

        peticion.enqueue(object: Callback<Resultado> {
            override fun onResponse(call: Call<Resultado>, response: Response<Resultado>) {
                val resultado = response.body()
                mandaralerta(resultado)

            }

            override fun onFailure(call: Call<Resultado>, t: Throwable) {
                Log.e("RETROFIT", "OCURRIO UN ERROR${t.message}")
            }

        })
        finish()
    }
    private fun mandaralerta(resultado: Resultado?){
        Toast.makeText(this, "Resultado es ${resultado!!.estado}",
            Toast.LENGTH_LONG).show()
    }
}