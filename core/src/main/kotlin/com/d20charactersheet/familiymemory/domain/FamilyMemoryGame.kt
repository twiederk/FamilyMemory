package com.d20charactersheet.familiymemory.domain

import java.util.*

class FamilyMemoryGame(numberOfImages: Int = 1, cardBoardConfig: CardBoardConfig = CardBoardConfig()) {
    private val cardBoard = CardBoard(numberOfImages, cardBoardConfig)
    var numberOfTries = 0

    fun match(): Optional<Pair<Card, Card>> {
        val frontFaceCards = cardBoard.cards.filter { it.face == Face.Front && it.state == CardState.OnBoard }.toList()
        if (frontFaceCards.size == 2) {
            if (frontFaceCards[0].imageId == frontFaceCards[1].imageId) {
                frontFaceCards[0].state = CardState.OffBoard
                frontFaceCards[1].state = CardState.OffBoard
            } else {
                frontFaceCards[0].face = Face.Back
                frontFaceCards[1].face = Face.Back
            }
            numberOfTries++
            return Optional.of(Pair(frontFaceCards[0], frontFaceCards[1]))
        }
        return Optional.empty()
    }

    fun getCards() = cardBoard.cards

    fun flip(card: Card): Boolean {
        if (card.face == Face.Back &&
                cardBoard.cards.filter { it.face == Face.Front && it.state == CardState.OnBoard }.count() < 2) {
            card.flip()
            return true
        }
        return false
    }

    fun memoryCompleted(): Boolean {
        return cardBoard.cards.filter { it.state == CardState.OnBoard }.isEmpty()
    }


}
