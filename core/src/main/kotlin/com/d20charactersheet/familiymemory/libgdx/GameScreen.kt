package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame


class GameScreen(internal var familyMemoryGame: FamilyMemoryGame = FamilyMemoryGame()) : Screen {

    internal lateinit var menuRenderer: MenuRenderer
    internal lateinit var imageCards: ImageCards

    internal lateinit var libGDXFactory: LibGDXFactory
    internal lateinit var stage: Stage



    constructor(familyMemoryGame: FamilyMemoryGame = FamilyMemoryGame(), libGDXFactory: LibGDXFactory) : this(familyMemoryGame) {
        this.libGDXFactory = libGDXFactory
        this.menuRenderer = MenuRenderer(libGDXFactory)
        this.imageCards = ImageCards(familyMemoryGame, libGDXFactory)

        menuRenderer.buildGUI(this)
        buildStage()

        Gdx.graphics.isContinuousRendering = false
        Gdx.graphics.requestRendering()
    }

    private fun buildStage() {
        stage = libGDXFactory.createStage()
        imageCards.addActors(stage)
        menuRenderer.addActors(stage)
        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        updateGame()
        clearScreen()
        renderScene()
    }

    private fun updateGame() {
        familyMemoryGame.match().ifPresent { imageCards.updateCards() }
        menuRenderer.updateNumberOfTries(familyMemoryGame.numberOfTries)
        if (familyMemoryGame.memoryCompleted()) {
            menuRenderer.updateMemoryCompleted()
        }
    }

    private fun clearScreen() {
        Gdx.gl.glClearColor(0.33f, 0.33f, 0.81f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    private fun renderScene() {
        stage.act()
        stage.draw()
    }

    override fun hide() {
    }

    override fun show() {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
        stage.dispose()
    }

    fun restart(familyMemoryGame: FamilyMemoryGame) {
        this.familyMemoryGame = familyMemoryGame
        imageCards.restart(familyMemoryGame, stage)
        menuRenderer.restart()
    }

}