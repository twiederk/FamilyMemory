package com.d20charactersheet.familiymemory

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.d20charactersheet.familiymemory.domain.CardBoardConfig
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.d20charactersheet.familiymemory.libgdx.GameScreen
import com.d20charactersheet.familiymemory.libgdx.LibGDXFactory

class FamilyMemoryMain(private val libGDXFactory: LibGDXFactory = LibGDXFactory()) : Game() {

    internal var gameScreen: GameScreen? = null


    override fun create() {
        val cardBoardConfig = CardBoardConfig(boardWith = Gdx.graphics.width, boardHeight = Gdx.graphics.height)
        gameScreen = gameScreen ?: GameScreen(FamilyMemoryGame(8, cardBoardConfig), libGDXFactory)
        setScreen(gameScreen)
    }

    override fun dispose() {
        gameScreen?.dispose()
    }

}
