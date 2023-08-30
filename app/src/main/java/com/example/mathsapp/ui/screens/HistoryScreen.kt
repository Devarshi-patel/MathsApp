package com.example.mathsapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mathsapp.data.model.ResultExpr
import java.text.DateFormat
import java.util.Locale

@Composable
fun HistoryScreen(resultList: List<ResultExpr>) {
    Column(
        modifier =  Modifier.fillMaxWidth().fillMaxHeight(0.95f).padding(15.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Expressions", modifier = Modifier.padding(15.dp))
            Text(text = "Date", modifier = Modifier.padding(15.dp))
        }

        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val listSize = resultList.size
            items(listSize) { index ->
                val resultItem = resultList[index]
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = resultItem.value,
                        modifier = Modifier.padding(5.dp).fillMaxWidth(0.5f),
                        softWrap = true
                    )
                    Text(text = getDate(resultItem.dateAndTime), modifier = Modifier.padding(5.dp))
                }
            }
        }
    }
}

fun getDate(dateTime: Long): String {
   return DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK).format(dateTime)
}
