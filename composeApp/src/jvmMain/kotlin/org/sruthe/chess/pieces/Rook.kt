package org.sruthe.chess.pieces

class Rook(color: Color): Piece(color) {
    override fun movesStraight() = true
}