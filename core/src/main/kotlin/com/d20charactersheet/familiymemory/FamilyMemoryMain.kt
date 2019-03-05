package com.d20charactersheet.familiymemory

import com.badlogic.gdx.ApplicationAdapter
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.d20charactersheet.familiymemory.libgdx.GameRenderer
import com.d20charactersheet.familiymemory.libgdx.ImageFactory

class FamilyMemoryMain(private val gameRenderer: GameRenderer = GameRenderer(FamilyMemoryGame(), ImageFactory())) : ApplicationAdapter() {

    override fun create() {
        gameRenderer.create()
    }

    override fun render() {
        gameRenderer.render()
    }

    override fun dispose() {
        gameRenderer.dispose()
    }
}
