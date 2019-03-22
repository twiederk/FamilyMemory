package com.d20charactersheet.familiymemory

import com.badlogic.gdx.Game
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.d20charactersheet.familiymemory.libgdx.GameScreen
import com.d20charactersheet.familiymemory.libgdx.LibGDXFactory

class FamilyMemoryMain(private val libGDXFactory: LibGDXFactory = LibGDXFactory()) : Game() {

    internal var gameScreen: GameScreen? = null

    override fun create() {
        gameScreen = gameScreen ?: GameScreen(FamilyMemoryGame(4), libGDXFactory)
        setScreen(gameScreen)
    }

    override fun dispose() {
        gameScreen?.dispose()
    }

}
