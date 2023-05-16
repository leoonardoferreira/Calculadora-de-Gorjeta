package com.example.calculadoradegorjeta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoradegorjeta.ui.theme.CalculadoraDeGorjetaTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraDeGorjetaTheme {
                AppCalculadoraDeGorjeta()
            }
        }
    }
}

@Preview
@Composable
fun AppCalculadoraDeGorjeta() {
    var valorServico by remember { mutableStateOf("") }
    var porcentagemDaGorjeta by remember { mutableStateOf("") }
    var gorjeta by remember { mutableStateOf(0.0) }
    var arredondar by remember { mutableStateOf(false) }

    gorjeta = CalcularGorjeta(valorServico, porcentagemDaGorjeta)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                text = "Calculadora de Gorjeta",
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.size(20.dp))
            CampoTextoEditavel(
                label = "Valor do serviço",
                value = valorServico,
                onValueChange = { valorServico = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
            CampoTextoEditavel(
                label = "Gorjeta (%)",
                value = porcentagemDaGorjeta,
                onValueChange = { porcentagemDaGorjeta = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 50.dp)
            ) {
                Text(text = "Arredondar gorjeta?")
                Switch(
                    checked = arredondar,
                    onCheckedChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = "Valor da Gorjeta: ${NumberFormat.getCurrencyInstance().format(gorjeta)}",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CampoTextoEditavel(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) {
    Spacer(modifier = Modifier.size(5.dp))
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

fun CalcularGorjeta(valorServico: String, porcentagemDaGorjeta: String):Double {
    return (valorServico.toDoubleOrNull()?:0.0) * (porcentagemDaGorjeta.toDoubleOrNull()?:0.0) / 100
}

//fun ArredondarGorjeta() {
//
//}