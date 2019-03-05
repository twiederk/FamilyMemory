package com.d20charactersheet.familiymemory.domain

class Card(val imageId: Int) {

    var face: Face = Face.Back
    var x: Float = 0.0f
    var y: Float = 0.0f
    var state: CardState = CardState.OnBoard

    fun flip() {
        face = when (face) {
            Face.Front -> Face.Back
            Face.Back -> Face.Front
        }
    }

}

sealed class Face {
    object Front : Face()
    object Back : Face()

    override fun toString(): String {
        return javaClass.simpleName
    }
}

sealed class CardState {
    object OnBoard : CardState()
    object OffBoard : CardState()
}
