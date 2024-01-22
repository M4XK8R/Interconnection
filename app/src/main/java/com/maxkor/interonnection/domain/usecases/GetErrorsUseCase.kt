package com.maxkor.interonnection.domain.usecases

import androidx.compose.runtime.State
import com.maxkor.interonnection.domain.repository.MainRepository
import javax.inject.Inject

class GetErrorsUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke(): State<String> {
        return repository.errorMsg
    }

}