package com.maxkor.interonnection.data.retrofit

data class CoinResponse(
    val status: String,
    val data: CoinData
) {
    data class CoinData(
//      val stats
        val coins: List<DataModelDto>
    )

}

