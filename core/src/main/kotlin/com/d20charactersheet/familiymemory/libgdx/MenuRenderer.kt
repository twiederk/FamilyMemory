package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.Align

class MenuRenderer(private val libGDXFactory: LibGDXFactory) {

    internal lateinit var numberOfTries: Label
    internal lateinit var memoryCompleted: Label
    internal lateinit var newGame: TextButton


    fun buildGUI(gameScreen: GameScreen) {
        val skin = libGDXFactory.createSkin("skin/glassy-ui.json")
        createNumberOfTries(skin)
        createMemoryCompleted(skin)
        createNewGame(skin, gameScreen)
    }

    private fun createNumberOfTries(skin: Skin) {
        this.numberOfTries = createLabel("Number of tries: 0", skin, 1)
    }


    private fun createMemoryCompleted(skin: Skin) {
        memoryCompleted = createLabel("Memory Completed!!!", skin, 2)
        memoryCompleted.isVisible = false
    }


    private fun createNewGame(skin: Skin, gameScreen: GameScreen) {
        newGame = libGDXFactory.createTextButton("New Game", skin, "small")
        val x = (Gdx.graphics.width.toFloat() - newGame.width) / 2
        val y = (Gdx.graphics.height.toFloat() - newGame.height) / 2
        newGame.setPosition(x, y)
        newGame.isVisible = false
        newGame.addListener(NewGameListener(gameScreen))
    }

    private fun createLabel(text: String, skin: Skin, row: Int): Label {
        val rowHeight = Gdx.graphics.width.toFloat() / 12
        val label = libGDXFactory.createLabel(text, skin)
        label.setSize(Gdx.graphics.width.toFloat(), rowHeight)
        label.setPosition(0.0f, Gdx.graphics.height.toFloat() - rowHeight * row)
        label.setAlignment(Align.center)
        return label
    }

    fun updateNumberOfTries(numberOfTries: Int) {
        this.numberOfTries.setText("Number of tries: $numberOfTries")
    }

    fun updateMemoryCompleted() {
        memoryCompleted.isVisible = true
        newGame.isVisible = true
    }

    fun addActors(stage: Stage) {
        stage.addActor(numberOfTries)
        stage.addActor(memoryCompleted)
        stage.addActor(newGame)
    }

    fun restart() {
        memoryCompleted.isVisible = false
        newGame.isVisible = false
    }


}