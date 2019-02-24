package com.d20charactersheet.familiymemory.libgdx

import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import java.util.*


class CardBoard(familyMemoryGame: FamilyMemoryGame, private val imageFactory: ImageFactory, private val cardBoardConfig: CardBoardConfig = CardBoardConfig(), random: Random = Random()) {


    internal var imageCards = mutableListOf<ImageCard>()

    init {
        createImageCards(familyMemoryGame)
        imageCards.shuffle(random)
        arrangeImageCards()
    }

    private fun createImageCards(familyMemoryGame: FamilyMemoryGame) {
        imageCards = familyMemoryGame.cards
                .map { ImageCard(it, imageFactory.createImage("${it.imageId}_${cardBoardConfig.cardSize}.jpg")) }
                .toMutableList()
    }

    private fun arrangeImageCards() {
        with(cardBoardConfig) {
            val numberOfColumns = (((boardWith - (margin * 2)) / (cardSize + spacing))).toInt()
            for (i in 0 until imageCards.size) {
                val col = i % numberOfColumns
                val row = i / numberOfColumns

                val image = imageCards[i].image
                image.x = margin + ((cardSize + spacing) * col)
                image.y = margin + ((cardSize + spacing) * row)


            }
        }


    }

}
