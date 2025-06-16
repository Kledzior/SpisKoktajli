package com.example.mobilecocktails.LocalDatabase

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun populateCocktails(context: Context, dao: CocktailDao) {
    val cocktailsDetails =
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
            "★Lemonade★" to Pair(
                listOf("120ml Woda gazowana", "60ml Sok z cytryny", "30ml Syrop cukrowy", "Lód", "Plasterek cytryny do dekoracji", "Listki mięty"),
                "Do szklanki typu highball wsyp lód, dodaj sok z cytryny i syrop cukrowy. Wymieszaj. Dolej wodę gazowaną i delikatnie zamieszaj. Udekoruj plasterkiem cytryny i listkami mięty."
            ),
            "★Shirley Temple★" to Pair(
                listOf("150ml Sprite lub 7Up", "30ml Grenadyna", "Lód", "1 Wiśnia koktajlowa", "Plasterek cytryny lub pomarańczy"),
                "Napełnij szklankę lodem. Wlej grenadynę, a następnie dopełnij napojem gazowanym (Sprite/7Up). Delikatnie zamieszaj. Udekoruj wiśnią koktajlową i plasterkiem cytrusa."
            ),
            "★Virgin Mojito★" to Pair(
                listOf("10 listków mięty", "20ml Sok z limonki", "30ml Syrop cukrowy", "100ml Woda gazowana", "Lód kruszony", "Plasterki limonki do dekoracji"),
                "W szklance rozgnieć miętę z sokiem z limonki i syropem cukrowym. Dodaj kruszony lód i dopełnij wodą gazowaną. Zamieszaj delikatnie i udekoruj plasterkami limonki oraz miętą."
            )
        )


    CoroutineScope(Dispatchers.IO).launch {
        dao.deleteAll()

        val cocktails = cocktailsDetails.map { (name, pair) ->
            Cocktail(
                name = name,
                ingredients = pair.first.joinToString(", "),
                preparation = pair.second
            )
        }.toTypedArray()

        dao.insertAll(*cocktails)
    }
}