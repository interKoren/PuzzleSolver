package koren.proj.puzzlesolver.model.searchAlgo

import koren.proj.puzzlesolver.model.puzzle.AbstractPuzzle
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class DFS : GenericSearchInterface {

    override fun search(initialPuzzle: AbstractPuzzle, goal: AbstractPuzzle): List<AbstractPuzzle> {
        val childToParentMapping: HashMap<AbstractPuzzle, AbstractPuzzle> = HashMap()
        val visitedSet: HashSet<AbstractPuzzle> = HashSet()
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
                        childToParentMapping[currentNeighbor] = currentPuzzle
                        searchQueue.push(currentNeighbor)
                    }
                }
            }
        }

        return if (currentPuzzle == goal) {
            inferStepsToGoal(initialPuzzle, goal, childToParentMapping)
        } else {
            LinkedList<AbstractPuzzle>()
        }
    }

    private fun inferStepsToGoal(
        initPuzzle: AbstractPuzzle,
        goal: AbstractPuzzle,
        childToParentMapping: HashMap<AbstractPuzzle, AbstractPuzzle>
    ): List<AbstractPuzzle> {
        var currentPuzzle = goal
        val stepsToFirstFromGoal = LinkedList(listOf(currentPuzzle))

        while (currentPuzzle != initPuzzle) {
            stepsToFirstFromGoal.add(childToParentMapping[currentPuzzle])
            currentPuzzle = childToParentMapping[currentPuzzle]!!
        }

        return stepsToFirstFromGoal.reversed()
    }
}