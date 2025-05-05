package com.example.mobilecocktails

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.mobilecocktails.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.*
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.core.content.ContextCompat.startActivity
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Text
import com.example.mobilecocktails.ui.theme.MobileCocktailsTheme
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.filled.Star
import androidx.compose.foundation.clickable
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Menu
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import androidx.compose.material3.Button
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Menu
import kotlinx.coroutines.launch
import androidx.navigation.NavOptionsBuilder
import androidx.compose.material.Card
import kotlin.text.lowercase
import android.animation.ObjectAnimator
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import kotlin.random.Random

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

    NavHost(navController, startDestination = "loadingScreen") {
        composable("loadingScreen")
        {
            LoadingScreen(navController)
        }

        composable("splashScreen") {
            SplashScreen(navController)
        }
        composable("cocktailList") {
            CocktailList(navController)

        }

        composable("favorites") {
            FavoritesList(navController)
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
            text = "Witamy w aplikacji Mobile Cocktails! \uD83C\uDF79 \uD83D\uDE0E",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("cocktailList") }) {
            Text(text = "Przejdź do koktajli")
        }
    }
}
@Composable
fun CocktailList(navController: NavController) {
    val cocktails = rememberSaveable {
        listOf("Cosmopolitan", "Whiskey Sour", "Piña Colada", "Mai Tai","Daiquiri", "Manhattan","Mojito", "Gin Fizz", "Caipirinha", "Long Island Iced Tea","Negroni", "Bloody Mary", "Tequila Sunrise","Espresso Martini")
    }




    val tabs = listOf("Alkoholowe", "Bezalkoholowe")
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }

    //val alcoholicCocktails = listOf("Whiskey Sour", "Mojito", "Negroni")
    val nonAlcoholicCocktails = listOf("★Virgin Mojito★", "★Shirley Temple★", "★Lemonade★")

    val currentList = if (selectedTabIndex == 0) cocktails else nonAlcoholicCocktails



    var selectedCocktail by rememberSaveable { mutableStateOf("Cosmopolitan") }
    //val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val contextCockTail = LocalContext.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(drawerState = drawerState)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            androidx.compose.material.TopAppBar(
                title = { Text("Witamy w MobileCocktails\u00AE") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch { drawerState.open() }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                }
            )
        },

        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(300.dp)
                    .padding(16.dp)
            ){
            Text(
                text = "\uD83C\uDFE0 Główna Strona \uD83C\uDFE0", fontSize = 20.sp,
                modifier = Modifier

                    .fillMaxWidth().padding(16.dp)
                    .clickable {
                        scope.launch { drawerState.close() }
                        navController.navigate("cocktailList") { popUpTo("cocktailList") }
                    }
                    .padding(top = 50.dp)
            )
            Text(
                text = "\u2B50 Ulubione \u2B50", fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth().padding(10.dp)
                    .clickable {
                        scope.launch { drawerState.close() }
                        navController.navigate("favorites") { popUpTo("cocktailList") }
                    }
                    .padding(top = 30.dp)
            )
        }
        },
        content = { innerPadding ->

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
            {
                androidx.compose.material3.TabRow(selectedTabIndex = selectedTabIndex) {
                    tabs.forEachIndexed { index, title ->
                        androidx.compose.material3.Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = { Text(title) }
                        )
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp, top = 20.dp)
                ) {
                    items(currentList) { cocktail ->
                        Card(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .clickable {
                                    Log.d("CocktailApp", "Kliknięto koktajl: $cocktail")
                                    selectedCocktail = cocktail
                                    val intent = Intent(contextCockTail,CocktailActivity::class.java)
                                    intent.putExtra("cocktail_name", selectedCocktail)
                                    contextCockTail.startActivity(intent)
                                           },
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                val context = LocalContext.current
                                val imageName = cocktail
                                    .lowercase()
                                    .replace(" ", "")
                                    .replace("ñ", "n")
                                    .replace("[^a-z0-9_]".toRegex(), "")
                                    .replace("★", "")
                                val imageResId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

                                Image(
                                    painter = painterResource(id = if (imageResId != 0) imageResId else R.drawable.trollface),
                                    contentDescription = "$cocktail Image",
                                    modifier = Modifier
                                        .height(270.dp)
                                        .fillMaxWidth()
                                        .background(Color.DarkGray),
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    text = cocktail,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(8.dp),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }

            }
        }
    )
}
@Composable
fun FavoritesList(navController: NavController) {
    val context = LocalContext.current
    var selectedCocktail by rememberSaveable { mutableStateOf("Cosmopolitan") }
    // cała lista koktajli (tę samą co w CocktailList)
    val allCocktails = remember {
        listOf("Cosmopolitan", "Whiskey Sour", "Piña Colada", "Mai Tai","Daiquiri", "Manhattan","Mojito", "Gin Fizz", "Caipirinha", "Long Island Iced Tea","Negroni", "Bloody Mary", "Tequila Sunrise","Espresso Martini","★Virgin Mojito★", "★Shirley Temple★", "★Lemonade★")
    }


    val favoritesSet = remember { loadFavorites(context) }
    val favorites = allCocktails.filter { it in favoritesSet }
    val contextCockTail = LocalContext.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scaffoldState = rememberScaffoldState(drawerState = drawerState)
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            androidx.compose.material.TopAppBar(
                title = { Text("\u2B50 Ulubione \u2B50") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch { drawerState.open() }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(300.dp)
                    .padding(16.dp)
            ){
                Text(
                    text = "\uD83C\uDFE0 Główna Strona \uD83C\uDFE0", fontSize = 20.sp,
                    modifier = Modifier

                        .fillMaxWidth().padding(16.dp)
                        .clickable {
                            scope.launch { drawerState.close() }
                            navController.navigate("cocktailList") { popUpTo("cocktailList") }
                        }
                        .padding(top = 50.dp)
                )
                Text(
                    text = "\u2B50 Ulubione \u2B50", fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth().padding(10.dp)
                        .clickable {
                            scope.launch { drawerState.close() }
                            navController.navigate("favorites") { popUpTo("cocktailList") }
                        }
                        .padding(top = 30.dp)
                )
            }
        },
     content = { innerPadding ->

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(4.dp)
        )
        {

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp, top = 24.dp)
            ) {
                items(favorites) { cocktail ->
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .clickable {
                                Log.d("CocktailApp", "Kliknięto koktajl: $cocktail")
                                selectedCocktail = cocktail
                                val intent = Intent(contextCockTail,CocktailActivity::class.java)
                                intent.putExtra("cocktail_name", selectedCocktail)
                                contextCockTail.startActivity(intent)
                            },
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            val context = LocalContext.current
                            val imageName = cocktail
                                .lowercase()
                                .replace(" ", "")
                                .replace("ñ", "n")
                                .replace("[^a-z0-9_]".toRegex(), "")
                                .replace("★", "")
                            val imageResId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

                            Image(
                                painter = painterResource(id = if (imageResId != 0) imageResId else R.drawable.trollface),
                                contentDescription = "$cocktail Image",
                                modifier = Modifier
                                    .height(270.dp)
                                    .fillMaxWidth()
                                    .background(Color.DarkGray),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = cocktail,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(8.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }

        }
    })
}

