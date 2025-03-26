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
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.compose.composable
import androidx.compose.ui.Alignment

import com.example.mobilecocktails.ui.theme.MobileCocktailsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileCocktailsTheme {
                AppNavigation()
            }
        }
    }
}
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "splashScreen") {
        composable("splashScreen") {
            SplashScreen(navController)
        }
        composable("cocktailList") {
            CocktailListWithDetails()
        }
    }
}
@Composable
fun SplashScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Witamy w aplikacji Mobile Cocktails! 🍹",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("cocktailList") }) {
            Text(text = "Przejdź do koktajli")
        }
    }
}
@Composable
fun CocktailListWithDetails() {
    val cocktails = remember {
        listOf("Cosmopolitan", "Whiskey Sour", "Piña Colada", "Mai Tai","Daiquiri", "Manhattan","Mojito", "Gin Fizz", "Caipirinha", "Long Island Iced Tea","Negroni", "Bloody Mary", "Tequila Sunrise","Espresso Martini")
    }

    val cocktailsDetails = remember {
        mapOf(
            "Cosmopolitan" to Pair(
                listOf("40ml Wódka cytrynowa", "20ml Triple Sec", "20ml Sok z limonki", "30ml Sok żurawinowy"),
                "Wymieszaj w shakerze z lodem, przelej do schłodzonego kieliszka koktajlowego."
            ),
            "Whiskey Sour" to Pair(
                listOf("50ml Bourbon", "25ml Sok z cytryny", "15ml Syrop cukrowy", "1 białko jaja (opcjonalnie)"),
                "Wstrząśnij w shakerze z lodem, przelej do szklanki typu old-fashioned."
            ),
            "Piña Colada" to Pair(
                listOf("50ml Rum biały", "50ml Mleko kokosowe", "100ml Sok ananasowy"),
                "Zmiksuj z lodem, podawaj w wysokiej szklance z plastrem ananasa."
            ),
            "Mai Tai" to Pair(
                listOf("40ml Rum biały", "20ml Rum ciemny", "15ml Triple Sec", "10ml Syrop migdałowy", "10ml Sok z limonki"),
                "Wstrząśnij w shakerze z lodem i przelej do szklanki."
            ),
            "Daiquiri" to Pair(
                listOf("50ml Rum biały", "25ml Sok z limonki", "15ml Syrop cukrowy", "Lód"),
                "W shakerze wymieszaj rum, sok z limonki i syrop cukrowy. Dodaj lód i mocno wstrząśnij. Przelej do schłodzonego kieliszka koktajlowego."
            ),
            "Manhattan" to Pair(
                listOf("50ml Whiskey żytnia", "20ml Słodki wermut", "2 krople Angostury"),
                "Wymieszaj w szklanicy barmańskiej z lodem i przelej do kieliszka koktajlowego."
            ),
            "Mojito" to Pair(
                listOf("50ml Rum biały", "Soda", "Pół limonki", "Świeża mięta", "2 łyżeczki cukru", "Lód"),
                "W szklance rozgnieć limonkę z cukrem. Dodaj świeżą miętę i delikatnie ją ugnieć. Wrzuć kostki lodu, wlej rum i dopełnij sodą. Zamieszaj i udekoruj listkami mięty."
            ),
            "Gin Fizz" to Pair(
                listOf("50ml Gin", "25ml Sok z cytryny", "15ml Syrop cukrowy", "Soda"),
                "Wstrząśnij w shakerze i dopełnij wodą sodową."
            ),
            "Caipirinha" to Pair(
                listOf("50ml Cachaca", "1 Limonka", "2 łyżeczki cukru"),
                "Rozgnieć limonkę z cukrem, dodaj lód i cachacę, zamieszaj."
            ),
            "Long Island Iced Tea" to Pair(
                listOf("20ml Wódka", "20ml Gin", "20ml Rum", "20ml Tequila", "20ml Triple Sec", "20ml Sok z cytryny", "Cola"),
                "Wstrząśnij w shakerze i dopełnij colą."
            ),
            "Negroni" to Pair(
                listOf("30ml Gin", "30ml Campari", "30ml Wermut czerwony", "Pomarańcza do dekoracji", "Lód"),
                "W szklance wymieszaj gin, Campari i czerwony wermut. Dodaj kostki lodu i delikatnie zamieszaj. Udekoruj plasterkiem pomarańczy."
            ),
            "Bloody Mary" to Pair(
                listOf("50ml Wódka", "100ml Sok pomidorowy", "10ml Sok z cytryny", "Szczypta soli i pieprzu", "Kropla sosu Worcestershire", "Kropla Tabasco"),
                "Wymieszaj w szklance z lodem, podawaj z selerem naciowym."
            ),
            "Tequila Sunrise" to Pair(
                listOf("50ml Tequila", "100ml Sok pomarańczowy", "10ml Grenadyna"),
                "Wlej tequilę i sok pomarańczowy do szklanki z lodem, powoli dodaj grenadynę."
            ),
            "Espresso Martini" to Pair(
                listOf("40ml Wódka", "20ml Likier kawowy (np. Kahlua)", "30ml Świeżo parzona kawa espresso", "10ml Syrop cukrowy", "Lód", "3 ziarna kawy do dekoracji"),
                "Do shakera z lodem dodaj wódkę, likier kawowy, espresso i syrop cukrowy. Wstrząśnij energicznie przez około 15 sekund. Przelej do schłodzonego kieliszka koktajlowego przez sitko barmańskie. Udekoruj trzema ziarnami kawy."
            ),
        )
    }




    var selectedCocktail by remember { mutableStateOf("Cosmopolitan") }

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
                text = "Wybrany: $selectedCocktail",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Składniki:",
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
                text = "Sposób przygotowania:",
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
