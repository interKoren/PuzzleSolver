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

class PuzzleSolverService(initial_state: Array<Array<String>>, goal_state: Array<Array<String>>,
                          puzzleName: String, searcherName: String) {

    private val puzzle: AbstractPuzzle
    private val solvedPuzzle: AbstractPuzzle
    private val searcher: GenericSearchInterface

    init {

        when(puzzleName) {
            MAZE_PUZZLE_NAME -> {
                this.puzzle = MazePuzzle(initial_state)
                this.solvedPuzzle = MazePuzzle(goal_state)
            }
            SLIDING_PUZZLE_NAME -> {
                this.puzzle = SlidingPuzzle(initial_state)
                this.solvedPuzzle = SlidingPuzzle(goal_state)
            }
            else -> {
                throw IllegalMazeNameException()
            }
        }
        when(searcherName) {
            BFS_SEARCHER -> this.searcher = BFS()
            DFS_SEARCHER -> this.searcher = DFS()
            else -> {
                throw IllegalSearcherNameException()
            }
        }
    }

    fun solve(): Collection<AbstractPuzzle> {
        val stepsList = searcher.search(this.puzzle, this.solvedPuzzle)

        for (step in stepsList) {
            println(step)
        }

        return stepsList
    }
}