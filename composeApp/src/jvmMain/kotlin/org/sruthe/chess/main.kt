package org.sruthe.chess

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.sruthe.chess.pieces.Color
import org.sruthe.chess.pieces.Position
import org.sruthe.chess.pieces.Queen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Chess"
    ) {
        App()
        Greet("seyi")
    }
    var queen = Queen(Color.BLACK, Position(1, 1))
    queen.position
}
@Composable
fun Greet(name: String) {
    Text("Hello $name!")
}
