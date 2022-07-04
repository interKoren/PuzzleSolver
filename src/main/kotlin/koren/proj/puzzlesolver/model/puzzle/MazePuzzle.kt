package koren.proj.puzzlesolver.model.puzzle

import koren.proj.puzzlesolver.model.puzzle.puzzleException.IllegalMazeValuesException
import koren.proj.puzzlesolver.model.puzzle.puzzleException.XOccurrencesException
import koren.proj.puzzlesolver.model.puzzle.puzzleException.PuzzleSizeException
import java.util.*

private const val X_OCCURRENCES: Int = 1
private const val X_SQUARE: String = "X"
private const val EMPTY_SQUARE: String = "0"
private const val WALL: String = "1"

open class MazePuzzle (private val state: Array<Array<String>>) : AbstractPuzzle(state) {

    init {
        checkValid()
    }

    override fun generateSteps(): Collection<AbstractPuzzle> {
        val xPosition: Position = findXPosition()
        val neighbours: List<Position> = findNeighboursPositions(xPosition)

        return createFutureStepsFromPositions(neighbours, xPosition)
    }

    final override fun checkValid() {
        val validatePuzzleSize: (Array<String>) -> Boolean = { it.isEmpty()}
        if (state.isEmpty() || state.any(validatePuzzleSize)){
            throw PuzzleSizeException()
        }

        val validateXOccurrences: (String) -> Boolean = { it == X_SQUARE}
        if (state.fold(0) { sumX, row: Array<String> -> sumX + row.count(validateXOccurrences) } != X_OCCURRENCES){
            throw XOccurrencesException()
        }

        val validateMazeValues: (String) -> Boolean = { it == X_SQUARE || it == EMPTY_SQUARE || it == WALL}
        if (state.all{ row -> row.all(validateMazeValues) }.not()){
            throw IllegalMazeValuesException()
        }
    }

    private fun findXPosition(): Position {
        var rowIndex = 0
        var colIndex = 0
        for (currentRowIndex in state.indices) {
            if (state[currentRowIndex].contains(X_SQUARE)) {
                rowIndex = currentRowIndex
                colIndex = state[rowIndex].indexOf(X_SQUARE)
            }
        }

        return Position(rowIndex, colIndex)
    }

    private fun findNeighboursPositions(xPosition: Position): List<Position> {
        val upNeighbour = Position(xPosition.rowIndex + 1, xPosition.colIndex)
        val downNeighbour = Position(xPosition.rowIndex - 1, xPosition.colIndex)
        val rightNeighbour = Position(xPosition.rowIndex, xPosition.colIndex + 1)
        val leftNeighbour = Position(xPosition.rowIndex, xPosition.colIndex - 1)
        val neighborsIndexList = listOf(rightNeighbour, leftNeighbour, upNeighbour, downNeighbour)

        val legalIndex: (Position) -> Boolean = checkPossibleStepPosition()

        return neighborsIndexList.filter(legalIndex)
    }

    private fun checkPossibleStepPosition(): (Position) -> Boolean = {
        val checkPosition = it
        val rowIndex = checkPosition.rowIndex
        val colIndex = checkPosition.colIndex
        val isIndexNotNegative = rowIndex >= 0 && colIndex >= 0
        val isIndexSmallerThanMax = rowIndex < state.size && colIndex < state[rowIndex].size

        isIndexNotNegative && isIndexSmallerThanMax && isEmptyPosition(checkPosition)
    }

    private fun createFutureStepsFromPositions(
        neighboursPosition: Collection<Position>,
        xPosition: Position
    ): Collection<AbstractPuzzle> {

        val possibleSteps = LinkedList<AbstractPuzzle>()
        for (neighborPosition in neighboursPosition) {
            val stateCopy: Array<Array<String>> = state.map { it.clone() }.toTypedArray()

            stateCopy[xPosition.rowIndex][xPosition.colIndex] =
                stateCopy[neighborPosition.rowIndex][neighborPosition.colIndex]
            stateCopy[neighborPosition.rowIndex][neighborPosition.colIndex] = X_SQUARE
            possibleSteps += MazePuzzle(stateCopy)
        }

        return possibleSteps
    }

    private fun isEmptyPosition(checkPosition: Position): Boolean {
        return state[checkPosition.rowIndex][checkPosition.colIndex] == EMPTY_SQUARE
    }

}