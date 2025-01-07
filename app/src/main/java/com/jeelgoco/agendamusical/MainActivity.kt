package com.jeelgoco.agendamusical

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jeelgoco.agendamusical.vistas.VistaCancion
import com.jeelgoco.agendamusical.vistas.VistaInicio

class MainActivity : ComponentActivity() {


    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppNavigation(modifier = Modifier.fillMaxSize())
        }
    }
}


@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "Inicio", modifier = modifier) {
        composable("Inicio") { VistaInicio(navController) }
        composable("Cancion/{id}") {
            val id = it.arguments?.getString("id")
            id?.let {
                VistaCancion(navController, id.toInt())
            }

        }

    }

}
