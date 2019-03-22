package com.d20charactersheet.familiymemory

import com.badlogic.gdx.Gdx
import com.d20charactersheet.familiymemory.libgdx.GameScreen
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

internal class FamilyMemoryMainTest {

    private lateinit var familyMemoryMain: FamilyMemoryMain
    private lateinit var gameScreen: GameScreen


    @BeforeEach
    fun setup() {
        Gdx.graphics = mock()
        familyMemoryMain = FamilyMemoryMain(mock())
        gameScreen = mock()
        familyMemoryMain.gameScreen = gameScreen
    }


    @Test
    fun create() {

        // Act
        familyMemoryMain.create()

        // Assert
        verify(gameScreen).show()
        verify(gameScreen).resize(0, 0)
    }


    @Test
    fun render() {
        // Arrange
        familyMemoryMain.create()
        Mockito.reset(gameScreen)

        // Act
        familyMemoryMain.render()

        // Assert
        verify(gameScreen, only()).render(0f)
    }

    @Test
    fun dispose() {

        // Act
        familyMemoryMain.dispose()

        // Assert
        verify(gameScreen, only()).dispose()
    }

}