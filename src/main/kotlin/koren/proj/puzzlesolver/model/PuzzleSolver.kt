package koren.proj.puzzlesolver.model

import koren.proj.puzzlesolver.model.puzzle.AbstractPuzzle
import koren.proj.puzzlesolver.model.puzzle.MazePuzzle
import koren.proj.puzzlesolver.model.puzzle.SlidingPuzzle
import koren.proj.puzzlesolver.model.searchAlgo.BFS
import koren.proj.puzzlesolver.model.searchAlgo.GenericSearchInterface

class PuzzleSolver(initial_state: Array<Array<String>>, goal_state: Array<Array<String>>,
                   puzzleName: String, solverName: String) {

    private val puzzle: AbstractPuzzle
    private val solvedPuzzle: AbstractPuzzle
    private val solver: GenericSearchInterface

    init {
        when(puzzleName) {
            "Maze" -> this.puzzle = MazePuzzle(initial_state)
            "Sliding" -> this.puzzle = SlidingPuzzle(initial_state)
            else -> {
                throw Exception("Illegal puzzle type name")
            }
        }
        when(puzzleName) {
            "Maze" -> this.solvedPuzzle = MazePuzzle(goal_state)
            "Sliding" -> this.solvedPuzzle = SlidingPuzzle(goal_state)
            else -> {
                throw Exception("Illegal solved puzzle type name")
            }
        }
        when(solverName) {
            "BFS" -> this.solver = BFS()
            "DFS" -> this.solver = BFS()
            else -> {
                throw Exception("Illegal solver name")
            }
        }
    }

    fun solve() {
        this.solver.search(this.puzzle, this.solvedPuzzle)
    }
}