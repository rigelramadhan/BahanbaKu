package com.bangkit.bahanbaku.ui.snapfood.result

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.bahanbaku.databinding.ActivitySnapFoodResultBinding
import com.bangkit.bahanbaku.utils.AppExecutors
import com.bangkit.bahanbaku.utils.reduceFileImage
import com.bangkit.bahanbaku.utils.rotateBitmap
import java.io.File
import java.io.FileOutputStream

class SnapFoodResultActivity : AppCompatActivity() {

    private val binding: ActivitySnapFoodResultBinding by lazy {
        ActivitySnapFoodResultBinding.inflate(layoutInflater)
    }

    private val appExecutor: AppExecutors by lazy {
        AppExecutors()
    }

    private var file: File? = null
    private var isBack: Boolean = true
    private var compressingDone: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bindResult()
    }

    private fun bindResult() {
        file = intent.getSerializableExtra(EXTRA_PICTURE) as File
        isBack = intent.getBooleanExtra(EXTRA_IS_BACK_CAMERA, true)

        val result = rotateBitmap(BitmapFactory.decodeFile((file as File).path), isBack)
        result.compress(Bitmap.CompressFormat.JPEG, 100, FileOutputStream(file))

        appExecutor.diskIO.execute {
            file = reduceFileImage(file as File)
            compressingDone = true
        }

        binding.imgSnapFood.setImageBitmap(result)
    }

    companion object {
        const val EXTRA_PICTURE = "extra_picture"
        const val EXTRA_IS_BACK_CAMERA = "extra_is_back_camera"
    }
}