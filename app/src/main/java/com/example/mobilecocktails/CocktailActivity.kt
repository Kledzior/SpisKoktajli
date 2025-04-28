package com.example.mobilecocktails

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobilecocktails.ui.theme.MobileCocktailsTheme
import kotlinx.coroutines.launch
import android.util.Log
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.navigation.NavController

class CocktailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CocktailApp", "Przejście do drugiej aktywnosci:")

        val cocktaiilName = intent.getStringExtra("cocktail_name")

        enableEdgeToEdge()
        setContent {
            MobileCocktailsTheme {
                 CocktailListWithDetails(cocktaiilName)

                }
            }
        }
    }

@Composable
fun CocktailListWithDetails(cocktailName: String?) {
    Log.d("CocktailApp", "Przejście do szczegółów koktajlu: $cocktailName")
    val cocktailsDetails = rememberSaveable {
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
                "Do shakera z lodem dodaj wódkę, likier kawowy, espresso i syrop cukrowy. Wstrząśnij energicznie przez około 15 sekund. Przelej do schłodzonego kieliszka koktajlowego przez sitko barmańskie. Udekoruj trzema ziarnami kawy. Wypij w ciagu 60 sekund"
            ),
        )
    }


    var selectedCocktail by rememberSaveable { mutableStateOf(cocktailName) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AppToolbarWithImage(selectedCocktail)
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val (ingredients, _) = cocktailsDetails[selectedCocktail] ?: Pair(emptyList(), "")
                    val message = if (ingredients.isNotEmpty()) {
                        "Składniki: ${ingredients.joinToString(", ")}"
                    } else {
                        "Brak danych dla $selectedCocktail"
                    }
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            ) {
                Icon(Icons.Outlined.Info, contentDescription = "Pokaż składniki")
            }
        },
        content = { innerPadding ->

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(4.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp, top = 24.dp)
                ) {
                }

                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .weight(2f)
                        .verticalScroll(scrollState)
                        .padding(top = 24.dp)
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

                    val secondsList = extractSecondsFromText(preparation)
                    CountdownTimer(secondsList)
                }
            }
        }
    )
}


fun extractSecondsFromText(text: String): List<Int> {
    val regex = Regex("""(\d+)\s*(sekund(?:y|a|))""", RegexOption.IGNORE_CASE)
    return regex.findAll(text).mapNotNull { matchResult ->
        matchResult.groups[1]?.value?.toIntOrNull()
    }.toList()
}

@Composable
fun CountdownTimer(durationSeconds: List<Int>) {
    var currentIndex by rememberSaveable { mutableStateOf(0) }
    val currentDuration = durationSeconds.getOrNull(currentIndex)

    if (currentDuration != null) {
        var timeLeft by rememberSaveable { mutableIntStateOf(currentDuration) }
        var isRunning by rememberSaveable { mutableStateOf(false) }
        var isPaused by rememberSaveable { mutableStateOf(false) }

        LaunchedEffect(key1 = currentIndex, key2 = isRunning, key3 = isPaused) {
            while (timeLeft > 0 && isRunning && !isPaused) {
                kotlinx.coroutines.delay(1000L)
                timeLeft--
            }
            if (timeLeft == 0 && currentIndex < durationSeconds.size - 1) {
                isPaused = true
                currentIndex++
                timeLeft = durationSeconds[currentIndex]
            }
        }

        Column(
            modifier = Modifier.padding(top = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text("⏱ $timeLeft sek", modifier = Modifier.padding(end = 8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {

                if (timeLeft == currentDuration) {
                    Button(onClick = {
                        timeLeft = currentDuration
                        isRunning = true
                        isPaused = false
                    }) {
                        Text("Start")
                    }
                }


                if(timeLeft != currentDuration) {
                    Button(onClick = {
                        isRunning = false
                        timeLeft = currentDuration
                        isPaused = false
                    }) {
                        Text("Stop")
                    }
                }

                if (timeLeft in 1 until currentDuration) {
                    Button(onClick = {
                        isPaused = !isPaused
                        if (!isPaused) {
                            isRunning = true
                        }
                    }) {
                        Text(if (isPaused) "Wznów" else "Przerwij")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbarWithImage(cocktailName: String?) {
    val context = LocalContext.current
    val imageName = cocktailName
        ?.lowercase()
        ?.replace(" ", "")
        ?.replace("ñ", "n")
        ?.replace("[^a-z0-9_]".toRegex(), "")
    val imageResId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

    TopAppBar(
        title = {
            if (imageResId != 0) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "$cocktailName image",
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )
            } else {
                Text(text = cocktailName ?: "Brak nazwy", style = MaterialTheme.typography.titleMedium)
            }
        },
//        navigationIcon = {
//            IconButton(onClick = {
//                // Możesz zamknąć aktywność
//                (context as? ComponentActivity)?.finish()
//            }) {
//                Icon(Icons.Default.ArrowBack, contentDescription = "Wróć")
//            }
//        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}