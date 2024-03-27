package com.vostrikov.flowsummation.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vostrikov.flowsummation.R
import com.vostrikov.flowsummation.data.FlowRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FlowViewModel(private val application: Application) : AndroidViewModel(application) {

    private val repository = FlowRepository

    private var jobSummation: Job? = null

    private val _summationResult = MutableStateFlow("")
    val summationResult = _summationResult.asStateFlow()

    fun launchSummation(inputNumber: String) {
        if (!checkNumberIsInt(inputNumber)) {
            return
        }
        resetPreviousJob()

        jobSummation = viewModelScope.launch {
            var sum = 0
            repository.getFlowOfNumbers(inputNumber.toInt())
                .onStart {
                    _summationResult.value =
                        _summationResult.value + application.getString(
                            R.string.summation_result_input_number,
                            inputNumber
                        ) + application.getString(R.string.summation_result_summation)
                }
                .onEach { sum += it }
                .onCompletion {
                    _summationResult.value =
                        _summationResult.value + application.getString(R.string.summation_result_end_of_summation)
                }
                .collect {
                    _summationResult.value =
                        application.getString(R.string.summation_result_divider, _summationResult.value, sum.toString())
                }
        }
    }

    private fun resetPreviousJob() {
        jobSummation?.let { job ->
            if (job.isActive) {
                job.cancel()
            }
        }
    }

    private fun checkNumberIsInt(inputNumber: String): Boolean {
        return try {
            inputNumber.toInt()
            true
        } catch (e: NumberFormatException) {
            false
            //TODO("add observing wrong numbers")
        }
    }

}