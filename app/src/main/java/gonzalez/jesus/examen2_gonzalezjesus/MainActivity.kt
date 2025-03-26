package gonzalez.jesus.examen2_gonzalezjesus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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

        val nombreEliminar = intent.getStringExtra("nombre")
        if (nombreEliminar != null) {
            eliminarCancion(nombreEliminar)
        }

        var btnAgregar : Button = findViewById(R.id.btnAgregarCancion) as Button

        btnAgregar.setOnClickListener {
            val intent = Intent(this, AgregarCancion::class.java)
            startActivityForResult(intent, 2)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            val nombreCancion = data.getStringExtra("nombre")

            when (requestCode) {
                1 -> { // Eliminar canción
                    if (!nombreCancion.isNullOrEmpty()) {
                        eliminarCancion(nombreCancion)
                    }
                }
                2 -> { // Agregar canción
                    val artistaCancion = data.getStringExtra("artista")
                    val albumCancion = data.getStringExtra("album")
                    val duracionCancion = data.getStringExtra("duracion")

                    if (!nombreCancion.isNullOrEmpty() && !artistaCancion.isNullOrEmpty() &&
                        !albumCancion.isNullOrEmpty() && !duracionCancion.isNullOrEmpty()) {

                        canciones.add(Cancion(nombreCancion, artistaCancion, duracionCancion, albumCancion))
                        adapter?.notifyDataSetChanged()
                        Toast.makeText(this, "Canción agregada: $nombreCancion", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    fun cargarCanciones(){
        canciones.add(Cancion("Shape of You", "Ed Sheeran", "3:53", "Divide"))
        canciones.add(Cancion("Bohemian Rhapsody", "Queen", "5:55", "A Night at the Opera"))
        canciones.add(Cancion("Hotel California", "Eagles", "6:30", "Hotel California"))
        canciones.add(Cancion("Smells Like Teen Spirit", "Nirvana", "5:01", "Nevermind"))
        canciones.add(Cancion("Billie Jean", "Michael Jackson", "4:54", "Thriller"))
        canciones.add(Cancion("Someone Like You", "Adele", "4:45", "21"))
    }

    fun eliminarCancion(nombre: String) {
        val iterator = canciones.iterator()
        while (iterator.hasNext()) {
            val cancion = iterator.next()
            if (cancion.nombre.equals(nombre, ignoreCase = true)) {
                iterator.remove()
                adapter?.notifyDataSetChanged()
                Toast.makeText(this, "Canción eliminada: $nombre", Toast.LENGTH_SHORT).show()
                return
            }
        }
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
            val intent = Intent(context, DetalleCancion::class.java)
            intent.putExtra("nombre", cancion.nombre)
            intent.putExtra("artista", cancion.artista)
            intent.putExtra("duracion", cancion.duracion)
            intent.putExtra("album", cancion.album)

            if (context is MainActivity) {
                (context as MainActivity).startActivityForResult(intent, 1)
            }
        }


        return vista
    }


}