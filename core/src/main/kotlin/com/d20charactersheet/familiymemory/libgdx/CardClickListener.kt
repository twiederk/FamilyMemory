package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class CardClickListener(private val imageCard: ImageCard) : ClickListener() {

    override fun clicked(event: InputEvent?, x: Float, y: Float) {
        imageCard.flip()
    }
}
