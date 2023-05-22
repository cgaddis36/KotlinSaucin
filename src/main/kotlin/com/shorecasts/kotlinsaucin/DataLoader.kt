package com.shorecasts.kotlinsaucin

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class DataLoader(var hotSauceRepository: HotSauceRepository) {

    fun String.trimIndentsAndRemoveNewLines() = this.trimIndent().replace("/n", " ")

    @PostConstruct
    fun loadData() {
        hotSauceRepository.saveAll(listOf(
            HotSauce(
                brandName = "Truff",
                sauceName = "Hot Sauce",
                description = """
                    our sauce is saucy
                """.trimIndentsAndRemoveNewLines(),
                url = "hotsaucebaby.com",
                heat = 2_500
            ),
            HotSauce(
                brandName = "Truff",
                sauceName = "Hotter Sauce",
                description = """
                    TRUFF Hotter Sauce is a jalapeño rich blend of red chili peppers, Black Truffle and Black Truffle 
                    Oil, Organic Agave Nectar, Red Habanero Powder, Organic Cumin and Organic Coriander..
                    """.trimIndentsAndRemoveNewLines(),
                url = "https://truffhotsauce.com/collections/sauce/products/hotter-truff-hot-sauce",
                heat = 4_000
            ),
            HotSauce(
                brandName = "Cholula",
                sauceName = "Original",
                description = """
                    Cholula Original Hot Sauce is created from a generations old recipe that features carefully-selected
                    arbol and piquin peppers and a blend of signature spices..
                    """.trimIndentsAndRemoveNewLines(),
                url = "https://www.cholula.com/original.html",
                heat = 3_600
            ),
            HotSauce(
                brandName = "Mad Dog",
                sauceName = "357",
                description = """
|                   Finally, a super hot sauce that tastes like real chile peppers. This sauce is blended
|                   with ingredients that create a sauce fit to take your breath away. .
|                   """.trimIndentsAndRemoveNewLines(),
                url = "https://www.saucemania.com.au/mad-dog-357-hot-sauce-148ml/",
                heat = 357_000
            ),
            HotSauce(
                brandName = "Hot Ones",
                sauceName = "Fiery Chipotle",
                description = """
                    This hot sauce was created with one goal in mind: to get celebrity interviewees on Hot Ones to say 
                    "damn that's tasty, and DAMN that's HOT!" .
                    """.trimIndentsAndRemoveNewLines(),
                url = "https://chillychiles.com/products/hot-ones-fiery-chipotle-hot-sauce",
                heat = 15_600
            ),
            HotSauce(
                brandName = "Hot Ones",
                sauceName = "The Last Dab",
                description = """
                    More than simple mouth burn, Pepper X singes your soul. Starting with a pleasant burn in the mouth,
                    the heat passes quickly, lulling you into a false confidence.
                    """.trimIndentsAndRemoveNewLines(),
                url = "https://www.saucemania.com.au/hot-ones-the-last-dab-hot-sauce-148ml/",
                heat = 1_000_000
            ),
            HotSauce(
                brandName = "Torchbearer",
                sauceName = "Zombie Apocalypse",
                description = """
                    The Zombie Apocalypse Hot Sauce lives up to its name, combining Ghost Peppers and Habaneros with a
                    mix of spices, vegetables, and vinegar to create a slow burning blow torch.
                    """.trimIndentsAndRemoveNewLines(),
                url = "https://heatonist.com/products/zombie-apocalypse",
                heat = 100_000
            ),
            HotSauce(
                brandName = "Heartbeat",
                sauceName = "Pineapple Habanero",
                description = """
                    Pineapple Habanero is Heartbeat Hot Sauce’s most recent offering and their spiciest to date! They’ve
                    yet again collaborated with an Ontario craft brewery, this time from their home town of Thunder Bay.
                    """.trimIndentsAndRemoveNewLines(),
                url = "https://www.saucemania.com.au/heartbeat-pineapple-habanero-hot-sauce-177ml/",
                heat = 12_200
            ),
            HotSauce(
                brandName = "Karma Sauce",
                sauceName = "Burn After Eating",
                description = """
                    Karma Sauce Burn After Eating Hot Sauce is imbued with a unique flavour thanks to green mango,
                    ajwain and hing powder. Forged with a top-secret blend of super hots 
                    """.trimIndentsAndRemoveNewLines(),
                url = "https://www.saucemania.com.au/karma-sauce-burn-after-eating-hot-sauce-148ml/",
                heat = 669_000
            )
        ))
    }
}

