package koren.proj.puzzlesolver.model.customeExceptions.puzzleExceptions

private const val ILLEGAL_PUZZLE_SIZE_MESSAGE = "Illegal maze size - empty"

class PuzzleSizeException() : Exception(ILLEGAL_PUZZLE_SIZE_MESSAGE) {
}