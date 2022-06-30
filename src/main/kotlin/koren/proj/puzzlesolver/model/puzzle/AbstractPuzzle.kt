package koren.proj.puzzlesolver.model.puzzle

abstract class AbstractPuzzle(private val state: Array<Array<String>>) {

    abstract fun generateSteps(): Collection<AbstractPuzzle>
    protected abstract fun checkValid()

    override fun hashCode(): Int {
        return state.contentDeepHashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AbstractPuzzle

        if (!state.contentDeepEquals(other.state)) return false

        return true
    }
}
