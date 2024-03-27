package com.vostrikov.flowsummation.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.merge

object FlowRepository {
    private const val DELAY_VALUE_IN_MS = 100L

    private suspend fun setDelay(index: Int): Int {
        delay((index + 1) * DELAY_VALUE_IN_MS)
        return index + 1
    }

    fun getFlowOfNumbers(number: Int): Flow<Int> {
        val listOfFlow = mutableListOf<Flow<Int>>()
        (0 until number).forEach { it ->
            listOfFlow.add(flow {
                emit(setDelay(it))
            })
        }
        return listOfFlow.merge().flowOn(Dispatchers.IO)
    }
}