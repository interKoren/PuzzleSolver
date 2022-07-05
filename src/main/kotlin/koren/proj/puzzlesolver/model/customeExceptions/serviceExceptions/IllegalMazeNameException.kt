package koren.proj.puzzlesolver.model.customeExceptions.serviceExceptions

private const val ILLEGAL_MAZE_NAME_MESSAGE = "Input maze name is illegal, should be: Maze | Sliding"

class IllegalMazeNameException : Exception(ILLEGAL_MAZE_NAME_MESSAGE) {}