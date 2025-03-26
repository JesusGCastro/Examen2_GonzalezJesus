package gonzalez.jesus.examen2_gonzalezjesus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var adapter: CancionAdapter? = null
    var canciones = ArrayList<Cancion>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cargarCanciones()

        adapter = CancionAdapter(this, canciones)

        var gridview: GridView = findViewById(R.id.gvCanciones) as GridView
        gridview.adapter = adapter

    }

    fun cargarCanciones(){
        canciones.add(Cancion("Shape of You", "Ed Sheeran", "3:53", "Divide"))
        canciones.add(Cancion("Bohemian Rhapsody", "Queen", "5:55", "A Night at the Opera"))
        canciones.add(Cancion("Hotel California", "Eagles", "6:30", "Hotel California"))
        canciones.add(Cancion("Smells Like Teen Spirit", "Nirvana", "5:01", "Nevermind"))
        canciones.add(Cancion("Billie Jean", "Michael Jackson", "4:54", "Thriller"))
        canciones.add(Cancion("Someone Like You", "Adele", "4:45", "21"))
    }
}

class CancionAdapter: BaseAdapter {
    var canciones = ArrayList<Cancion>()
    var context: Context? = null

    constructor(context: Context, canciones: ArrayList<Cancion>){
        this.context = context
        this.canciones = canciones
    }

    override fun getCount(): Int {
        return canciones.size
    }

    override fun getItem(p0: Int): Any {
        return canciones[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var cancion = canciones[p0]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista = inflator.inflate(R.layout.cancion, null)

        var nombre: TextView = vista.findViewById(R.id.tvNombreCancionItem) as TextView
        nombre.setText(cancion.nombre)

        var artista: TextView = vista.findViewById(R.id.tvArtistaCancionItem) as TextView
        artista.setText(cancion.artista)

        nombre.setOnClickListener {
            var intent = Intent(context, DetalleCancion::class.java)
            intent.putExtra("nombre", cancion.nombre)
            intent.putExtra("artista", cancion.artista)
            intent.putExtra("duracion", cancion.duracion)
            intent.putExtra("album", cancion.album)
            context!!.startActivity(intent)
        }

        return vista
    }


}