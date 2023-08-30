package com.example.mathsapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mathsapp.data.model.ApiResult
import com.example.mathsapp.data.model.ResultExpr
import com.example.mathsapp.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    /** Keeps the value of evaluated answer as a State which is used to display result to users. */
    val evaluatedAnswers: MutableState<String> = mutableStateOf("")

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData("")

    /** [LiveData] for the errors received in API responses. Helps showing toast to the users. */
    val errorLiveData: LiveData<String> = _errorLiveData

    /** For keeping the list of previous expression evaluations fetched from database. */
    val results: MutableState<List<ResultExpr>> = mutableStateOf(emptyList())

    init {
        refreshList()
    }

    fun evaluateExpressions(exprList: List<String>) {
        viewModelScope.launch {
            for (expr in exprList) {
                when (val response = repository.fetchEvaluatedExpression(expr)) {
                    is ApiResult.Success -> {
                        val exprAndAnswerString = expr + "=>" + response.result
                        evaluatedAnswers.value = evaluatedAnswers.value + "\n" + exprAndAnswerString
                        insertIntoDataBase(exprAndAnswerString)
                    }

                    is ApiResult.Error -> {
                        val exprAndErrorString = expr + "=>" + response.error
                        evaluatedAnswers.value = evaluatedAnswers.value + "\n" + exprAndErrorString
                        _errorLiveData.value = response.error
                    }
                }
            }
        }

    }

    private fun insertIntoDataBase(exprAndAnswerString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val dateAndTime = Calendar.getInstance().time.time
            repository.insertResult(
                ResultExpr(
                    value = exprAndAnswerString,
                    dateAndTime = dateAndTime
                )
            )
            refreshList()
        }
    }

    private fun refreshList() {
        viewModelScope.launch(Dispatchers.IO) {
            results.value = repository.getResults()
        }
    }
}

