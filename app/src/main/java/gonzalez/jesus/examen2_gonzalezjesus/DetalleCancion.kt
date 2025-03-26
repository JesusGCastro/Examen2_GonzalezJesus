package gonzalez.jesus.examen2_gonzalezjesus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetalleCancion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_cancion)

        val nombre: TextView = findViewById(R.id.nombreCancion)
        val artista: TextView = findViewById(R.id.artistaCancion)
        val album: TextView = findViewById(R.id.albumCancion)
        val duracion: TextView = findViewById(R.id.duracionCancion)

        // Obtener los datos correctamente como String
        val bundle = intent.extras
        if (bundle != null) {
            nombre.text = bundle.getString("nombre", "Desconocido")
            artista.text = bundle.getString("artista", "Desconocido")
            album.text = "Álbum: " + bundle.getString("album", "Desconocido")
            duracion.text = bundle.getString("duracion", "0:00")
        }

        val btnAtras: ImageView = findViewById(R.id.btnAtras)
        val btnPlay: Button = findViewById(R.id.btnPlay)
        val btnEliminar: TextView = findViewById(R.id.btnEliminarCancion)

        btnAtras.setOnClickListener {
            finish()
        }

        btnPlay.setOnClickListener {
            Toast.makeText(this, "Reproduciendo...", Toast.LENGTH_SHORT).show()
        }

        btnEliminar.setOnClickListener {
            val intent = Intent()
            intent.putExtra("nombre", nombre.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}