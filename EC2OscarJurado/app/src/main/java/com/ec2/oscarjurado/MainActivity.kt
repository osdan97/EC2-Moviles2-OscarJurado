package com.ec2.oscarjurado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ec2.oscarjurado.databinding.ActivityLoginBinding
import com.ec2.oscarjurado.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        binding.btnCamara.setOnClickListener {
            val intent = Intent(this, TakePhotoActivity::class.java)
            startActivity(intent)
        }
        binding.btnMapa.setOnClickListener {
            val intent = Intent(this, ShowMapActivity::class.java)
            startActivity(intent)
        }
    }

}