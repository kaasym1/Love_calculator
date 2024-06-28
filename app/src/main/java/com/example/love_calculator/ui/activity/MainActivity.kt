package com.example.love_calculator.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.love_calculator.R
import com.example.love_calculator.data.model.LoveModel
import com.example.love_calculator.data.retrofit.RetrofitService
import com.example.love_calculator.databinding.ActivityMainBinding
import com.example.love_calculator.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnCalculate.setOnClickListener {
            val firstName = binding.etFname.text.toString()
            val secondName = binding.etSname.text.toString()

            if (firstName.isNotEmpty() && secondName.isNotEmpty()) {
                initClickers(firstName, secondName)
            }
        }
    }
    private fun initClickers(firstName: String, secondName: String) {
        val loveApi = RetrofitService().api

        loveApi.getPercentage(Constants.API_KEY,Constants.HOST,firstName,secondName).enqueue(object :
            Callback<LoveModel> {
            override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                if (response.isSuccessful){
                    val loveModel = response.body()
                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    intent.putExtra(ARG_FIRST_NAME, firstName)
                    intent.putExtra(ARG_SECOND_NAME, secondName)
                    intent.putExtra(ARG_PERCENTAGE, loveModel?.percentage)
                    intent.putExtra(ARG_RESULT, loveModel?.result)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<LoveModel>, t: Throwable) {
            }
        })
    }
}
const val ARG_FIRST_NAME = "firstName"
const val ARG_SECOND_NAME = "secondName"
const val ARG_PERCENTAGE = "percentage"
const val ARG_RESULT = "result"