package org.sruthe.chess

import org.sruthe.chess.pieces.Bishop
import org.sruthe.chess.pieces.Color
import org.sruthe.chess.pieces.King
import org.sruthe.chess.pieces.Knight
import org.sruthe.chess.pieces.Pawn
import org.sruthe.chess.pieces.Piece
import org.sruthe.chess.pieces.Position
import org.sruthe.chess.pieces.Queen
import org.sruthe.chess.pieces.Rook

class Square(color: Color, position: Position, piece: Piece? = null) {
    var piece = piece
    var color = color
    var position = position
}
class Board {
    val squares = initializeSquares()


    fun getSquare(position: Position): Square {
        return squares[position.row - 1][position.col -1]
    }

    fun getPiece(position: Position): Piece? {
        return getSquare(position).piece
    }

    fun movePiece(from: Position, to: Position) {

    }

    fun evaluatePossibleMoves(){
        for (row in squares) {
            for (square in row){
                var piece = square.piece
                var position = square.position
                if(piece == null) continue
                if (piece is Pawn){

                }
            }
        }
    }

    fun initializeSquares(): Array<Array<Square>> {
        val temp = Array(8) { row ->
            Array(8) { col ->
                val color = if ((row + col) % 2 == 0) Color.BLACK else Color.WHITE
                Square(color, Position(row, col))
            }
        }
        return temp
    }
    fun initializePieces(){
        getSquare(Position(1, 1)).piece = Rook(Color.WHITE)
        getSquare(Position(1, 2)).piece = Knight(Color.WHITE)
        getSquare(Position(1, 3)).piece = Bishop(Color.WHITE)
        getSquare(Position(1, 4)).piece = Queen(Color.WHITE)
        getSquare(Position(1, 5)).piece = King(Color.WHITE)
        getSquare(Position(1, 6)).piece = Bishop(Color.WHITE)
        getSquare(Position(1, 7)).piece = Knight(Color.WHITE)
        getSquare(Position(1, 8)).piece = Rook(Color.WHITE)

        getSquare(Position(8, 1)).piece = Rook(Color.BLACK)
        getSquare(Position(8, 2)).piece = Knight(Color.BLACK)
        getSquare(Position(8, 3)).piece = Bishop(Color.BLACK)
        getSquare(Position(8, 4)).piece = Queen(Color.BLACK)
        getSquare(Position(8, 5)).piece = King(Color.BLACK)
        getSquare(Position(8, 6)).piece = Bishop(Color.BLACK)
        getSquare(Position(8, 7)).piece = Knight(Color.BLACK)
        getSquare(Position(8, 8)).piece = Rook(Color.BLACK)

        for (column in 1..8) getSquare(Position(2, column)).piece = Pawn(Color.WHITE)
        for (column in 1..8) getSquare(Position(7, column)).piece = Pawn(Color.BLACK)
    }
}