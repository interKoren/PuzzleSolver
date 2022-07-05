package koren.proj.puzzlesolver.model.customeExceptions.puzzleExceptions

private const val X_ILLEGAL_OCCURRENCES_MESSAGE = "X occurring illegal amount of times"

class XOccurrencesException() : Exception(X_ILLEGAL_OCCURRENCES_MESSAGE) {
}