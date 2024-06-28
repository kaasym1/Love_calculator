package com.example.love_calculator.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.love_calculator.data.model.LoveModel
import com.example.love_calculator.data.repository.LoveRepository

class MainViewModel:ViewModel() {

    private val repository = LoveRepository()

    // var lovePercentageLv = MutableLiveData<LoveModel>()
    fun getLovePercentage(firstName: String, secondName: String): LiveData<LoveModel> =
        repository.getLovePercentage(firstName, secondName)

}