package koren.proj.puzzlesolver.model.searchAlgo

import koren.proj.puzzlesolver.model.puzzle.AbstractPuzzle
import koren.proj.puzzlesolver.model.puzzle.MazePuzzle
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*


private val MOCK_INITIAL_NO_PATH_PUZZLE: MazePuzzle = Mockito.mock(MazePuzzle::class.java)
private val MOCK_GOAL_NO_PATH_PUZZLE: MazePuzzle = Mockito.mock(MazePuzzle::class.java)
private val MOCK_INITIAL_PATH_PUZZLE: MazePuzzle = Mockito.mock(MazePuzzle::class.java)
private val MOCK_FIRST_CHILD_PUZZLE: MazePuzzle = Mockito.mock(MazePuzzle::class.java)
private val MOCK_SECOND_CHILD_PUZZLE: MazePuzzle = Mockito.mock(MazePuzzle::class.java)
private val MOCK_THIRD_CHILD_PUZZLE: MazePuzzle = Mockito.mock(MazePuzzle::class.java)
private val MOCK_CHILD_OF_FIRST_CHILD_PUZZLE: MazePuzzle = Mockito.mock(MazePuzzle::class.java)
private val MOCK_GOAL_PATH_PUZZLE: MazePuzzle = Mockito.mock(MazePuzzle::class.java)


internal class BFSTest {

    private lateinit var bfs: BFS

    @BeforeEach
    fun beforeEachTest() {
        bfs = BFS()
    }

    @Test
    fun shouldReturnEmptyList_whenThereIsNoNextPossibleState(){
        Mockito.`when`(MOCK_INITIAL_NO_PATH_PUZZLE.generateSteps()).thenReturn(LinkedList<AbstractPuzzle>())
        val resultedSteps = bfs.search(MOCK_INITIAL_NO_PATH_PUZZLE, MOCK_GOAL_NO_PATH_PUZZLE)

        Assertions.assertTrue(resultedSteps.isEmpty())
    }

    @Test
    fun shouldReturnEmptyList_whenThereIsNoPathToGoal(){
        Mockito.`when`(MOCK_INITIAL_NO_PATH_PUZZLE.generateSteps()).thenReturn(listOf(
            MOCK_FIRST_CHILD_PUZZLE, MOCK_SECOND_CHILD_PUZZLE, MOCK_THIRD_CHILD_PUZZLE
        ))
        Mockito.`when`(MOCK_FIRST_CHILD_PUZZLE.generateSteps()).thenReturn(listOf(
            MOCK_CHILD_OF_FIRST_CHILD_PUZZLE
        ))
        Mockito.`when`(MOCK_CHILD_OF_FIRST_CHILD_PUZZLE.generateSteps()).thenReturn(listOf())
        Mockito.`when`(MOCK_SECOND_CHILD_PUZZLE.generateSteps()).thenReturn(listOf(
            MOCK_CHILD_OF_FIRST_CHILD_PUZZLE
        ))
        Mockito.`when`(MOCK_THIRD_CHILD_PUZZLE.generateSteps()).thenReturn(listOf(
            MOCK_SECOND_CHILD_PUZZLE
        ))
        val resultedSteps = bfs.search(MOCK_INITIAL_NO_PATH_PUZZLE, MOCK_GOAL_NO_PATH_PUZZLE)

        Assertions.assertTrue(resultedSteps.isEmpty())
    }

    @Test
    fun shouldStatePath_whenThereIsPathToGoal(){
        Mockito.`when`(MOCK_INITIAL_PATH_PUZZLE.generateSteps()).thenReturn(
            listOf(MOCK_FIRST_CHILD_PUZZLE, MOCK_SECOND_CHILD_PUZZLE, MOCK_THIRD_CHILD_PUZZLE)
        )
        Mockito.`when`(MOCK_FIRST_CHILD_PUZZLE.generateSteps()).thenReturn(
            listOf(MOCK_CHILD_OF_FIRST_CHILD_PUZZLE)
        )
        Mockito.`when`(MOCK_CHILD_OF_FIRST_CHILD_PUZZLE.generateSteps()).thenReturn(
            listOf(MOCK_FIRST_CHILD_PUZZLE)
        )
        Mockito.`when`(MOCK_SECOND_CHILD_PUZZLE.generateSteps()).thenReturn(
            listOf(MOCK_GOAL_PATH_PUZZLE)
        )
        Mockito.`when`(MOCK_THIRD_CHILD_PUZZLE.generateSteps()).thenReturn(
            listOf(MOCK_GOAL_PATH_PUZZLE)
        )
        val resultedSteps = bfs.search(MOCK_INITIAL_PATH_PUZZLE, MOCK_GOAL_PATH_PUZZLE)
        val expectedSteps = listOf(MOCK_INITIAL_PATH_PUZZLE, MOCK_SECOND_CHILD_PUZZLE, MOCK_GOAL_PATH_PUZZLE)

        Assertions.assertTrue(resultedSteps == expectedSteps)
    }

}