package com.jeelgoco.agendamusical

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jeelgoco.agendamusical.datos.MyViewModel
import com.jeelgoco.agendamusical.vistas.Busqueda
import com.jeelgoco.agendamusical.vistas.Sobrepuestos
import com.jeelgoco.agendamusical.vistas.VistaCancion
import com.jeelgoco.agendamusical.vistas.VistaInicio

class MainActivity : ComponentActivity() {


    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val viewModel = MyViewModel(this)

            AppNavigation(modifier = Modifier.fillMaxSize(), viewModel)
        }
    }
}


@Composable
fun AppNavigation(modifier: Modifier, viewModel: MyViewModel) {

    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf("Inicio") }
    var showBottomBar by remember { mutableStateOf(true) }


    Scaffold(
        floatingActionButton = {

            if (showBottomBar) {
                Sobrepuestos().FabAdd()
            }
        },
        floatingActionButtonPosition = androidx.compose.material3.FabPosition.Center,
        bottomBar = {

            Log.i("Navigation", "showBottomBar en eleccion: $showBottomBar")
            if (showBottomBar) {
                Sobrepuestos().BotonDebajo(
                    navController,
                    selectedItem,
                    onItemSelected = { selectedItem = it })
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = "Inicio",
            modifier = modifier.padding(paddingValues)
        ) {
            composable("Inicio") {
                showBottomBar = true
                Log.i("Navigation", "Vamos a inicio -> $showBottomBar")
                VistaInicio(navController, viewModel, modifier)
            }
            composable("Galeria") { TODO() }
            composable("Buscar") { Busqueda().BarraSearch(viewModel, navController) }
            composable("Configuracion") { TODO() }
            composable("Favoritos") { TODO() }
            composable("Cancion/{id}") {
                showBottomBar = false
                val id = it.arguments?.getString("id")
                id?.let {
                    VistaCancion(navController, id.toInt(), viewModel)
                }

            }

        }
    }


}


