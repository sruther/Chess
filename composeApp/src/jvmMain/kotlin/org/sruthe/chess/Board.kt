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

class Square(var color: Color, var position: Position, var piece: Piece? = null)
class Board {
    val squares = initializeSquares()
    var whiteMoves = mutableListOf<Position>()
    var blackMoves = mutableListOf<Position>()


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
                val piece = square.piece
                val position = square.position
                if(piece == null) continue
                if (piece is Pawn){
                    checkPossibleMove(square, Position(position.row + 1, position.col))
                    checkPossibleMove(square, Position(position.row + 1, position.col + 1))
                    checkPossibleMove(square, Position(position.row + 1, position.col - 1))
                    if (piece.isNeverPlayed) checkPossibleMove(square, Position(position.row + 2, position.col))
                    TODO("Add en passant")
                }
                if (piece is King){
                    for (i in -1..1){
                        for (j in -1..1){
                            checkPossibleMove(square, Position(i, j))
                        }
                    }
                }

            }
        }
    }

    fun checkPossibleMove(square: Square, position: Position) {
        if (position.row < 1 || position.col < 1 || position.row > 8 || position.col > 8) return
        if (position == square.position) return
        val piece = square.piece!!
        val targetPiece = getSquare(position).piece // Possibly null
        if (targetPiece?.color == piece.color && piece !is Pawn){
            targetPiece.isProtected = true
        }
        if (piece is Pawn && targetPiece != null){
            if (square.position.col != position.col){
                if (piece.color == targetPiece.color){
                    targetPiece.isProtected = true
                    return
                }
                addPossibleMove(piece, position)
                return
            }
        }
        val enemyMoves = if (piece.color == Color.WHITE) blackMoves else whiteMoves
        if (piece is King && position in enemyMoves) return
        if (piece is King && getSquare(position).piece?.isProtected == true) return
        addPossibleMove(piece, position)
    }

    fun addPossibleMove(piece: Piece, position: Position) {
        piece.possibleMoves.add(position)
        when(piece.color){
            Color.BLACK -> blackMoves.add(position)
            Color.WHITE -> whiteMoves.add(position)
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