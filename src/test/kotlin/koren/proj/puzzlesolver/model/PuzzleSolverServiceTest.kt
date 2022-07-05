package koren.proj.puzzlesolver.model

import koren.proj.puzzlesolver.model.puzzle.MazePuzzle
import koren.proj.puzzlesolver.model.puzzle.SlidingPuzzle
import koren.proj.puzzlesolver.model.searchAlgo.BFS
import koren.proj.puzzlesolver.model.searchAlgo.DFS
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private val INITIAL_MAZE_PUZZLE: MazePuzzle = MazePuzzle(
    arrayOf(arrayOf("0", "0", "1"), arrayOf("0", "0", "1"), arrayOf("1", "X", "0"), arrayOf("1", "0", "1")))
private val FIRST_STEP_MAZE_PUZZLE: MazePuzzle = MazePuzzle(
    arrayOf(arrayOf("0", "0", "1"), arrayOf("0", "X", "1"), arrayOf("1", "0", "0"), arrayOf("1", "0", "1")))
private val DFS_SECOND_STEP_MAZE_PUZZLE: MazePuzzle = MazePuzzle(
    arrayOf(arrayOf("0", "X", "1"), arrayOf("0", "0", "1"), arrayOf("1", "0", "0"), arrayOf("1", "0", "1")))
private val BFS_SECOND_STEP_MAZE_PUZZLE: MazePuzzle = MazePuzzle(
    arrayOf(arrayOf("0", "0", "1"), arrayOf("X", "0", "1"), arrayOf("1", "0", "0"), arrayOf("1", "0", "1")))
private val GOAL_MAZE_PUZZLE: MazePuzzle = MazePuzzle(
    arrayOf(arrayOf("X", "0", "1"), arrayOf("0", "0", "1"), arrayOf("1", "0", "0"), arrayOf("1", "0", "1")))

private val INITIAL_SLIDING_PUZZLE: SlidingPuzzle = SlidingPuzzle(
    arrayOf(arrayOf("2", "3"), arrayOf("1", "X")))
private val FIRST_STEP_SLIDING_PUZZLE: SlidingPuzzle = SlidingPuzzle(
    arrayOf(arrayOf("2", "X"), arrayOf("1", "3")))
private val SECOND_STEP_SLIDING_PUZZLE: SlidingPuzzle = SlidingPuzzle(
    arrayOf(arrayOf("X", "2"), arrayOf("1", "3")))
private val THIRD_SLIDING_PUZZLE: SlidingPuzzle = SlidingPuzzle(
    arrayOf(arrayOf("1", "2"), arrayOf("X", "3")))
private val GOAL_SLIDING_PUZZLE: SlidingPuzzle = SlidingPuzzle(
    arrayOf(arrayOf("1", "2"), arrayOf("3", "X")))

internal class PuzzleSolverServiceTest {

    private lateinit var bfs: BFS
    private lateinit var dfs: DFS

    @BeforeEach
    fun resetSearchersBeforeTest() {
        bfs = BFS()
        dfs = DFS()
    }

    @Test
    fun shouldReturnStepsForMaze_whenBFSPathExists() {
        val resultedSteps = bfs.search(INITIAL_MAZE_PUZZLE, GOAL_MAZE_PUZZLE)
        val expectedSteps = listOf(INITIAL_MAZE_PUZZLE, FIRST_STEP_MAZE_PUZZLE,
            BFS_SECOND_STEP_MAZE_PUZZLE, GOAL_MAZE_PUZZLE)

        assertTrue(resultedSteps == expectedSteps)
    }

    @Test
    fun shouldReturnStepsForMaze_whenDFSPathExists() {
        val resultedSteps = dfs.search(INITIAL_MAZE_PUZZLE, GOAL_MAZE_PUZZLE)
        val expectedSteps = listOf(INITIAL_MAZE_PUZZLE, FIRST_STEP_MAZE_PUZZLE,
            DFS_SECOND_STEP_MAZE_PUZZLE, GOAL_MAZE_PUZZLE)

        assertTrue(resultedSteps == expectedSteps)
    }

    @Test
    fun shouldReturnStepsForSliding_whenDFSPathExists() {
        val resultedSteps = dfs.search(INITIAL_SLIDING_PUZZLE, GOAL_SLIDING_PUZZLE)
        val expectedSteps = listOf(INITIAL_SLIDING_PUZZLE, FIRST_STEP_SLIDING_PUZZLE,
            SECOND_STEP_SLIDING_PUZZLE, THIRD_SLIDING_PUZZLE, GOAL_SLIDING_PUZZLE)

        assertTrue(resultedSteps == expectedSteps)
    }

    @Test
    fun shouldReturnStepsForSliding_whenBFSPathExists() {
        val resultedSteps = dfs.search(INITIAL_SLIDING_PUZZLE, GOAL_SLIDING_PUZZLE)
        val expectedSteps = listOf(INITIAL_SLIDING_PUZZLE, FIRST_STEP_SLIDING_PUZZLE,
            SECOND_STEP_SLIDING_PUZZLE, THIRD_SLIDING_PUZZLE, GOAL_SLIDING_PUZZLE)

        assertTrue(resultedSteps == expectedSteps)
    }
}