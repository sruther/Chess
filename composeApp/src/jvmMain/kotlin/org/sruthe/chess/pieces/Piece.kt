package org.sruthe.chess.pieces
enum class Color{
    WHITE,
    BLACK
}

data class Position(val row: Int, val col: Int)

abstract class Piece(var color: Color) {
    var possibleMoves: MutableList<Position> = mutableListOf()
    open fun movesDiagonal() = false
    open fun movesStraight() = false
    var isProtected = false
}