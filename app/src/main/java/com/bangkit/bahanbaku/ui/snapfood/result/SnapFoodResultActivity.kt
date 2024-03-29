package com.bangkit.bahanbaku.ui.snapfood.result

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.adapter.SnapFoodResultAdapter
import com.bangkit.bahanbaku.databinding.ActivitySnapFoodResultBinding
import com.bangkit.bahanbaku.ui.login.LoginActivity
import com.bangkit.bahanbaku.utils.AppExecutors
import com.bangkit.bahanbaku.utils.Result
import com.bangkit.bahanbaku.utils.reduceFileImage
import com.bangkit.bahanbaku.utils.rotateBitmap
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bindResult()
    }

    private fun bindResult() {
        binding.progressBar.isVisible = true
        file = intent.getSerializableExtra(EXTRA_PICTURE) as File
        isBack = intent.getBooleanExtra(EXTRA_IS_BACK_CAMERA, true)
        val isGallery = intent.getBooleanExtra(EXTRA_IS_FROM_GALLERY, false)

        binding.tvStatus.text = getString(R.string.compressing_image)
        binding.tvStatus.isVisible = true

        var result = BitmapFactory.decodeFile((file as File).path)
        if (!isGallery) {
            result = rotateBitmap(result, isBack)
        }

        appExecutor.diskIO.execute {
            file = reduceFileImage(file as File)
            compressingDone.postValue(true)
        }

        compressingDone.observe(this) { done ->
            if (done) {
                file?.let { loadFoodResult(it) }
                binding.imgSnapFood.setImageBitmap(result)
            }
        }

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
                    binding.tvStatus.text = getString(R.string.our_machine_is_detecting_the_food)
                    binding.tvStatus.isVisible = true
                    binding.imgSnapfoodStatus.setImageResource(R.drawable.ic_illustration_thinking)
                }
                is Result.Error -> {
                    binding.progressBar.isVisible = false
                    binding.tvStatus.isVisible = false
                    binding.imgSnapfoodStatus.setImageResource(R.drawable.ic_illustration_something_went_wrong)
                    binding.imgSnapfoodStatus.isVisible = true

                    val error = result.error
                    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                }
                is Result.Success -> {
                    binding.progressBar.isVisible = false
                    binding.tvStatus.isVisible = false
                    val data = result.data.results

                    binding.imgSnapfoodStatus.isVisible = data.isEmpty()

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_PICTURE = "extra_picture"
        const val EXTRA_IS_FROM_GALLERY = "extra_is_from_gallery"
        const val EXTRA_IS_BACK_CAMERA = "extra_is_back_camera"
    }
}