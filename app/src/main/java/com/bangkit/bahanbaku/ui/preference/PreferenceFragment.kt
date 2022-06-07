package com.bangkit.bahanbaku.ui.preference

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.ui.profile.edit.EditProfileActivity

class PreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            KEY_EDIT_PROFILE -> {
                val intent = Intent(requireContext(), EditProfileActivity::class.java)
                startActivity(intent)
            }

            DARK_MODE -> {
                Toast.makeText(requireContext(), "This feature is in progress", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onPreferenceTreeClick(preference)
    }

    companion object {
        private const val KEY_EDIT_PROFILE = "key_edit_profile"
        private const val DARK_MODE = "key_dark_mode"
    }
}