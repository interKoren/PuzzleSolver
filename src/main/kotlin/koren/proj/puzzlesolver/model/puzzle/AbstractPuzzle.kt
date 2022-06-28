package koren.proj.puzzlesolver.model.puzzle

abstract class AbstractPuzzle(private val state: Array<Array<String>>) {

    abstract fun getChildren(): Array<AbstractPuzzle>
    abstract fun checkValid(): Boolean

    override fun equals(other: Any?): Boolean {
        if (other is AbstractPuzzle){
            val otherAP: AbstractPuzzle = other

            return otherAP.state.contentEquals(this.state)
        }
        return false
    }

    override fun hashCode(): Int {
        return state.contentDeepHashCode()
    }
}
