package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test

internal class GameRendererCreateTest {

    private val imageFactory: ImageFactory = mock()

    private val underTest = GameRenderer(imageFactory)

    @Test
    fun `create stage`() {

        // Arrange
        Gdx.graphics = mock()
        Gdx.input = mock()
        val stage: Stage = mock()
        whenever(imageFactory.createDrawable(any())).thenReturn(mock())

        // Act
        underTest.create(stage)

        // Assert
        verify(stage, times(8)).addActor(any())
        verify(Gdx.graphics).isContinuousRendering = false
        verify(Gdx.graphics).requestRendering()
    }
}
