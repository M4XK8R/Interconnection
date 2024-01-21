package com.maxkor.interonnection.domain.usecases

import com.maxkor.interonnection.domain.helpers.InternetChecker
import javax.inject.Inject

class CheckInternetUseCase @Inject constructor(
    private val internetChecker: InternetChecker
) {

    operator fun invoke() : Boolean {
       return internetChecker.isNetworkAvailable()
    }
}