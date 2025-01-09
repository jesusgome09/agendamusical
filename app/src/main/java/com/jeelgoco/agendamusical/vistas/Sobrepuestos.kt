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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun _vistaPrevia() {
    Scaffold(
        floatingActionButton = { Sobrepuestos().FabAdd() },
        floatingActionButtonPosition = androidx.compose.material3.FabPosition.Center,
        bottomBar = { Sobrepuestos().BotonDebajo() },
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
    fun BotonDebajo() {

        BottomAppBar(
            containerColor = Color(0xFFB0A4FD),
            tonalElevation = 8.dp

        ) {

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.Face, contentDescription = "Gallery",
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.weight(1f, true))
            Spacer(modifier = Modifier.weight(1f, true))

            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.Search, contentDescription = "search",
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.Settings, contentDescription = "Config",
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )
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