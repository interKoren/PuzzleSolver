package koren.proj.puzzlesolver.model.puzzle

abstract class AbstractPuzzle(private val state: Array<Array<String>>) {

    abstract fun generateSteps(): Collection<AbstractPuzzle>
    protected abstract fun checkValid()

    override fun equals(other: Any?): Boolean {
        if (other is AbstractPuzzle){
            val otherAP: AbstractPuzzle = other

            if (otherAP.state.size != this.state.size) // checks mazes size
                return false

            var isEquals = true
            for (rowInd in 0 until this.state.size) {
                isEquals = isEquals && this.state[rowInd].contentEquals(otherAP.state[rowInd]) // checks mazes values
            }

            return isEquals
        }
        return false
    }

    override fun hashCode(): Int {
        return state.contentDeepHashCode()
    }
}
