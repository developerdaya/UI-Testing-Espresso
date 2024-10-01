package com.developerdaya.ui_testing_espresso

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.developerdaya.ui_testing_espresso.databinding.ActivityMainBinding
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()
            if (!isValidPassword(password))
            {
                binding.etPassword.error = "Password must be at least 8 characters and include one uppercase letter, one lowercase letter, one number, and one special character."
            } else if (password != confirmPassword)
            {
                binding.etConfirmPassword.error = "Passwords do not match"
            } else {
                startActivity(Intent(this,Screen2Activity::class.java))
            }
        }
    }

    fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+$).{8,}$"
        val pattern = Pattern.compile(passwordPattern)
        return pattern.matcher(password).matches()
    }

}
