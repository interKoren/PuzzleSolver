package koren.proj.puzzlesolver.model.customeExceptions.serviceExceptions

private const val ILLEGAL_SEARCHER_NAME_MESSAGE = "Input searcher name is illegal, should be: BFS | DFS"

class IllegalSearcherNameException : Exception(ILLEGAL_SEARCHER_NAME_MESSAGE) {}