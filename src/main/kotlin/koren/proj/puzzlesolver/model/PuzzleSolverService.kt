package koren.proj.puzzlesolver.model

import koren.proj.puzzlesolver.model.customeExceptions.serviceExceptions.IllegalMazeNameException
import koren.proj.puzzlesolver.model.customeExceptions.serviceExceptions.IllegalSearcherNameException
import koren.proj.puzzlesolver.model.puzzle.AbstractPuzzle
import koren.proj.puzzlesolver.model.puzzle.MazePuzzle
import koren.proj.puzzlesolver.model.puzzle.SlidingPuzzle
import koren.proj.puzzlesolver.model.searchAlgo.BFS
import koren.proj.puzzlesolver.model.searchAlgo.DFS
import koren.proj.puzzlesolver.model.searchAlgo.GenericSearchInterface

private const val MAZE_PUZZLE_NAME = "Maze"
private const val SLIDING_PUZZLE_NAME = "Sliding"
private const val BFS_SEARCHER = "BFS"
private const val DFS_SEARCHER = "DFS"

class PuzzleSolverService() {


    fun solve(
        puzzleName: String, searcherName: String,
        initialState: Array<Array<String>>, goalState: Array<Array<String>>,
    ): Collection<AbstractPuzzle> {
        val (puzzle, goalPuzzle, searcher) = initialSearchArguments(puzzleName, searcherName, initialState, goalState)

        return searcher.search(puzzle, goalPuzzle)
    }

    private fun initialSearchArguments(
        puzzleName: String, searcherName: String,
        initialState: Array<Array<String>>, goalState: Array<Array<String>>)
    : Triple<AbstractPuzzle, AbstractPuzzle, GenericSearchInterface> {
        val puzzle: AbstractPuzzle
        val goalPuzzle: AbstractPuzzle

        when(puzzleName) {
            MAZE_PUZZLE_NAME -> {
                puzzle = MazePuzzle(initialState)
                goalPuzzle = MazePuzzle(goalState)
            }
            SLIDING_PUZZLE_NAME -> {
                puzzle = SlidingPuzzle(initialState)
                goalPuzzle = SlidingPuzzle(goalState)
            }
            else -> {
                throw IllegalMazeNameException()
            }
        }

        val searcher: GenericSearchInterface = when (searcherName) {
            BFS_SEARCHER -> BFS()
            DFS_SEARCHER -> DFS()
            else -> {
                throw IllegalSearcherNameException()
            }
        }

        return Triple(puzzle, goalPuzzle, searcher)
    }
}