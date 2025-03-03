package ui

import CustomPrintSequencerViewModel
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun division() {

    Spacer(modifier = Modifier.height(8.dp))
    Divider()
    Spacer(modifier = Modifier.height(8.dp))

}


@Composable
fun headerTittle() {
    Text(text = "Intervalo de Paginas", fontSize = 20.sp)
}


@Composable
fun textFieldPageInputs(
    initialPage: String, finalPage: String,
    initialPageValueChanged: (String) -> Unit,
    finalPageValueChanged: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth() // Asegura que el Row ocupe todo el ancho disponible
    ) {
        textFieldInput("Pagina inicial", Modifier.weight(1f), initialPage, initialPageValueChanged)
        Spacer(modifier = Modifier.width(16.dp)) // Espacio horizontal de 16.dp
        textFieldInput("Pagina final", Modifier.weight(1f), finalPage, finalPageValueChanged)

    }
}

@Composable
fun textFieldInput(title: String, modifier: Modifier, number: String, numberPageValueChanged: (String) -> Unit) {
    var isFocused by remember { mutableStateOf(false) }

    TextField(
        value = number,
        onValueChange = numberPageValueChanged,
        label = { Text(title, modifier = Modifier.padding(bottom = 0.dp), color = Color.Black) }, // Evita superposición
        textStyle = TextStyle(fontSize = 12.sp),
        modifier = modifier
            .defaultMinSize(minHeight = 20.dp)
            .padding(vertical = 0.dp)
            .onFocusChanged { isFocused = it.isFocused }, // Detecta el foco
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = if (isFocused) Color.White else Color(0xFFf6f6f6), // Cambia color de fondo
            focusedIndicatorColor = Color.Red, // Borde rojo al enfocar
            unfocusedIndicatorColor = Color.LightGray // Borde gris cuando no tiene foco
        )
    )
}

@Preview
@Composable
fun inputsBotonConfirmation(viewModel: CustomPrintSequencerViewModel) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        OutlinedButton(
            onClick = { },
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF0da1fd)),
            border = BorderStroke(1.dp, Color(0xFF0da1fd))
        ) {
            Text("Cancelar")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = { viewModel.confirm() },
            colors = ButtonDefaults.buttonColors(contentColor = Color.White, backgroundColor = Color(0xFF0da1fd))
        ) {
            Text("Aceptar")
        }
    }

}


@Composable
fun pageGenerationContainer(viewModel: CustomPrintSequencerViewModel) {
    Column(Modifier.fillMaxWidth()) {
        pagesGeneration("Todas las páginas del lado frontal", viewModel.frontPagesRange.collectAsState())
        pagesGeneration("Todas las páginas del lado opuesto", viewModel.oppositePageRange.collectAsState())
    }
}

@Composable
fun pagesGeneration(titulo: String, state: State<String>) {
    Column(Modifier.fillMaxWidth()) {
        Text(text = titulo, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = state.value,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )
        Button(
            onClick = { },
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF270733),
                contentColor = Color.White
            )
        ) {
            Text("Copiar")
        }
    }
}


@Composable
fun printInfo(viewModel: CustomPrintSequencerViewModel) {


    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

        Image(
            painter = painterResource("drawable/printer.png"),
            contentDescription = null,
            modifier = Modifier.width(70.dp).height(80.dp)
        )

        Column(Modifier.fillMaxWidth()) {
            Text(
                text = "Numero de hojas Necesarias: ${viewModel.necesarySheet.collectAsState().value}",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
            Text(
                text = "Numero total de paginas: ${viewModel.totalSheet.collectAsState().value}",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
            Text(
                text = "Total de paginas en el frente: ${viewModel.pageNumberFront.collectAsState().value}",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
            Text(
                text = "Total de paginas en el dorso: ${viewModel.pageNumberBack.collectAsState().value}",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun CustomPrintSequenceDesktop(viewModel: CustomPrintSequencerViewModel = viewModel()) {
    val initialPage by viewModel.initialRange.collectAsState()
    val finalPage by viewModel.finalRange.collectAsState()


    Box(modifier = Modifier.padding(16.dp)) {
        Column() {
            headerTittle()
            division()

            textFieldPageInputs(
                initialPage, finalPage,
                initialPageValueChanged = {
                    viewModel.updateInitialRange(it)
                },
                finalPageValueChanged = {
                    viewModel.updateFinalRange(it)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            division()
            inputsBotonConfirmation(viewModel)
            Spacer(modifier = Modifier.height(16.dp))
            pageGenerationContainer(viewModel)
            printInfo(viewModel)
        }
    }
}


