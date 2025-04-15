package com.example.mobilecocktails.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

val LimeGreen = Color(0xFF6CD47D)
val OrangeSunset = Color(0xFFFF9F45)
val CocktailBlue = Color(0xFF3A86FF)
val Surface = Color(0xFF74B644)
val Purple40 = CocktailBlue
val PurpleGrey40 = LimeGreen
val Pink40 = OrangeSunset


private val LightColorScheme = lightColorScheme(
    primary = CocktailBlue,
    secondary = LimeGreen,
    tertiary = OrangeSunset,
    surface= Surface
)

private val DarkColorScheme = darkColorScheme(
    primary = CocktailBlue,
    secondary = LimeGreen,
    tertiary = OrangeSunset,
    surface= Surface
)

