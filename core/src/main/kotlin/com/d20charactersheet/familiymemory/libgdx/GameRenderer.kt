package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame

class GameRenderer(private val familyMemoryGame: FamilyMemoryGame = FamilyMemoryGame(), private val imageFactory: ImageFactory) {

    internal lateinit var stage: Stage
    internal var imageCards = mutableListOf<ImageCard>()


    fun create(stage: Stage = Stage()) {
        buildImageCards()
        buildStage(stage)

        Gdx.graphics.isContinuousRendering = false
        Gdx.graphics.requestRendering()
    }


    private fun buildImageCards() {
        val cardSize = familyMemoryGame.getCardSize()
        val backDrawable = imageFactory.createDrawable("0_$cardSize.jpg")
        imageCards = familyMemoryGame.getCards()
                .map { ImageCard(familyMemoryGame, it, imageFactory.createDrawable("${it.imageId}_$cardSize.jpg"), backDrawable) }
                .toMutableList()
        imageCards.forEach { it.image.addListener(CardClickListener(this, it)) }
    }

    private fun buildStage(stage: Stage) {
        this.stage = stage
        imageCards.forEach { stage.addActor(it.image) }
        Gdx.input.inputProcessor = stage
    }


    fun render() {
        updateGame()
        clearScreen()
        renderScene()
    }

    private fun updateGame() {
        familyMemoryGame.match().ifPresent { imageCards.forEach { it.update() } }
    }

    private fun clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    private fun renderScene() {
        stage.act()
        stage.draw()
    }

    fun dispose() {
        stage.dispose()
    }

    fun flip(imageCard: ImageCard) {
        if (imageCards.flatMap { it.image.actions }.isEmpty()) {
            imageCard.flip()
        }
    }

}