@Composable
fun LoadingScreen(navController: NavController)
{
    val context = LocalContext.current

    val allCocktails = remember {
        listOf("Cosmopolitan", "Whiskey Sour", "Piña Colada", "Mai Tai","Daiquiri", "Manhattan","Mojito", "Gin Fizz", "Caipirinha", "Long Island Iced Tea","Negroni", "Bloody Mary", "Tequila Sunrise","Espresso Martini","★Virgin Mojito★", "★Shirley Temple★", "★Lemonade★")
    }

    val uniqueRandomList = (0..allCocktails.size).shuffled().take(3)
    var vCounter by remember { mutableStateOf(0) }
    val imageResIds = remember {
        allCocktails.mapNotNull { cocktail ->
            val imageName = cocktail
                .lowercase()
                .replace(" ", "")
                .replace("ñ", "n")
                .replace("[^a-z0-9_]".toRegex(), "")
                .replace("★", "")
            val resId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
            if (resId != 0) resId else null
        }
    }
    var currentImageIndex by remember { mutableStateOf(uniqueRandomList[0])}
    val imageViewRef = remember { mutableStateOf<ImageView?>(null) }

    LaunchedEffect(currentImageIndex) {
        val imageView = imageViewRef.value
        imageView?.let {
            // Początek poza ekranem po prawej
            it.translationX = 1000f
            it.setImageResource(imageResIds[currentImageIndex])

            // Animacja przesuwania w lewo
            val animator = ObjectAnimator.ofFloat(it, "translationX", 1000f, -1000f)
            animator.duration = 1500
            animator.start()

            delay(1500) // Czekaj aż obrazek zniknie

            if (vCounter < 2) {
                vCounter += 1
                currentImageIndex = uniqueRandomList[vCounter]
            } else {
                navController.navigate("splashScreen") {
                    popUpTo("loadingScreen") { inclusive = true }
                }
            }
        }
    }
    AndroidView(
        modifier = Modifier
            .fillMaxSize(),
            factory = {
                context ->
                ImageView(context).apply{
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    imageViewRef.value = this
//                    background = null
                    setBackgroundColor(Color.Red.toArgb())

                }
            },
            update = { imageView ->
            }
    )

}
