package com.ec2.oscarjurado

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.ec2.oscarjurado.databinding.ActivityMainBinding
import com.ec2.oscarjurado.databinding.ActivityTakePhotoBinding

class TakePhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTakePhotoBinding
    private lateinit var operaCameraLauncher:ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityTakePhotoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_take_photo)
        binding.btnTakePhoto.setOnClickListener {
        if (permissionValidated()){
            takePhoto()
        }

        operaCameraLauncher =registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->if(result.resultCode== RESULT_OK){
                val photoBitmap = result.data?.extras?.get("data") as Bitmap
            binding.image.setImageBitmap(photoBitmap)
        }
        }

    }
    }
    private fun permissionValidated():Boolean{
        val cameraPermission=ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
        val permissionList:MutableList<String> = mutableListOf()
        if(cameraPermission != PackageManager.PERMISSION_GRANTED  ){
            permissionList.add(android.Manifest.permission.CAMERA)
        }

        if(permissionList.isNotEmpty()){
            ActivityCompat.requestPermissions(this,permissionList.toTypedArray(),1000)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            1000->{
                if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){
                    takePhoto()
                }
            }
        }
    }


    private fun takePhoto(){

        val cameraIntent=Intent()
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE)
    }
}