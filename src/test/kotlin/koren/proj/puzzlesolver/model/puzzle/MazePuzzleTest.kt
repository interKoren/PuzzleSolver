package koren.proj.puzzlesolver.model.puzzle

import koren.proj.puzzlesolver.model.puzzle.puzzleException.IllegalMazeValues
import koren.proj.puzzlesolver.model.puzzle.puzzleException.PuzzleSizeException
import koren.proj.puzzlesolver.model.puzzle.puzzleException.XOccurrencesException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

private val ILLEGAL_ROW_STATE_SIZE: Array<Array<String>> = arrayOf()
private val ILLEGAL_COL_STATE_SIZE: Array<Array<String>> = arrayOf(arrayOf("0", "0"), arrayOf(), arrayOf("1", "1"))
private val MISSING_X_STATE: Array<Array<String>> = arrayOf(arrayOf("0", "0"), arrayOf("1"), arrayOf("1", "1"))
private val MULTIPLE_X_STATE: Array<Array<String>> = arrayOf(arrayOf("0", "X"), arrayOf("1"), arrayOf("X", "1"))
private val ILLEGAL_VALUES_STATE: Array<Array<String>> = arrayOf(
    arrayOf("0", "X"), arrayOf("0", "32", "3"), arrayOf("0", "1"))
private val LEGAL_STATE: Array<Array<String>> = arrayOf(
    arrayOf("0", "0", "1"), arrayOf("1", "X", "1"), arrayOf("1", "0", "1"))
private val FIRST_FUTURE_STEP: Array<Array<String>> = arrayOf(
    arrayOf("0", "X", "1"), arrayOf("1", "0", "1"), arrayOf("1", "0", "1"))
private val SECOND_FUTURE_STEP: Array<Array<String>> = arrayOf(
    arrayOf("0", "0", "1"), arrayOf("1", "0", "1"), arrayOf("1", "X", "1"))

internal class MazePuzzleTest {

    @Test
    fun generateSteps() {
        val mazePuzzle = MazePuzzle(LEGAL_STATE)
        val resultedSteps = mazePuzzle.generateSteps()
        val expectedSteps = listOf(MazePuzzle(FIRST_FUTURE_STEP), MazePuzzle(SECOND_FUTURE_STEP))

        assertTrue(resultedSteps.containsAll(expectedSteps) && resultedSteps.size == expectedSteps.size)
    }

    @Test
    fun should_ThrowException_When_PuzzleIsEmpty() {
        assertThrows(PuzzleSizeException::class.java) { MazePuzzle(ILLEGAL_ROW_STATE_SIZE) }
    }

    @Test
    fun should_ThrowException_When_PuzzleRowIsEmpty() {
        assertThrows(PuzzleSizeException::class.java) { MazePuzzle(ILLEGAL_COL_STATE_SIZE) }
    }

    @Test
    fun should_ThrowException_When_X_Missing() {
        assertThrows(XOccurrencesException::class.java) { MazePuzzle(MISSING_X_STATE) }
    }

    @Test
    fun should_ThrowException_When_X_HasMultipleOccurrences() {
        assertThrows(XOccurrencesException::class.java) { MazePuzzle(MULTIPLE_X_STATE) }
    }

    @Test
    fun should_ThrowException_When_MazeHasIllegalValues() {
        assertThrows(IllegalMazeValues::class.java) { MazePuzzle(ILLEGAL_VALUES_STATE) }
    }
}