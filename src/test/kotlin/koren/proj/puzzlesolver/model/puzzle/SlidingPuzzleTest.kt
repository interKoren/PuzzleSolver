package koren.proj.puzzlesolver.model.puzzle

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

private const val ILLEGAL_MAZE_SIZE_MESSAGE = "Illegal maze size - empty"
private const val X_ILLEGAL_OCCURRENCES_MESSAGE = "X occurring illegal amount of times"

internal class SlidingPuzzleTest {

    private val illegalRowStateSize: Array<Array<String>> = arrayOf()
    private val illegalColStateSize: Array<Array<String>> = arrayOf(arrayOf("0", "0"), arrayOf(), arrayOf("1", "1"))
    private val missingXState: Array<Array<String>> = arrayOf(arrayOf("0", "0"), arrayOf("2"), arrayOf("1", "1"))
    private val multipleXState: Array<Array<String>> = arrayOf(arrayOf("0", "X"), arrayOf("2"), arrayOf("X", "1"))
    private val legalState: Array<Array<String>> = arrayOf(arrayOf("2", "3"), arrayOf("1", "X"))
    private val firstFutureStep: Array<Array<String>> = arrayOf(arrayOf("2", "X"), arrayOf("1", "3"))
    private val secFutureStep: Array<Array<String>> = arrayOf(arrayOf("2", "3"), arrayOf("X", "1"))

    @Test
    fun generateSteps() {
        val slidingPuzzle = SlidingPuzzle(legalState)
        val stepsLst = slidingPuzzle.generateSteps()
        val expectedSteps = arrayOf(SlidingPuzzle(firstFutureStep), SlidingPuzzle(secFutureStep))
        var i = 0

        for (resulted in stepsLst) {
            assertTrue(resulted == expectedSteps[i])
            i += 1
        }
    }

    @Test
    fun checkValidMazeSize() {

        var exception: Exception = assertThrows(IllegalArgumentException::class.java)
        { SlidingPuzzle(illegalRowStateSize) }
        var expectedMessage = ILLEGAL_MAZE_SIZE_MESSAGE
        var actualMessage = exception.message
        assertTrue(actualMessage.equals(expectedMessage))

        exception = assertThrows(IllegalArgumentException::class.java)
        { SlidingPuzzle(illegalColStateSize) }
        expectedMessage = ILLEGAL_MAZE_SIZE_MESSAGE
        actualMessage = exception.message
        assertTrue(actualMessage.equals(expectedMessage))
    }

    @Test
    fun checkValidXOccurrences() {

        var exception = assertThrows(IllegalArgumentException::class.java)
        { SlidingPuzzle(missingXState) }
        var expectedMessage = X_ILLEGAL_OCCURRENCES_MESSAGE
        var actualMessage = exception.message
        assertTrue(actualMessage.equals(expectedMessage))

        exception = assertThrows(IllegalArgumentException::class.java)
        { SlidingPuzzle(multipleXState) }
        expectedMessage = X_ILLEGAL_OCCURRENCES_MESSAGE
        actualMessage = exception.message
        assertTrue(actualMessage.equals(expectedMessage))
    }

    @Test
    fun checkValidLegal() {
        assertDoesNotThrow { SlidingPuzzle(legalState) }
    }
}