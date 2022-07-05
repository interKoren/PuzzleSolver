package koren.proj.puzzlesolver.model.puzzle

import koren.proj.puzzlesolver.model.customeExceptions.puzzleExceptions.PuzzleSizeException
import koren.proj.puzzlesolver.model.customeExceptions.puzzleExceptions.XOccurrencesException

private const val X_SQUARE: String = "X"
private const val X_OCCURRENCES: Int = 1

open class SlidingPuzzle (private val state: Array<Array<String>>) : AbstractPuzzle(state) {

    init {
        checkValid()
    }

    override fun generateSteps(): Set<AbstractPuzzle> {
        val xPosition: Position = findXPosition()
        val neighbours: List<Position> = findNeighboursPositions(xPosition)

        return createFutureStepsFromPositions(neighbours, xPosition)
    }

    final override fun checkValid() {
        val validatePuzzleSize: (Array<String>) -> Boolean = { it.isEmpty()}
        if (state.isEmpty() || state.any(validatePuzzleSize)){
            throw PuzzleSizeException()
        }

        val validateXOccurrences: (String) -> Boolean = { it == X_SQUARE }
        if (state.fold(0) { sumX, row: Array<String> -> sumX + row.count(validateXOccurrences) } != X_OCCURRENCES){
            throw XOccurrencesException()
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

        return neighborsIndexList.filter(checkPossibleStepPosition())
    }

    private fun checkPossibleStepPosition(): (Position) -> Boolean = {
        val checkPosition = it

        isIndexNotNegative(checkPosition) && isIndexSmallerThanMax(checkPosition)
    }

    private fun isIndexNotNegative(checkPosition: Position): Boolean {
        return checkPosition.rowIndex >= 0 && checkPosition.colIndex >= 0
    }

    private fun isIndexSmallerThanMax(checkPosition: Position): Boolean {
        return checkPosition.rowIndex < state.size && checkPosition.colIndex < state[checkPosition.rowIndex].size
    }

    private fun createFutureStepsFromPositions(
        neighboursPosition: Collection<Position>,
        xPosition: Position
    ): Set<AbstractPuzzle> {

        val possibleSteps = HashSet<AbstractPuzzle>()
        for (neighborPosition in neighboursPosition) {
            val stateCopy: Array<Array<String>> = state.map { it.clone() }.toTypedArray()

            stateCopy[xPosition.rowIndex][xPosition.colIndex] =
                stateCopy[neighborPosition.rowIndex][neighborPosition.colIndex]
            stateCopy[neighborPosition.rowIndex][neighborPosition.colIndex] = X_SQUARE
            possibleSteps += SlidingPuzzle(stateCopy)
        }

        return possibleSteps
    }
}