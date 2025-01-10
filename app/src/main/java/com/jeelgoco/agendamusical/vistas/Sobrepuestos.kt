package com.jeelgoco.agendamusical.vistas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun _vistaPrevia() {

    var selectedItem by remember { mutableStateOf("Inicio") }
    val navController = rememberNavController()


    Scaffold(
        floatingActionButton = { Sobrepuestos().FabAdd() },
        floatingActionButtonPosition = androidx.compose.material3.FabPosition.Center,
        bottomBar = {
            Sobrepuestos().BotonDebajo(
                navController,
                selectedItem,
                onItemSelected = { selectedItem = it })
        },
        content = {
            con(
                modifier = Modifier
                    .padding(it)
                    .background(Color(0xFFB0A4FD))
                    .fillMaxSize()
            )
        }

    )
}

@Composable
fun con(modifier: Modifier) {
    Column(modifier = modifier) {

    }
}


class Sobrepuestos {

    @Composable
    fun BotonDebajo(
        navController: NavController,
        selectedItem: String,
        onItemSelected: (String) -> Unit
    ) {

        BottomAppBar(
            containerColor = Color(0xFFB0A4FD),
            tonalElevation = 8.dp

        ) {

            val items = listOf(
                Pair("Inicio", Icons.Filled.Home),
                Pair("Galeria", Icons.Filled.Face),
                Pair("Buscar", Icons.Filled.Search),
                Pair("Configuración", Icons.Filled.Settings)

            )

            items.forEach { (route, icon) ->

                IconButton(onClick = {
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                    onItemSelected(route)
                }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = route,
                        modifier = Modifier.size(30.dp),
                        tint = if (route == selectedItem) Color.Black else Color.White
                    )
                }
                if (route != "Configuración") {

                    Spacer(modifier = Modifier.weight(1f, true))
                }
                if (route == "Galeria") {

                    Spacer(modifier = Modifier.weight(1f, true))
                    Spacer(modifier = Modifier.weight(1f, true))
                }


            }

        }
    }


    @Composable
    fun FabAdd() {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(31.dp),

            modifier = Modifier
                .offset(y = (80).dp),
            containerColor = Color.Transparent,
            elevation = androidx.compose.material3.FloatingActionButtonDefaults.elevation(
                defaultElevation = 0.dp
            )


        ) {
            Icon(
                Icons.Filled.AddCircle, contentDescription = "Add",
                modifier = Modifier.size(70.dp),
                tint = Color.White
            )
        }
    }

}