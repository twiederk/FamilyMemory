package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class CardClickListener(private val cardRenderer: CardRenderer, private val imageCard: ImageCard) : ClickListener() {

    override fun clicked(event: InputEvent?, x: Float, y: Float) {
        cardRenderer.flip(imageCard)
    }
}
