package com.d20charactersheet.familiymemory.libgdx

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test

internal class CardClickListenerTest {

    @Test
    fun clicked() {
        // Arrange
        val imageCard: ImageCard = mock()
        val cardClickListener = CardClickListener(imageCard)

        // Act
        cardClickListener.clicked(mock(), 0.0f, 0.0f)

        // Assert
        verify(imageCard, only()).flip()
    }
}