package com.ec2.oscarjurado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.ec2.oscarjurado.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

        binding.edtPass.editText?.addTextChangedListener { text->
           binding.btnGoLogin.isEnabled= validateEmailPassword(text.toString(),binding.edtPass.editText?.text.toString())
        }
        binding.edtEmail.editText?.addTextChangedListener { text->
            binding.btnGoLogin.isEnabled=validateEmailPassword(binding.edtEmail.editText?.text.toString(),text.toString())

        }

        binding.btnGoLogin.setOnClickListener{

            val username=binding.edtEmail.editText?.text.toString();
            val password=binding.edtPass.editText?.text.toString();
            if (isValidCredentials(username, password)) {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Mostrar mensaje de error
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }

        }


    }
    private fun validateEmailPassword(email:String,password:String):Boolean{

        var validateEmail=email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        var validatePassword=password.length>=6
        return validateEmail && validatePassword
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        return (username == "ejemplo@idat.edu.pe" && password == "123456")
    }
}