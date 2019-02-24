package com.d20charactersheet.familiymemory.domain

class FamilyMemoryGame(numberOfImages: Int = 1) {

    val cards: List<Card>

    init {
        cards = ArrayList(numberOfImages * 2)
        for (i in 1..numberOfImages) {
            cards += Card(i)
            cards += Card(i)
        }

    }

}
