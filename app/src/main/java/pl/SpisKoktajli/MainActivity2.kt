package pl.SpisKoktajli

import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import pl.SpisKoktajli.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnLongClickListener{//przytrzymanie
            Toast.makeText(applicationContext,
                "Przytrzymales przycisk",
                Toast.LENGTH_LONG).show()
            false//konsumpcja zdarzenia - jak puszcze przycisk to konczy sie akcja przytrzymania i pozniej konczy sie akcja wcisniecia stad dostane jakbym przytrzymal a potem wcisnal
            //dla true - zachowa sie tylko jakbym przytrzymal
            //nacisnięcie + przytrzymanie
            //jesli true - przytrzymanie
        }

//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main2)
//
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}