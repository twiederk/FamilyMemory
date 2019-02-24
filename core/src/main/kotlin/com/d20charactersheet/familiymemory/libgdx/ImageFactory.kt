package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image

class ImageFactory {

    fun createImage(textureName: String): Image {
        return Image(Texture(textureName))
    }

}
