package koren.proj.puzzlesolver.model.searchAlgo

import koren.proj.puzzlesolver.model.puzzle.AbstractPuzzle

class BFS : GenericSearchInterface {

    override var parentMap: HashMap<AbstractPuzzle, AbstractPuzzle> = HashMap()
    override var visitedSet: HashSet<AbstractPuzzle> = HashSet()

    override fun search(initial_puzzle: AbstractPuzzle, goal: AbstractPuzzle) {
        TODO("Not yet implemented")
    }
}