package com.d20charactersheet.familiymemory.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.d20charactersheet.familiymemory.FamilyMemoryMain

object DesktopLauncher {

    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.title = "Family Memory"
        config.width = 480
        config.height = 800
        LwjglApplication(FamilyMemoryMain(), config)
    }
}
