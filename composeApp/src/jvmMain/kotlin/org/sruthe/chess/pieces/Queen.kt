package org.sruthe.chess.pieces

class Queen(color: Color): Piece(color) {
    override fun movesDiagonal() = true
    override fun movesStraight() = true
}