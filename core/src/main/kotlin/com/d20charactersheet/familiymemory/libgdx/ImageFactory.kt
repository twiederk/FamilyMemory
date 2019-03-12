package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

class ImageFactory {

    fun createDrawable(textureName: String): Drawable = TextureRegionDrawable(Texture(textureName))

    fun createBitmapFont(fontName: String): BitmapFont = BitmapFont(Gdx.files.internal(fontName))

}
