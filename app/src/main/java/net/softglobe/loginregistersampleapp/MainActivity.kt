package net.softglobe.loginregistersampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import net.softglobe.loginregistersampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            lifecycleScope.launch {
                try {
                    val response = RetrofitInstance.api.loginUser(email, password)
                    if (response.isSuccessful && response.body() != null) {
                        Toast.makeText(
                            this@MainActivity,
                            response.body()!!.result.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Something went wrong. Please try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e : Exception) {
                    Toast.makeText(
                        this@MainActivity,
                        "Something went wrong. Please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.txtRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}