import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.WindowState
import ui.CustomPrintSequenceDesktop
import java.awt.Label

fun main() = application {
    val windowState = WindowState(width =440.dp, height = 700.dp )


    Window(
        onCloseRequest = ::exitApplication,
        title = "CustomPrintSecuencer",
        state = windowState
    ) {
        CustomPrintSequenceDesktop()
    }
}




