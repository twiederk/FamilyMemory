package com.d20charactersheet.familiymemory.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.d20charactersheet.familiymemory.FamilyMemoryMain
import com.d20charactersheet.familiymemory.libgdx.LibGDXFactory

object DesktopLauncher {

    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.title = "Family Memory"
        config.width = 1440
        config.height = 2560
        LwjglApplication(FamilyMemoryMain(LibGDXFactory()), config)
    }
}
