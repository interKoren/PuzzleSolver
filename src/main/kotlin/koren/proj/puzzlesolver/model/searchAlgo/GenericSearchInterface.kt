package koren.proj.puzzlesolver.model.searchAlgo

import koren.proj.puzzlesolver.model.puzzle.AbstractPuzzle

interface GenericSearchInterface {

    var parentMap : HashMap<AbstractPuzzle, AbstractPuzzle>
    var visitedSet : HashSet<AbstractPuzzle>

    fun search(initial_puzzle: AbstractPuzzle, goal: AbstractPuzzle)
}