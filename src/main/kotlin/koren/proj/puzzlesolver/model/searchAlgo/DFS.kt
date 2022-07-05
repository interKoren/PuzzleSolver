package koren.proj.puzzlesolver.model.searchAlgo

import koren.proj.puzzlesolver.model.puzzle.AbstractPuzzle
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class DFS : GenericSearchInterface {

    private val parentMap: HashMap<AbstractPuzzle, AbstractPuzzle> = HashMap()
    private val visitedSet: HashSet<AbstractPuzzle> = HashSet()

    override fun search(initialPuzzle: AbstractPuzzle, goal: AbstractPuzzle): Collection<AbstractPuzzle> {
        var currentPuzzle = initialPuzzle
        val searchQueue: Stack<AbstractPuzzle> = Stack<AbstractPuzzle>()
        searchQueue.push(currentPuzzle)

        while (!searchQueue.isEmpty() && currentPuzzle != goal) {
            currentPuzzle = searchQueue.pop()
            if (!visitedSet.contains(currentPuzzle)) {
                visitedSet.add(currentPuzzle)
                val currentNeighbors = currentPuzzle.generateSteps()

                for (currentNeighbor in currentNeighbors) {
                    if (!visitedSet.contains(currentNeighbor)) {
                        parentMap[currentNeighbor] = currentPuzzle
                        searchQueue.push(currentNeighbor)
                    }
                }
            }
        }

        return if (currentPuzzle == goal) {
            inferStepsToGoal(initialPuzzle, goal)
        } else {
            LinkedList<AbstractPuzzle>()
        }
    }

    private fun inferStepsToGoal(initPuzzle: AbstractPuzzle, goal: AbstractPuzzle): Collection<AbstractPuzzle> {
        var currentPuzzle = goal
        val stepsToGoal = LinkedList(listOf(currentPuzzle))

        while (currentPuzzle != initPuzzle) {
            stepsToGoal.add(parentMap[currentPuzzle])
            currentPuzzle = parentMap[currentPuzzle]!!
        }

        return stepsToGoal.reversed()
    }
}