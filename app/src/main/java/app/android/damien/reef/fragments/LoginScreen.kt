package app.android.damien.reef.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresExtension
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.R
import app.android.damien.reef.databinding.FragmentLoginScreenBinding
import app.android.damien.reef.model.addUserRequestBody
import app.android.damien.reef.model.addUserResponseBody
import app.android.damien.reef.retrofit.ApiClient
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern
import kotlin.properties.Delegates


class LoginScreen : Fragment() {

    private val binding by lazy {
        FragmentLoginScreenBinding.inflate(layoutInflater)
    }

    private var widgetType = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        widgetType = arguments?.getInt("widgetType") ?: 0

        when (arguments?.getInt("widgetType")) {
            Constants.APEX -> {
                binding.loginPageHeading.text = getString(R.string.login_screen_heading, "Apex")
            }

            Constants.FOCUSTRONIC -> {
                binding.loginPageHeading.text =
                    getString(R.string.login_screen_heading, "Focustronic")
            }
        }

        binding.submit.setOnClickListener {
            var x = 0

            binding.emailLayout.error = null
            binding.nicknameLayout.error = null

            if (binding.emailInputField.text.toString().isEmpty()) {
                x++
                binding.emailLayout.error = "Email is required"
                Log.d("LoginScreen", "Email or nickname is empty")
            } else {
                if (Patterns.EMAIL_ADDRESS.matcher(binding.emailInputField.text.toString())
                        .matches()
                ) {
                    Log.d("LoginScreen", "Email is valid")
                } else {
                    x++
                    Log.d("LoginScreen", "Email is invalid")
                    binding.emailLayout.error = "Invalid email"
                }
            }

            if (binding.nicknameInputField.text.toString().isEmpty()) {
                x++
                binding.nicknameLayout.error = "Nickname is required"
                Log.d("LoginScreen", "Email or nickname is empty")
            } else {
                if (Pattern.matches("^[a-zA-Z0-9]*$", binding.nicknameInputField.text.toString())) {
                    Log.d("LoginScreen", "Nickname is valid")
                } else {
                    x++
                    Log.d("LoginScreen", "Nickname is invalid")
                    binding.nicknameLayout.error = "Nickname can only contains letters and numbers."
                }
            }

            if (x == 0) {
                saveCredentials(
                    binding.emailInputField.text.toString(),
                    binding.nicknameInputField.text.toString()
                )

                if (widgetType == Constants.APEX) {
                    findNavController().navigate(R.id.action_loginScreen_to_apexSelectWidgetScreen)
                } else if (widgetType == Constants.FOCUSTRONIC) {
                    findNavController().navigate(R.id.action_loginScreen_to_focustronicSelectWidgetScreen)
                }
            }
        }

        return binding.root
    }

    private fun saveCredentials(email: String, nickname: String) {
        SharedPreferences.write(widgetType.toString() + "email", email)
        SharedPreferences.write(widgetType.toString() + "nickname", nickname)
    }
}