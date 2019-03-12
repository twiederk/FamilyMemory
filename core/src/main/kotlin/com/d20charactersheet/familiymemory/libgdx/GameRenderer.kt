package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame


class GameRenderer(private val familyMemoryGame: FamilyMemoryGame = FamilyMemoryGame(), private val imageFactory: ImageFactory) {

    internal lateinit var stage: Stage
    internal var imageCards = mutableListOf<ImageCard>()
    internal lateinit var numberOfTries: Label
    internal lateinit var memoryCompleted: Label

    fun create(stage: Stage = Stage()) {
        buildImageCards()
        buildGUI()
        buildStage(stage)

        Gdx.graphics.isContinuousRendering = false
        Gdx.graphics.requestRendering()
    }


    internal fun buildImageCards() {
        val cardSize = familyMemoryGame.getCardSize()
        val backDrawable = imageFactory.createDrawable("0_$cardSize.jpg")
        imageCards = familyMemoryGame.getCards()
                .map { ImageCard(familyMemoryGame, it, imageFactory.createDrawable("${it.imageId}_$cardSize.jpg"), backDrawable) }
                .toMutableList()
        imageCards.forEach { it.image.addListener(CardClickListener(this, it)) }
    }


    private fun buildGUI() {
        val labelStyle = createLabelStyle()
        createNumberOfTries(labelStyle)
        createMemoryCompleted(labelStyle)
    }

    private fun createLabelStyle(): Label.LabelStyle {
        val labelStyle = Label.LabelStyle()
        val bitmapFont = imageFactory.createBitmapFont("Arial_Unicode_MS_Gradient.fnt")
        labelStyle.font = bitmapFont
        return labelStyle
    }

    private fun createNumberOfTries(labelStyle: Label.LabelStyle) {
        numberOfTries = createLabel(labelStyle, 1)
    }


    private fun createMemoryCompleted(labelStyle: Label.LabelStyle) {
        memoryCompleted = createLabel(labelStyle, 2)
        memoryCompleted.isVisible = false
    }

    private fun createLabel(labelStyle: Label.LabelStyle, row: Int): Label {
        val rowHeight = Gdx.graphics.width.toFloat() / 12
        val label = Label("", labelStyle)
        label.setSize(Gdx.graphics.width.toFloat(), rowHeight)
        label.setPosition(0.0f, Gdx.graphics.height.toFloat() - rowHeight * row)
        label.setAlignment(Align.center)
        return label
    }

    private fun buildStage(stage: Stage) {
        this.stage = stage
        imageCards.forEach { stage.addActor(it.image) }
        stage.addActor(numberOfTries)
        stage.addActor(memoryCompleted)
        Gdx.input.inputProcessor = stage
    }

    fun render() {
        updateGame()
        clearScreen()
        renderScene()
    }

    private fun updateGame() {
        familyMemoryGame.match().ifPresent { imageCards.forEach { it.update() } }
        numberOfTries.setText("Number of tries: ${familyMemoryGame.numberOfTries}")
        if (familyMemoryGame.memoryCompleted()) {
            memoryCompleted.setText("Memory Completed!!!")
            memoryCompleted.isVisible = true
        }
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