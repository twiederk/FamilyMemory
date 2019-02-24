package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame

class GameRenderer(private val imageFactory: ImageFactory) {

    internal lateinit var stage: Stage

    fun create(stage: Stage = Stage()) {
        val familyMemoryGame = FamilyMemoryGame(4)
        val cardBoard = CardBoard(familyMemoryGame, imageFactory)
        buildStage(cardBoard, stage)

        Gdx.graphics.isContinuousRendering = false
        Gdx.graphics.requestRendering()
    }

    private fun buildStage(cardBoard: CardBoard, stage: Stage) {
        this.stage = stage
        cardBoard.imageCards.forEach { stage.addActor(it.image) }
    }


    fun render() {
        clearScreen()
        renderScene()
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

}