package com.bangkit.bahanbaku.ui.camera

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.databinding.ActivityCameraBinding
import com.bangkit.bahanbaku.ui.login.LoginActivity
import com.bangkit.bahanbaku.ui.snapfood.result.SnapFoodResultActivity
import com.bangkit.bahanbaku.utils.Result
import com.bangkit.bahanbaku.utils.createFile
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@AndroidEntryPoint
class CameraActivity : AppCompatActivity() {

    private val binding: ActivityCameraBinding by lazy {
        ActivityCameraBinding.inflate(layoutInflater)
    }

    private val viewModel: CameraViewModel by viewModels()

    private var imageCapture: ImageCapture? = null
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        cameraExecutor = Executors.newSingleThreadExecutor()
        setupView()
    }

    private fun setupView() {
        binding.btnTakeSnap.setOnClickListener {
            takePhoto()
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()
        startCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun startCamera() {
        val cameraProvideFuture = ProcessCameraProvider.getInstance(this)

        cameraProvideFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProvideFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.failed_open_camera), Toast.LENGTH_SHORT)
                    .show()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return
        val photoFile = createFile(application)

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    viewModel.getToken().observe(this@CameraActivity) {
                        if (it.length < 5) {
                            backToLoginPage()
                        } else {
                            postSnapFood(it, photoFile)
                        }
                    }
                }

                override fun onError(exception: ImageCaptureException) {

                }
            }
        )
    }

    private fun backToLoginPage() {
        val intent = Intent(this@CameraActivity, LoginActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    private fun postSnapFood(token: String, photoFile: File) {
        viewModel.postSnapFood(token, photoFile)
            .observe(this@CameraActivity) { result ->
                when (result) {
                    is Result.Error -> {}
                    is Result.Loading -> {}
                    is Result.Success -> {
                        val intent = Intent()
                        intent.putExtra(SnapFoodResultActivity.EXTRA_PICTURE, photoFile)
                        intent.putExtra(
                            SnapFoodResultActivity.EXTRA_IS_BACK_CAMERA,
                            cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA
                        )
                        startActivity(intent)
                        finish()
                    }
                }
            }
    }
}