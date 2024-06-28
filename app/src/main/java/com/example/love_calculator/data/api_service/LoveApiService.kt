package com.example.love_calculator.data.api_service

import com.example.love_calculator.data.model.LoveModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LoveApiService {

    //https://love-calculator.p.rapidapi.com/getPercentage?sname=Alice&fname=John
    @GET("getPercentage")
    fun getPercentage(
        @Header("X-RapidAPI-Key") apiKey: String = "31349f87c1msh941e3b50ff85859p191fc0jsn6a217a151fa6",
        @Header("X-RapidAPI-Host") host: String = "love-calculator.p.rapidapi.com",
        @Query("fname") firstName: String,
        @Query("sname") secondName: String,

        ): Call<LoveModel>

}