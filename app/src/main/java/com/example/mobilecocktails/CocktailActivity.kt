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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
//import androidx.compose.material.icons.Icon
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.foundation.layout.Arrangement
import com.example.mobilecocktails.LocalDatabase.CocktailDatabase
import com.example.mobilecocktails.LocalDatabase.Cocktail



import androidx.compose.material.rememberDrawerState
import androidx.compose.material.DrawerValue


import androidx.compose.material.icons.filled.Menu

import android.content.Context
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.*

import androidx.compose.material.icons.filled.Star
import androidx.compose.foundation.clickable
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.*

import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.compose.material.icons.filled.ArrowBack





import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalDensity

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

fun PaddingValues.coercedTop(minTop: Dp): Dp {
    return this.calculateTopPadding().coerceAtLeast(minTop)
}


@Composable
fun CocktailListWithDetails(cocktailName: String?) {
    Log.d("CocktailApp", "Przejście do szczegółów koktajlu: $cocktailName")

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var cocktail by remember { mutableStateOf<Cocktail?>(null) }

    LaunchedEffect(cocktailName) {
        val db = CocktailDatabase.getDatabase(context)
        cocktail = db.cocktailDao().getCocktailByName(cocktailName ?: "")
    }

    val ingredients = cocktail?.ingredients?.split("\n") ?: emptyList()
    val preparation = cocktail?.preparation ?: "Brak danych"

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()
    val maxHeight = 270f
    val minHeight = 56f

//    val context = LocalContext.current
    var selectedCocktail by rememberSaveable { mutableStateOf(cocktailName) }
    val scaffoldState = rememberScaffoldState(drawerState = drawerState)

    val imageName = selectedCocktail
        ?.lowercase()
        ?.replace(" ", "")
        ?.replace("ñ", "n")
        ?.replace("[^a-z0-9_]".toRegex(), "")
    val imageResId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
    val activity = LocalActivity.current as? ComponentActivity
    val snackbarHostState = remember { SnackbarHostState() }
//    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            androidx.compose.material.TopAppBar(
                title = { Text(text = selectedCocktail ?: "Koktajl") },

                navigationIcon = {
                    IconButton(onClick = { activity?.onBackPressedDispatcher?.onBackPressed() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Wstecz")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
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
        })
    { innerPadding ->
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.surface),

        ) {

            item {
                val scrollOffset = minOf(scrollState.firstVisibleItemScrollOffset, with(LocalDensity.current) { maxHeight.dp.toPx() }.toInt())

                Image(
                    painter = painterResource(id = if (imageResId != 0) imageResId else R.drawable.trollface),
                    contentDescription = "$selectedCocktail Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(maxHeight.dp)
                        .graphicsLayer {
                            translationY = -scrollOffset.toFloat()
                        }
                        .fillMaxWidth()
                )
            }

            item {
                Text(
                    text = "Wybrany: $cocktailName",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(25.dp)
                )
                if (selectedCocktail != null) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 25.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        FavoriteStar(selectedCocktail!!)
                    }
                }
            }


            item {
                Text(
                    text = "Składniki:",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 25.dp, top = 8.dp)

                )
            }

            items(ingredients) { ingredient ->
                Text(
                    text = "- $ingredient",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 40.dp, top = 4.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Sposób przygotowania:",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 25.dp)
                )

                Text(
                    text = preparation,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 40.dp, top = 4.dp, bottom = 16.dp)
                )
                val secondsList = extractSecondsFromText(preparation)
                CountdownTimer(secondsList)
            }
        }
    }
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

fun saveFavorites(context: Context, favorites: Set<String>) {
    val sharedPreferences = context.getSharedPreferences("favorites_prefs", Context.MODE_PRIVATE)
    sharedPreferences.edit()
        .putStringSet("favorite_cocktails", favorites)
        .apply()
}

fun loadFavorites(context: Context): MutableSet<String> {
    val sharedPreferences = context.getSharedPreferences("favorites_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getStringSet("favorite_cocktails", emptySet())?.toMutableSet() ?: mutableSetOf()
}

@Composable
fun FavoriteStar(cocktailName: String) {
    val context = LocalContext.current
    var favorites by remember { mutableStateOf(loadFavorites(context).toMutableSet()) }

    val currentFavorites = rememberUpdatedState(favorites)

    val isFavorite = currentFavorites.value.contains(cocktailName)
    val HoneyYellow = Color(0xFFFFC107)
    Icon(
        imageVector = Icons.Default.Star,
        contentDescription = null,
        tint = if (isFavorite) HoneyYellow else Color.Gray,
        modifier = Modifier.clickable {
            val updatedFavorites = currentFavorites.value.toMutableSet()
            if (isFavorite) {
                updatedFavorites.remove(cocktailName)
            } else {
                updatedFavorites.add(cocktailName)
            }
            saveFavorites(context, updatedFavorites)

            favorites = updatedFavorites
        }
    )
}





