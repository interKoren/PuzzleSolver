package koren.proj.puzzlesolver.model.puzzle

class MazePuzzle (private val state: Array<Array<String>>) : AbstractPuzzle(state) {

    init {
        checkValid()
    }

    override fun getChildren(): Array<AbstractPuzzle> {
        TODO("Not yet implemented")
    }

    override fun checkValid(): Boolean {
        TODO("Not yet implemented")
    }
}