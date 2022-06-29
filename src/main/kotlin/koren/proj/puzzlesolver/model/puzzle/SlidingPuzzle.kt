package koren.proj.puzzlesolver.model.puzzle

import java.util.LinkedList

private const val X_SQUARE: String = "X"
private const val X_OCCURRENCES: Int = 1

class SlidingPuzzle (private val state: Array<Array<String>>) : AbstractPuzzle(state) {

    init {
        checkValid()
    }

    private fun findXIndices(): Array<Int> {
        // return indices of singular X value
        val xRowIndex: Int = state.indices.first { row: Int -> state[row].contains(X_SQUARE) }
        val xColIndex: Int = state.indices.first { col: Int -> state[xRowIndex][col] == X_SQUARE }

        return arrayOf(xRowIndex, xColIndex)
    }

    private fun findXNeighboursIndices(xIndices: Array<Int>): Collection<Array<Int>> {
        val neighIndLst: Collection<Array<Int>>
        val initialNeighIndLst: Collection<Array<Int>> = LinkedList<Array<Int>>()

        // add all possible neighbours indexes to list
        val rightNeighbour = arrayOf(xIndices[0] + 1, xIndices[1])
        val leftNeighbour = arrayOf(xIndices[0] - 1, xIndices[1])
        val upNeighbour = arrayOf(xIndices[0], xIndices[1] + 1)
        val downNeighbour = arrayOf(xIndices[0], xIndices[1] - 1)
        initialNeighIndLst.plus(listOf(rightNeighbour, leftNeighbour, upNeighbour, downNeighbour))

        //define predicate for filtering legal indices
        val legalIndices: (Array<Int>) -> Boolean = { (it[0] > 0 && it[1] > 0) &&
                (it[0] < state.size && it[1] < state[it[0]].size) }
        // remove illegal neighbours
        neighIndLst = initialNeighIndLst.filter(legalIndices)

        return neighIndLst
    }
    private fun iterateCreatorSteps(
        xNeighbours: Collection<Array<Int>>,
        xIndices: Array<Int>,
        possibleSteps: Collection<AbstractPuzzle>
    ): Collection<AbstractPuzzle> {
        //iterate and create all possible next steps
        for (neighInd in xNeighbours) {
            val stateCopy: Array<Array<String>> = state.clone()
            // swapping the movable square
            stateCopy[xIndices[0]][xIndices[1]] = stateCopy[neighInd[0]][neighInd[1]]
            stateCopy[neighInd[0]][neighInd[1]] = X_SQUARE
            possibleSteps.plus(SlidingPuzzle(stateCopy))
        }

        return possibleSteps
    }

    override fun generateSteps(): Collection<AbstractPuzzle> {
        val possibleSteps: Collection<AbstractPuzzle> = LinkedList<AbstractPuzzle>()

        val xIndices: Array<Int> = findXIndices() // get X square indices
        val xNeighbours: Collection<Array<Int>> = findXNeighboursIndices(xIndices) // get indices of X neighbours

        // Generate all possible next steps
        return iterateCreatorSteps(xNeighbours, xIndices, possibleSteps)
    }

    override fun checkValid() {
        val rowPredicateSize: (Array<String>) -> Boolean = { it.isEmpty()}
        if (state.isEmpty() || state.any(rowPredicateSize))
            throw Exception("Illegal maze size - empty")

        val rowPredicateX: (String) -> Boolean = { it == X_SQUARE }
        if (state.fold(0) { sumX, row: Array<String> -> sumX + row.count(rowPredicateX) } == X_OCCURRENCES)
            throw Exception("X occurring illegal amount of times")
    }
}