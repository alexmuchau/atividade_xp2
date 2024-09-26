package com.example.atividadexp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atividadexp2.ui.theme.AtividadeXP2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AtividadeXP2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainLayout()
                }
            }
        }
    }

    companion object {
        var listaJogadores = mutableListOf(
            Jogador(
                name = "P1",
                _bonus = 4,
                _level = 1,
                _modificadores = 2
            ),
            Jogador(
                name = "P2",
                _bonus = 4,
                _level = 1,
                _modificadores = 2
            ),
            Jogador(
                name = "P3",
                _bonus = 4,
                _level = 1,
                _modificadores = 2
            ),
            Jogador(
                name = "P4",
                _bonus = 4,
                _level = 1,
                _modificadores = 2
            ),
        )
    }
}

@Composable
fun AtributoItem(
    label: String,
    valor: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(4.dp)
    ) {
        Text(text = "$label- $valor")
        Row {
            Button(onClick = onDecrement) {
                Text("-")
            }
            Button(onClick = onIncrement) {
                Text("+")
            }
        }
    }
}

@Composable
fun Item(jogador: Jogador) {
    var nome by remember { mutableStateOf(jogador.name) }
    var bonus by remember { mutableStateOf(jogador.bonus) }
    var level by remember { mutableStateOf(jogador.level) }
    var modificadores by remember { mutableStateOf(jogador.modificadores) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = nome,
            onValueChange = {
                nome = it
                jogador.name = it
            },
            label = { Text("Nome do jogador") },
            modifier = Modifier.fillMaxWidth()
        )

        AtributoItem(
            label = "Bonus de Equipamento",
            valor = bonus,
            onIncrement = { bonus++; jogador.bonus = bonus },
            onDecrement = { if (bonus > 0) bonus--; jogador.bonus = bonus }
        )

        AtributoItem(
            label = "Level",
            valor = level,
            onIncrement = { if (level < 10)level++; jogador.level = level },
            onDecrement = { if (level > 0) level--; jogador.level = level }
        )

        AtributoItem(
            label = "Modificadores",
            valor = modificadores,
            onIncrement = { modificadores++; jogador.modificadores = modificadores },
            onDecrement = { if (modificadores > 0) modificadores--; jogador.modificadores = modificadores }
        )

        Text(
            text = "Poder: ${jogador.poder}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .wrapContentWidth(Alignment.CenterHorizontally),
        )
    }
}


@Composable
fun MainLayout() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(MainActivity.listaJogadores) { Jogador ->
            Item(Jogador)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AtividadeXP2Theme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MainLayout()
        }
    }
}