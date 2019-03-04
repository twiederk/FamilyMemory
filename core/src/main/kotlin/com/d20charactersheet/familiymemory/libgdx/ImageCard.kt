package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.d20charactersheet.familiymemory.domain.Card
import com.d20charactersheet.familiymemory.domain.Face

data class ImageCard(val card: Card, val front: Drawable, val back: Drawable) {

    val image: Image = Image(getFaceDrawable())

    fun addListener(cardClickListener: CardClickListener) {
        image.addListener(cardClickListener)
    }

    fun flip() {
        card.flip()
        image.drawable = getFaceDrawable()
    }

    private fun getFaceDrawable(): Drawable {
        return when (card.face) {
            Face.Front -> front
            Face.Back -> back
        }
    }
}
