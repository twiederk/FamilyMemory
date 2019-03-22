package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

class LibGDXFactory {

    fun createDrawable(textureName: String): Drawable = TextureRegionDrawable(Texture(textureName))

    fun createSkin(skinName: String): Skin = Skin(Gdx.files.internal(skinName))

    fun createStage(): Stage = Stage()

    fun createLabel(text: String, skin: Skin): Label = Label(text, skin)

    fun createTextButton(text: String, skin: Skin, styleName: String) = TextButton(text, skin, styleName)


}
