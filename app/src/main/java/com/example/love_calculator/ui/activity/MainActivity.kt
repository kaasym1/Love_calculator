package com.example.love_calculator.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
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
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
    }

    private fun initClickers() = with(binding) {
        btnCalculate.setOnClickListener {
            viewModel.getLovePercentage(
                firstName = etFname.text.toString(),
                secondName = etSname.text.toString()
            ).observe(this@MainActivity) { data ->
                setupObservers(data)
            }
        }
    }

    private fun setupObservers(data: LoveModel) = with(viewModel) {
        startActivity(
            Intent(this@MainActivity, SecondActivity::class.java).apply {
                putExtra(ARG_FIRST_NAME, binding.etFname.text.toString())
                putExtra(ARG_SECOND_NAME, binding.etSname.text.toString())
                putExtra(ARG_PERCENTAGE, data.percentage)
                putExtra(ARG_RESULT, data.result)
            })
    }

    companion object {
        const val ARG_FIRST_NAME = "firstName"
        const val ARG_SECOND_NAME = "secondName"
        const val ARG_PERCENTAGE = "percentage"
        const val ARG_RESULT = "result"
    }
}