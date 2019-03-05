package com.d20charactersheet.familiymemory.domain

import java.util.*


class CardBoard(numberOfImages: Int, internal val cardBoardConfig: CardBoardConfig = CardBoardConfig(), random: Random = Random()) {

    internal var cards = mutableListOf<Card>()

    init {
        createCards(numberOfImages)
        cards.shuffle(random)
        arrangeImageCards()
    }

    private fun createCards(numberOfImages: Int) {
        cards = ArrayList(numberOfImages * 2)
        for (imageId in 1..numberOfImages) {
            cards.add(Card(imageId))
            cards.add(Card(imageId))
        }
    }

    private fun arrangeImageCards() {
        with(cardBoardConfig) {
            val numberOfColumns = (((boardWith - (margin * 2)) / (cardSize + spacing))).toInt()
            for (i in 0 until cards.size) {
                val col = i % numberOfColumns
                val row = i / numberOfColumns

                cards[i].x = margin + ((cardSize + spacing) * col)
                cards[i].y = margin + ((cardSize + spacing) * row)
            }
        }
    }

}
