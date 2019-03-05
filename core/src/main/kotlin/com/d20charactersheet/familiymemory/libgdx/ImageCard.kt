package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.d20charactersheet.familiymemory.domain.Card
import com.d20charactersheet.familiymemory.domain.CardState
import com.d20charactersheet.familiymemory.domain.Face
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame

data class ImageCard(val familyMemoryGame: FamilyMemoryGame, val card: Card, val front: Drawable, val back: Drawable) {

    val image: Image = Image(getFaceDrawable())

    init {
        image.x = card.x
        image.y = card.y
    }

    fun flip() {
        familyMemoryGame.flip(card)
        image.drawable = front
    }

    fun update() {
        if (card.state == CardState.OffBoard && image.isVisible && image.actions.isEmpty) {
            image.addAction(Actions.delay(2.0f, Actions.hide()))
        }
        if (card.face == Face.Back && image.drawable == front && card.state == CardState.OnBoard && image.actions.isEmpty) {
            image.addAction(Actions.delay(2.0f, Actions.run { image.drawable = back }))
        }
    }

    private fun getFaceDrawable(): Drawable {
        return when (card.face) {
            Face.Front -> front
            Face.Back -> back
        }
    }
}
