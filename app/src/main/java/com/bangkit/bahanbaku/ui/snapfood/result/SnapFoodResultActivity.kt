package com.bangkit.bahanbaku.ui.snapfood.result

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.bahanbaku.adapter.SnapFoodResultAdapter
import com.bangkit.bahanbaku.databinding.ActivitySnapFoodResultBinding
import com.bangkit.bahanbaku.ui.login.LoginActivity
import com.bangkit.bahanbaku.utils.AppExecutors
import com.bangkit.bahanbaku.utils.Result
import com.bangkit.bahanbaku.utils.reduceFileImage
import com.bangkit.bahanbaku.utils.rotateBitmap
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class SnapFoodResultActivity : AppCompatActivity() {

    private val binding: ActivitySnapFoodResultBinding by lazy {
        ActivitySnapFoodResultBinding.inflate(layoutInflater)
    }

    private val viewModel: SnapFoodResultViewModel by viewModels()

    private val appExecutor: AppExecutors by lazy {
        AppExecutors()
    }

    private var file: File? = null
    private var isBack: Boolean = true
    private var compressingDone: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bindResult()
    }

    private fun bindResult() {
        binding.progressBar.isVisible = true
        file = intent.getSerializableExtra(EXTRA_PICTURE) as File
        isBack = intent.getBooleanExtra(EXTRA_IS_BACK_CAMERA, true)

        val result = rotateBitmap(BitmapFactory.decodeFile((file as File).path), isBack)
        result.compress(Bitmap.CompressFormat.JPEG, 100, FileOutputStream(file))

        appExecutor.diskIO.execute {
            file = reduceFileImage(file as File)
            compressingDone.postValue(true)
        }

        compressingDone.observe(this) { done ->
            if (done) {
                file?.let { loadFoodResult(it) }
            }
        }
        binding.imgSnapFood.setImageBitmap(result)
    }

    private fun loadFoodResult(file: File) {
        viewModel.getToken().observe(this) {
            if (it == "null") {
                backToLoginPage()
            } else {
                val token = "Bearer $it"
                getResults(token, file)
            }
        }
    }

    private fun getResults(token: String, file: File) {
        viewModel.snapFood(token, file).observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Result.Error -> {
                    binding.progressBar.isVisible = false
                    val error = result.error
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                }
                is Result.Success -> {
                    binding.progressBar.isVisible = false
                    val data = result.data.results
                    binding.rvFoods.apply {
                        adapter = SnapFoodResultAdapter(data)
                        layoutManager = LinearLayoutManager(this@SnapFoodResultActivity)
                    }
                }
            }
        }
    }

    private fun backToLoginPage() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    companion object {
        const val EXTRA_PICTURE = "extra_picture"
        const val EXTRA_IS_BACK_CAMERA = "extra_is_back_camera"
    }
}