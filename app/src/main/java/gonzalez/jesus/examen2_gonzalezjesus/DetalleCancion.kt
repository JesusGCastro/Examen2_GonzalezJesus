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

        var nombre : TextView = findViewById(R.id.nombreCancion) as TextView
        var artista : TextView = findViewById(R.id.artistaCancion) as TextView
        var album : TextView = findViewById(R.id.albumCancion) as TextView
        var duracion : TextView = findViewById(R.id.duracionCancion) as TextView

        var bundle = intent.extras

        if (bundle != null){
            nombre.setText(bundle.getInt("nombre"))
            artista.setText(bundle.getInt("artista"))
            album.setText("Album: "+ bundle.getInt("album"))
            duracion.setText(bundle.getInt("duracion"))
        }

        var btnAtras : ImageView = findViewById(R.id.btnAtras) as ImageView
        var btnPlay : Button = findViewById(R.id.btnPlay) as Button
        var btnEliminar: TextView = findViewById(R.id.btnEliminarCancion) as TextView

        btnAtras.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnPlay.setOnClickListener{
            Toast.makeText(this, "Reproduciendo...", Toast.LENGTH_SHORT).show()
        }

        btnEliminar.setOnClickListener{
            val intent = Intent()
            intent.putExtra("nombre", nombre.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}