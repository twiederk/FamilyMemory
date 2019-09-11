package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.scenes.scene2d.Stage
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame

class ImageCards(familyMemoryGame: FamilyMemoryGame, private val libGDXFactory: LibGDXFactory) {

    internal var imageCards = mutableListOf<ImageCard>()

    init {
        buildImageCards(familyMemoryGame)
    }

    private fun buildImageCards(familyMemoryGame: FamilyMemoryGame) {
        val backDrawable = libGDXFactory.createDrawable("0.jpg")
        imageCards = familyMemoryGame.getCards()
                .map { ImageCard(familyMemoryGame, it, libGDXFactory.createDrawable("${it.imageId}.jpg"), backDrawable) }
                .toMutableList()
        imageCards.forEach { it.image.addListener(CardClickListener(this, it)) }
        imageCards.forEach { it.image.setScale(2f) }
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