package koren.proj.puzzlesolver.model.customeExceptions.puzzleExceptions

private const val ILLEGAL_VALUES_MESSAGE = "There is illegal values in maze - Allow only: 1 | 0 | X"

class IllegalMazeValuesException() : Exception(ILLEGAL_VALUES_MESSAGE) {
}