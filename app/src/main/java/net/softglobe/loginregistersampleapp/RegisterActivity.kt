package net.softglobe.loginregistersampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import net.softglobe.loginregistersampleapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        super.onCreate(savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val name = binding.etName.text.toString()

            lifecycleScope.launch {
                try {
                    val response = RetrofitInstance.api.registerUser(User(name, email, password))
                    if (response.isSuccessful && response.body() != null) {
                        Toast.makeText(
                            this@RegisterActivity,
                            response.body()!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Something went wrong. Please try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e : Exception) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Something went wrong. Please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}