package koren.proj.puzzlesolver.model.searchAlgo

import koren.proj.puzzlesolver.model.puzzle.AbstractPuzzle

interface GenericSearchInterface {

    fun search(initialPuzzle: AbstractPuzzle, goal: AbstractPuzzle): Collection<AbstractPuzzle>
}