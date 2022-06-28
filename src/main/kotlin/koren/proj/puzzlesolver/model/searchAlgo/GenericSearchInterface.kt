package koren.proj.puzzlesolver.model.searchAlgo

import koren.proj.puzzlesolver.model.puzzle.AbstractPuzzle

interface GenericSearchInterface {

    abstract var parentMap : HashMap<AbstractPuzzle, AbstractPuzzle>
    abstract var visitedSet : HashSet<AbstractPuzzle>

    fun search(initial_puzzle: AbstractPuzzle, goal: AbstractPuzzle)
}