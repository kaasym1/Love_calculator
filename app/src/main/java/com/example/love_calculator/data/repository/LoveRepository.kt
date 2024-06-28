package com.example.love_calculator.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.love_calculator.data.model.LoveModel
import com.example.love_calculator.data.retrofit.RetrofitService
import com.example.love_calculator.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoveRepository {

    private val api = RetrofitService().api
    private val lovePercentageLv = MutableLiveData<LoveModel>()
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun getLovePercentage(firstName: String, secondName: String): MutableLiveData<LoveModel> {
        api.getPercentage(
            apiKey = Constants.API_KEY,
            host = Constants.HOST,
            firstName = firstName,
            secondName = secondName
        ).enqueue(object :
            Callback<LoveModel> {
            override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                if (response.isSuccessful && response.body() != null) {
                    lovePercentageLv.postValue(response.body())
                    loading.value = false
                }
            }

            override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                error.value = t.localizedMessage
                loading.value = false
            }
        })
        return lovePercentageLv
    }
}