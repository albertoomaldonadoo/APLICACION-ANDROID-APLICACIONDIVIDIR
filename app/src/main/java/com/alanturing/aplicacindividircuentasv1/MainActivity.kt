package com.alanturing.aplicacindividircuentasv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
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
    Text(stringResource(R.string.credit_text), style = MaterialTheme.typography.bodySmall)

    Column (Modifier.padding(top = 80.dp).padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)){



        TextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label = { Text(stringResource(R.string.label_amount)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardActions = KeyboardActions {
                KeyboardType.Number
            }
        )

        TextField(
            value = comensales,
            onValueChange = { comensales = it },
            label = { Text(stringResource(R.string.label_people)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardActions = KeyboardActions {
                KeyboardType.Number
            }
        )

        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(165.dp),
            modifier = Modifier.fillMaxWidth()){
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
        }




        Text(stringResource(R.string.label_rating))
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
                text = (stringResource(R.string.button_calculate)),
            )
        }
            Text (
                text = solucion, style = MaterialTheme.typography.bodyMedium,
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