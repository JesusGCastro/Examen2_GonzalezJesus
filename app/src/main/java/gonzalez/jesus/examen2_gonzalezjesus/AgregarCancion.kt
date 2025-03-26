package gonzalez.jesus.examen2_gonzalezjesus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AgregarCancion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_cancion)

        var nombre: EditText = findViewById(R.id.etNombreCancion) as EditText
        var artista: EditText = findViewById(R.id.etArtistaCancion) as EditText
        var album: EditText = findViewById(R.id.etAlbumCancion) as EditText
        var duracion: EditText = findViewById(R.id.etDuracionCancion) as EditText

        var btnGuardar: Button = findViewById(R.id.btnGuardar) as Button

        btnGuardar.setOnClickListener{
            val nombreCancion = nombre.text.toString().trim()
            val artistaCancion = artista.text.toString().trim()
            val albumCancion = album.text.toString().trim()
            val duracionCancion = duracion.text.toString().trim()

            if (nombreCancion.isNotEmpty() && artistaCancion.isNotEmpty() && albumCancion.isNotEmpty() && duracionCancion.isNotEmpty()) {
                val intent = Intent()
                intent.putExtra("nombre", nombreCancion)
                intent.putExtra("artista", artistaCancion)
                intent.putExtra("album", albumCancion)
                intent.putExtra("duracion", duracionCancion)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }

    }
}