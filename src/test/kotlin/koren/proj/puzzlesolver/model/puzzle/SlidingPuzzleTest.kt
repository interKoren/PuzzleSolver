package koren.proj.puzzlesolver.model.puzzle

import koren.proj.puzzlesolver.model.customeExceptions.puzzleExceptions.PuzzleSizeException
import koren.proj.puzzlesolver.model.customeExceptions.puzzleExceptions.XOccurrencesException
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


private val ILLEGAL_ROW_STATE_SIZE: Array<Array<String>> = arrayOf()
private val ILLEGAL_COL_STATE_SIZE: Array<Array<String>> = arrayOf(arrayOf("0", "0"), arrayOf(), arrayOf("1", "1"))
private val MISSING_X_STATE: Array<Array<String>> = arrayOf(arrayOf("0", "0"), arrayOf("2"), arrayOf("1", "1"))
private val MULTIPLE_X_STATE: Array<Array<String>> = arrayOf(arrayOf("0", "X"), arrayOf("2"), arrayOf("X", "1"))
private val LEGAL_STATE: Array<Array<String>> = arrayOf(
    arrayOf("23", "2", "1"), arrayOf("54", "X", "4"), arrayOf("2", "6", "5"))
private val FIRST_FUTURE_STEP: Array<Array<String>> = arrayOf(
    arrayOf("23", "2", "1"), arrayOf("54", "4", "X"), arrayOf("2", "6", "5"))
private val SECOND_FUTURE_STEP: Array<Array<String>> = arrayOf(
    arrayOf("23", "2", "1"), arrayOf("X", "54", "4"), arrayOf("2", "6", "5"))
private val THIRD_FUTURE_STEP: Array<Array<String>> = arrayOf(
    arrayOf("23", "X", "1"), arrayOf("54", "2", "4"), arrayOf("2", "6", "5"))
private val FOURTH_FUTURE_STEP: Array<Array<String>> = arrayOf(
    arrayOf("23", "2", "1"), arrayOf("54", "6", "4"), arrayOf("2", "X", "5"))

internal class SlidingPuzzleTest {

    @Test
    fun shouldGeneratePossibleSteps() {
        val slidingPuzzle = SlidingPuzzle(LEGAL_STATE)
        val resultedSteps = slidingPuzzle.generateSteps()
        val expectedSteps = listOf(SlidingPuzzle(FIRST_FUTURE_STEP), SlidingPuzzle(SECOND_FUTURE_STEP),
            SlidingPuzzle(THIRD_FUTURE_STEP), SlidingPuzzle(FOURTH_FUTURE_STEP))

        assertTrue(resultedSteps.size == expectedSteps.size)
        assertTrue(resultedSteps.containsAll(expectedSteps))
    }

    @Test
    fun shouldThrowException_WhenPuzzleIsEmpty() {
        assertThrows(PuzzleSizeException::class.java) { SlidingPuzzle(ILLEGAL_ROW_STATE_SIZE) }
    }

    @Test
    fun shouldThrowException_WhenPuzzleRowIsEmpty() {
        assertThrows(PuzzleSizeException::class.java) { SlidingPuzzle(ILLEGAL_COL_STATE_SIZE) }
    }

    @Test
    fun shouldThrowException_WhenXMissing() {
        assertThrows(XOccurrencesException::class.java) { SlidingPuzzle(MISSING_X_STATE) }
    }

    @Test
    fun shouldThrowException_WhenXHasMultipleOccurrences() {
        assertThrows(XOccurrencesException::class.java) { SlidingPuzzle(MULTIPLE_X_STATE) }
    }
}