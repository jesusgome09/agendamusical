package com.jeelgoco.agendamusical.vistas

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class agregarCanciones {

    @Preview()
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ExpandableBottomShet() {
        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = false
        )
        val corroutineScope = rememberCoroutineScope()


        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        corroutineScope.launch {
                            sheetState.show()
                        }
                    }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    corroutineScope.launch {
                        sheetState.hide()
                    }
                },
                dragHandle = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.15f)
                            .height(5.dp)
                            .background(Color.Gray)
                            .alpha(0.5f)
                            .clip(MaterialTheme.shapes.small)

                    ){

                    }
                }
            ) {
               Text(text = "Bottom Sheet")

                Column {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text="Botton")
                    }
                }
            }


        }
    }
}