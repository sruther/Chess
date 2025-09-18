package org.sruthe.chess.pieces
enum class Color{
    WHITE,
    BLACK
}

data class Position(val row: Int, val col: Int)

abstract class Piece(color: Color) {
    var color = color
    var possibleMoves: List<Position> = emptyList()
    open fun movesDiagonal() = false
    open fun movesStraight() = false
}