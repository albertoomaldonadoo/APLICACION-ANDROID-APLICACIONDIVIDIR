package com.alanturing.aplicacindividircuentasv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alanturing.aplicacindividircuentasv1.ui.theme.AplicaciónDividirCuentasV1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AplicaciónDividirCuentasV1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var cantidad by remember { mutableStateOf("") }
    var comensales by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column {

        Text("Trabajo Realizado por: Alberto Maldonado Triana 2ºDAM")


        TextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label = { Text("CANTIDAD") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.padding(50.dp),
            keyboardActions = KeyboardActions {
                KeyboardType.Number
            }
        )


        TextField(
            value = comensales,
            onValueChange = { comensales = it },
            label = { Text("COMENSALES") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.padding(50.dp),
            keyboardActions = KeyboardActions {
                KeyboardType.Number
            }
        )

        Text("Redondear Propina")
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            )
        )


        Text("Valoración")
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            enabled = checked,
            steps = 4,
            valueRange = 0.1f..5f
        )
        Text(text = sliderPosition.toInt().toString())

        var cantidadAcontar: Int? = cantidad.toIntOrNull()
        var comensalesAcontar: Int? = comensales.toIntOrNull()
        var solucion by remember { mutableStateOf("") }

        Button (
            onClick = {
                if (cantidadAcontar != null && comensalesAcontar != null && comensalesAcontar > 0) {
                    var propina: Float = 0F
                    if(checked == true){
                        propina = cantidadAcontar * ((sliderPosition*5)/100)
                    }
                    var total = cantidadAcontar+propina
                    var cantidadTotalPersonas = total/comensalesAcontar
                    solucion = "Cantidad total: $total€ \n" +
                                "Cada uno: $cantidadTotalPersonas€"
                } else {
                    solucion = "Error en la cuenta."
                }
            },
            modifier = Modifier.fillMaxWidth().padding(10.dp)

        ) {
            Text (
                text = "Calcular",
            )
        }
            Text (
                text = "$solucion",
            )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AplicaciónDividirCuentasV1Theme {
        Greeting("Android")
    }
}