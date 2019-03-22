package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame

class NewGameListener(private val gameScreen: GameScreen) : InputListener() {

    override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        gameScreen.restart(FamilyMemoryGame(4))
        return true
    }

}
