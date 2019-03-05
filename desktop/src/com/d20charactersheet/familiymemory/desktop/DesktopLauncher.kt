package com.d20charactersheet.familiymemory.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.d20charactersheet.familiymemory.FamilyMemoryMain
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.d20charactersheet.familiymemory.libgdx.GameRenderer
import com.d20charactersheet.familiymemory.libgdx.ImageFactory

object DesktopLauncher {

    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.title = "Family Memory"
        config.width = 480
        config.height = 800
        LwjglApplication(FamilyMemoryMain(GameRenderer(FamilyMemoryGame(4), ImageFactory())), config)
    }
}
