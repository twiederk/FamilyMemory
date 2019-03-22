package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.scenes.scene2d.Stage
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame

class CardRenderer(private val libGDXFactory: LibGDXFactory) {

    internal var imageCards = mutableListOf<ImageCard>()

    fun buildImageCards(familyMemoryGame: FamilyMemoryGame) {
        val cardSize = familyMemoryGame.getCardSize()
        val backDrawable = libGDXFactory.createDrawable("0_$cardSize.jpg")
        imageCards = familyMemoryGame.getCards()
                .map { ImageCard(familyMemoryGame, it, libGDXFactory.createDrawable("${it.imageId}_$cardSize.jpg"), backDrawable) }
                .toMutableList()
        imageCards.forEach { it.image.addListener(CardClickListener(this, it)) }
    }

    fun addActors(stage: Stage) {
        imageCards.forEach { stage.addActor(it.image) }
    }

    fun updateCards() {
        imageCards.forEach { it.update() }
    }

    fun flip(imageCard: ImageCard) {
        if (imageCards.flatMap { it.image.actions }.isEmpty()) {
            imageCard.flip()
        }
    }

    fun restart(familyMemoryGame: FamilyMemoryGame, stage: Stage) {
        imageCards.forEach { it.image.remove() }
        imageCards.clear()
        buildImageCards(familyMemoryGame)
        addActors(stage)
    }


}