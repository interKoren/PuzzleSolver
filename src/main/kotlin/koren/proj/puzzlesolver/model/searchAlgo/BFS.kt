package koren.proj.puzzlesolver.model.searchAlgo

import koren.proj.puzzlesolver.model.puzzle.AbstractPuzzle
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class BFS : GenericSearchInterface {

    override fun search(initialPuzzle: AbstractPuzzle, goal: AbstractPuzzle): List<AbstractPuzzle> {
        val childToParentMap: HashMap<AbstractPuzzle, AbstractPuzzle> = HashMap()
        val visitedSet: HashSet<AbstractPuzzle> = HashSet()
        var currentPuzzle = initialPuzzle
        val searchQueue: Queue<AbstractPuzzle> = LinkedList(listOf(currentPuzzle))
        visitedSet.add(currentPuzzle)

        while (!searchQueue.isEmpty() && currentPuzzle != goal) {
            currentPuzzle = searchQueue.remove()
            val currentNeighbors = currentPuzzle.generateSteps()

            for (currentNeighbor in currentNeighbors) {
                if (!visitedSet.contains(currentNeighbor)) {
                    childToParentMap[currentNeighbor] = currentPuzzle
                    searchQueue.add(currentNeighbor)
                    visitedSet.add(currentNeighbor)
                }
            }
        }

        return if (currentPuzzle == goal) {
            inferStepsToGoal(initialPuzzle, goal, childToParentMap)
        } else {
            LinkedList<AbstractPuzzle>()
        }
    }

    private fun inferStepsToGoal(
        initPuzzle: AbstractPuzzle,
        goal: AbstractPuzzle,
        childToParentMap: HashMap<AbstractPuzzle, AbstractPuzzle>
    ): List<AbstractPuzzle> {
        var currentPuzzle = goal
        val stepsToFirstFromGoal = LinkedList(listOf(currentPuzzle))

        while (currentPuzzle != initPuzzle) {
            stepsToFirstFromGoal.add(childToParentMap[currentPuzzle])
            currentPuzzle = childToParentMap[currentPuzzle]!!
        }

        return stepsToFirstFromGoal.reversed()
    }
}