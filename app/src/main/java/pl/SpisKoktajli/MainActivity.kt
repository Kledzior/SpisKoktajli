package pl.SpisKoktajli

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.SpisKoktajli.databinding.ActivityMainBinding
import pl.SpisKoktajli.ui.theme.SpisKoktajliTheme

class MainActivity : ComponentActivity() {

    private lateinit var myText: TextView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        setContentView(R.layout.activity_main)
//
//        myText = findViewById(R.id.text)
//        myText.text = "Nowy tekst"
//
//
//        myText = findViewById(R.id.text)



        binding = ActivityMainBinding.inflate(layoutInflater)//Zamiana XML na kod kotlina
        setContentView(binding.root)//uchwyt do głównego kontenera layoutu

//        binding.button.setOnClickListener {//nasłuchuj nacisniecia
//            Toast.makeText(
//                applicationContext, "Nacisnales przycisk",
//                Toast.LENGTH_SHORT).show()
//            //wyswietl dymek
//        }

        binding.button.setOnClickListener{
            val intent = Intent(applicationContext,MainActivity2::class.java)//tworzenie intencji
//            val data = binding.textinput.text.toString()//dane z pola input
//            intent.putExtra("DATA",data)//przekaz dane: identyfikator -> dane
            startActivity(intent)//uruchom aktywnosc
        }

        binding.button.setOnLongClickListener{//przytrzymanie
            Toast.makeText(applicationContext,
                "Przytrzymales przycisk",
                Toast.LENGTH_LONG).show()
            false//konsumpcja zdarzenia - jak puszcze przycisk to konczy sie akcja przytrzymania i pozniej konczy sie akcja wcisniecia stad dostane jakbym przytrzymal a potem wcisnal
            //dla true - zachowa sie tylko jakbym przytrzymal
            //nacisnięcie + przytrzymanie
            //jesli true - przytrzymanie
        }



        binding.text.text = "Nowy Tekst"

        enableEdgeToEdge()



//        setContent {
//            SpisKoktajliTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpisKoktajliTheme {
        Greeting("Android")
    }
}