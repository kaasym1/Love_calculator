package com.example.love_calculator.data.model

import com.google.gson.annotations.SerializedName

data class LoveModel (
    @SerializedName("fname")
    val firstName: String,
    @SerializedName("sname")
    val secondName: String,
    @SerializedName("percentage")
    val percentage :String,
    @SerializedName("result")
    val result :String
)