package com.maxkor.interonnection.data.retrofit

import com.squareup.moshi.Json

data class CoinInfo(
    @Json val time: Time,

    @Json(name = "chartName")
    val currency: String,

    @Json(name = "bpi")
    val info: Info
) {
    companion object {

        private const val INITIAL_VALUE = "???"
        private const val INITIAL_VALUE_NUMBER = 0f

        private val time = Time(updated = INITIAL_VALUE)
        private val currency: String = INITIAL_VALUE
        private val usd = Usd(
            name = INITIAL_VALUE,
            value = INITIAL_VALUE_NUMBER
        )
        private val info = Info(usd = usd)

        val coinInfoInitial = CoinInfo(
            time = time,
            currency = currency,
            info = info
        )
    }
}

data class Time(
    @Json
    val updated: String,
)

data class Info(
    @Json(name = "USD")
    val usd: Usd
)

data class Usd(
    @Json(name = "code")
    val name: String,

    @Json(name = "rate_float")
    val value: Float
)





