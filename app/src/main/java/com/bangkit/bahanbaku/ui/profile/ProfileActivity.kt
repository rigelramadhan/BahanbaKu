package com.bangkit.bahanbaku.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.databinding.ActivityProfileBinding
import com.bangkit.bahanbaku.ui.login.LoginActivity
import com.bangkit.bahanbaku.ui.preference.PreferenceFragment
import com.bangkit.bahanbaku.utils.AppExecutors
import com.bangkit.bahanbaku.utils.Result
import com.bangkit.bahanbaku.utils.fromUriToFile
import com.bangkit.bahanbaku.utils.reduceFileImage
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import kotlin.random.Random

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    private val binding: ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    private val appExecutor: AppExecutors by lazy {
        AppExecutors()
    }

    private var compressingDone: MutableLiveData<Boolean> = MutableLiveData(false)

    private val viewModel: ProfileViewModel by viewModels()
    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction().add(R.id.fragment_settings, PreferenceFragment())
            .commit()

    }

    private fun getToken() {
        viewModel.getToken().observe(this) {
            if (it.length <= 5) {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            } else {
                val token = "Bearer $it"
                this.token = token
                setupView()
            }
        }
    }

    private fun setupView() {
        binding.imgProfile.setOnClickListener {
            startGallery()
        }

        binding.btnLogout.setOnClickListener {
            viewModel.deleteToken()
        }

        loadProfile()
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, getString(R.string.import_picture))
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == Activity.RESULT_OK) {
            val selectedImage: Uri = activityResult.data?.data as Uri
            var myFile = fromUriToFile(selectedImage, this)

            binding.progressBar.isVisible = true

            appExecutor.diskIO.execute {
                myFile = reduceFileImage(myFile)
                compressingDone.postValue(true)
            }

            compressingDone.observe(this) { done ->
                if (done) {
                    uploadPicture(myFile)
                }
            }
        }
    }

    private fun uploadPicture(myFile: File) {
        token?.let {
            viewModel.uploadPicture(it, myFile).observe(this) { result ->
                when (result) {
                    is Result.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(
                            this,
                            getString(R.string.profile_picture_update_failed),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is Result.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is Result.Success -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(
                            this,
                            getString(R.string.profile_picture_updated),
                            Toast.LENGTH_SHORT
                        ).show()

                        loadProfile()
                    }
                }
            }
        }
    }

    private fun loadProfile() {
        if (token != null) {
            viewModel.getProfile(token as String).observe(this) { result ->
                when (result) {
                    is Result.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(
                            this,
                            getString(R.string.error_loading_profile),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is Result.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is Result.Success -> {
                        binding.progressBar.isVisible = false
                        val data = result.data
                        val profile = data.results

                        binding.tvEmailProfile.text = profile.email
                        binding.tvNameProfile.text = profile.username

                        Glide.with(this)
                            .load(profile.picture + "?rand=${Random(2000000)}")
                            .apply(
                                RequestOptions().signature(
                                    ObjectKey(
                                        System.currentTimeMillis().toString()
                                    )
                                )
                            )
                            .into(binding.imgProfile)

                        Glide.with(this)
                            .load(profile.picture + "?rand=${Random(2000000)}")
                            .into(binding.imgProfileBackground)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getToken()
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
}