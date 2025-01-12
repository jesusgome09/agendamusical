package com.jeelgoco.agendamusical.vistas

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


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


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FabAdd(
        couroutineScope: CoroutineScope,
        sheetState: SheetState,
        onShowBottomSheetChange: (Boolean) -> Unit
    ) {

        FloatingActionButton(
            onClick = {

                couroutineScope.launch {
                    sheetState.show()
                    onShowBottomSheetChange(true)
                }


            },
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