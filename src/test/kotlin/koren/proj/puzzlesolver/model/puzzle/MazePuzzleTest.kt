package koren.proj.puzzlesolver.model.puzzle

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

private const val ILLEGAL_MAZE_SIZE_MESSAGE = "Illegal maze size - empty"
private const val X_ILLEGAL_OCCURRENCES_MESSAGE = "X occurring illegal amount of times"
private const val ILLEGAL_VALUES_MESSAGE = "There is illegal values in maze - Allow only: 1 | 0 | X"

internal class MazePuzzleTest {

    private val illegalRowStateSize: Array<Array<String>> = arrayOf()
    private val illegalColStateSize: Array<Array<String>> = arrayOf(arrayOf("0", "X"), arrayOf(), arrayOf("1", "1"))
    private val missingXState: Array<Array<String>> = arrayOf(arrayOf("0", "1"), arrayOf("0"), arrayOf("1", "1"))
    private val multipleXState: Array<Array<String>> = arrayOf(arrayOf("0", "X"), arrayOf("0"), arrayOf("X", "1"))
    private val illegalValuesState: Array<Array<String>> = arrayOf(arrayOf("0", "X"), arrayOf("5"), arrayOf("0", "1"))
    private val legalState: Array<Array<String>> = arrayOf(arrayOf("0", "0"), arrayOf("1", "X"))
    private val futureStep: Array<Array<String>> = arrayOf(arrayOf("0", "X"), arrayOf("1", "0"))
    private val zeroFutureState: Array<Array<String>> = arrayOf(arrayOf("0", "1"), arrayOf("1", "X"))

    @Test
    fun generateSteps() {
        val mazePuzzle = MazePuzzle(legalState)
        val stepsLst = mazePuzzle.generateSteps()
        val expectedSteps = arrayOf(MazePuzzle(futureStep))
        var i = 0

        for (resulted in stepsLst) {
            assertTrue(resulted == expectedSteps[i])
            i += 1
        }
    }

    @Test
    fun generateZeroSteps() {
        val mazePuzzle = MazePuzzle(zeroFutureState)
        val stepsLst = mazePuzzle.generateSteps()

        assertTrue(stepsLst.isEmpty())
    }

    @Test
    fun checkValidMazeSize() {

        var exception: Exception = assertThrows(IllegalArgumentException::class.java)
        { MazePuzzle(illegalRowStateSize) }
        var expectedMessage = ILLEGAL_MAZE_SIZE_MESSAGE
        var actualMessage = exception.message
        assertTrue(actualMessage.equals(expectedMessage))

        exception = assertThrows(IllegalArgumentException::class.java)
        { MazePuzzle(illegalColStateSize) }
        expectedMessage = ILLEGAL_MAZE_SIZE_MESSAGE
        actualMessage = exception.message
        assertTrue(actualMessage.equals(expectedMessage))
    }

    @Test
    fun checkValidXOccurrences() {

        var exception = assertThrows(IllegalArgumentException::class.java)
        { MazePuzzle(missingXState) }
        var expectedMessage = X_ILLEGAL_OCCURRENCES_MESSAGE
        var actualMessage = exception.message
        assertTrue(actualMessage.equals(expectedMessage))

        exception = assertThrows(IllegalArgumentException::class.java)
        { MazePuzzle(multipleXState) }
        expectedMessage = X_ILLEGAL_OCCURRENCES_MESSAGE
        actualMessage = exception.message
        assertTrue(actualMessage.equals(expectedMessage))
    }

    @Test
    fun checkValidValues() {

        val exception = assertThrows(IllegalArgumentException::class.java)
        { MazePuzzle(illegalValuesState) }
        val expectedMessage = ILLEGAL_VALUES_MESSAGE
        val actualMessage = exception.message
        assertTrue(actualMessage.equals(expectedMessage))
    }

    @Test
    fun checkValidLegal() {
        org.junit.jupiter.api.assertDoesNotThrow { MazePuzzle(legalState) }
    }
}