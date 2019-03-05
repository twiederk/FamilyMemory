package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GameRendererCreateTest {

    private val drawableMock: Drawable = mock()
    private val imageFactory: ImageFactory = mock()

    private val underTest = GameRenderer(FamilyMemoryGame(4), imageFactory)

    @BeforeEach
    fun setUp() {
        whenever(imageFactory.createDrawable(any())).thenReturn(drawableMock)
    }


    @Test
    fun `create stage`() {

        // Arrange
        Gdx.graphics = mock()
        Gdx.input = mock()
        val stage: Stage = mock()

        // Act
        underTest.create(stage)

        // Assert
        with(underTest.imageCards[0]) {
            assertThat(front).isEqualTo(drawableMock)
            assertThat(back).isEqualTo(drawableMock)
            assertThat(image.x).isEqualTo(32.0f)
            assertThat(image.y).isEqualTo(32.0f)
        }

        with(underTest.imageCards[1]) {
            assertThat(front).isEqualTo(drawableMock)
            assertThat(back).isEqualTo(drawableMock)
            assertThat(image.x).isEqualTo(184.0f)
            assertThat(image.y).isEqualTo(32.0f)
        }

        verify(stage, times(8)).addActor(any())
        verify(Gdx.graphics).isContinuousRendering = false
        verify(Gdx.graphics).requestRendering()
    }
}
