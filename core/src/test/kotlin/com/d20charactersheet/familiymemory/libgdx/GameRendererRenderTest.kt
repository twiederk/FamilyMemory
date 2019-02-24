package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Test

internal class GameRendererRenderTest {

    private val underTest = GameRenderer(mock())

    @Test
    fun `draw stage`() {

        // Arrange
        underTest.stage = mock()
        Gdx.gl = mock()

        // Act
        underTest.render()

        // Assert
        inOrder(underTest.stage, Gdx.gl) {
            verify(Gdx.gl).glClearColor(0f, 0f, 0f, 1f)
            verify(Gdx.gl).glClear(GL20.GL_COLOR_BUFFER_BIT)
            verify(underTest.stage).act()
            verify(underTest.stage).draw()
        }
    }

}