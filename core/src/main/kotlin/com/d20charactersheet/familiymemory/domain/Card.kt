package com.d20charactersheet.familiymemory.domain

data class Card(val imageId: Int) {

    var face: Face = Face.Back

    fun flip() {
        face = when (face) {
            Face.Front -> Face.Back
            Face.Back -> Face.Front
        }

    }

}
