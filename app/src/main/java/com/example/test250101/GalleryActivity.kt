package com.example.test250101

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test250101.databinding.ActivityGalleryBinding
import com.example.test250101.databinding.ActivityTest11Binding

class GalleryActivity : AppCompatActivity() {
    lateinit var binding:ActivityGalleryBinding
    lateinit var requestGalleryLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            try{
                val calRatio = calculateInSampleSize(it!!.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize))
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                var inputStream = contentResolver.openInputStream(it!!.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null
                bitmap?.let{
                    binding.galleryResult.setImageBitmap(bitmap)
                }?:let{
                    Log.d("amrs","bitmap null")
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

        binding.gallerybtn1.setOnClickListener {
            val intent:Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type="image/*"
            requestGalleryLauncher.launch(intent)
        }
    }

    private fun calculateInSampleSize(fileUri: Uri, reqWidth:Int, reqHeight:Int): Int{
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true

        try{
            var inputStream = contentResolver.openInputStream(fileUri)
            BitmapFactory.decodeStream(inputStream, /* outPadding */ null, options)
            inputStream!!.close()
            inputStream = null
        }catch (e:Exception){
            e.printStackTrace()
        }

        val (height:Int, width:Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if(height>reqHeight || width > reqWidth){
            val halfHeight:Int = height/2
            val halfWidth:Int = width/2
            while((halfHeight / inSampleSize) >= reqHeight &&
                (halfWidth / inSampleSize) >= reqWidth){
                inSampleSize*=2
            }
        }
        return inSampleSize
    }
}