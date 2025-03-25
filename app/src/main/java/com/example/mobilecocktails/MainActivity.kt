package com.example.mobilecocktails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobilecocktails.ui.theme.MobileCocktailsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileCocktailsTheme {
                CocktailListWithDetails()
            }
        }
    }
}

@Composable
fun CocktailListWithDetails() {
    val cocktails = remember {
        listOf("Mojito", "Margarita", "Old Fashioned", "Daiquiri", "Negroni")
    }
    val cocktailsDetails = remember {
        mapOf(
            "Mojito" to Pair(
                listOf("50ml Rum", "Soda", "Limonka", "Mięta", "Cukier"),
                "Rozgnieć limonkę z cukrem, dodaj miętę, rum, lód i dopełnij sodą."
            ),
            "Margarita" to Pair(
                listOf("50ml Tequila", "20ml Triple sec", "20ml Sok z limonki", "Sól"),
                "Wymieszaj składniki w shakerze, przelej do kieliszka z solą na rancie."
            ),
            "Old Fashioned" to Pair(
                listOf("50ml Bourbon", "Kostka cukru", "Angostura", "Woda"),
                "Rozpuść cukier z Angosturą, dodaj lód i bourbon, zamieszaj."
            ),
            "Daiquiri" to Pair(
                listOf("50ml Rum", "25ml Sok z limonki", "15ml Syrop cukrowy"),
                "Wymieszaj w shakerze i przelej do schłodzonego kieliszka."
            ),
            "Negroni" to Pair(
                listOf("30ml Gin", "30ml Campari", "30ml Wermut czerwony"),
                "Wymieszaj składniki w szklance z lodem, udekoruj pomarańczą."
            )
        )
    }


    var selectedCocktail by remember { mutableStateOf("Mojito") }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()  // Wypełnia całą dostępną wysokość
                .widthIn(max = 150.dp)  // Ogranicza szerokość lewej kolumny
                .padding(end = 16.dp,top = 8.dp )  // Padding między kolumnami
                .background(Color(0xFF0c2a36))
        ) {
            items(cocktails) { cocktail ->
                Text(
                    text = cocktail,
                    color = Color.LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedCocktail = cocktail
                        }
                        .padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
            .padding(top=10.dp)
        ) {
            val (ingredients, preparation) = cocktailsDetails[selectedCocktail] ?: Pair(emptyList(), "Brak danych")

            Text(
                text = "Selected: $selectedCocktail",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Ingredients:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
            ingredients.forEach { ingredient ->
                Text(
                    text = "- $ingredient",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Preparation:",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = preparation,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CocktailListPreview() {
    MobileCocktailsTheme {
        CocktailListWithDetails()
    }
}
