package com.example.mathsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mathsapp.R
import com.example.mathsapp.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .padding(15.dp)
    ) {
        val expression = remember { mutableStateOf("") }
        val result = viewModel.evaluatedAnswers

        BasicTextField(
            value = expression.value,
            onValueChange = { expression.value = it },
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = false,
            maxLines = 10,
            cursorBrush = Brush.horizontalGradient(listOf(Color.Black, Color.Black, Color.Black))
        )

        Button(
            onClick = {
                val listOfExpressions = expression.value.lines()
                viewModel.evaluateExpressions(listOfExpressions)
            }
        ) {
            Text(text = stringResource(id = R.string.evaluate_button_text))
        }

        Text(text = "Result")

        Text(
            text = result.value
        )
    }
}
