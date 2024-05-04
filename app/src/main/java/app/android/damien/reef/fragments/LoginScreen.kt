package app.android.damien.reef.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.R
import app.android.damien.reef.databinding.FragmentLoginScreenBinding
import app.android.damien.reef.model.login.LoginRequest
import app.android.damien.reef.model.addUserResponseBody
import app.android.damien.reef.retrofit.ApiClient
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
            Constants.APEX -> { binding.loginPageHeading.text = getString(R.string.login_screen_heading, "Apex") }

            Constants.FOCUSTRONIC -> { binding.loginPageHeading.text = getString(R.string.login_screen_heading, "Focustronic") }
        }

        binding.submit.setOnClickListener {
            var x = 0

            binding.usernameLayout.error = null
            binding.passwordLayout.error = null

            if (binding.usernameInputField.text.toString().isEmpty()) {
                x++
                binding.usernameLayout.error = "Username is required"
                Log.d("LoginScreen", "username or password is empty")
            }

            if (binding.passwordInputField.text.toString().isEmpty()) {
                x++
                binding.passwordLayout.error = "Password is required"
                Log.d("LoginScreen", "username or password is empty")
            }

            if (x == 0) {
                if (widgetType == Constants.APEX) {
                    val apiClient = ApiClient.apiService.addApexAccount(
                        SharedPreferences.read("nickname", "").toString(),
                        LoginRequest(
                            binding.usernameInputField.text.toString(),
                            binding.passwordInputField.text.toString()
                        )
                    )

                    apiClient.enqueue(object : Callback<addUserResponseBody> {
                        override fun onResponse(
                            call: Call<addUserResponseBody>,
                            response: Response<addUserResponseBody>
                        ) {
                            if (response.isSuccessful) {
                                saveCredentials(
                                    binding.usernameInputField.text.toString(),
                                    binding.passwordInputField.text.toString()
                                )
                                findNavController().navigate(R.id.action_loginScreen_to_apexSelectWidgetScreen)
                            } else {
                                Toast.showSnackbar(binding.root, "Failed to add user")
                            }
                        }

                        override fun onFailure(call: Call<addUserResponseBody>, t: Throwable) {
                            Toast.showSnackbar(binding.root, "Failed to add user")
                        }
                    })
                } else if (widgetType == Constants.FOCUSTRONIC) {
                    val apiClient = ApiClient.apiService.addFocustronicAccount(
                        SharedPreferences.read("nickname", "").toString(),
                        AddFocustronicUserRequest(
                            binding.usernameInputField.text.toString(),
                            binding.passwordInputField.text.toString()
                        )
                    )
                    apiClient.enqueue(object : Callback<AddFocustronicUserResponse> {
                        override fun onResponse(
                            call: Call<AddFocustronicUserResponse>,
                            response: Response<AddFocustronicUserResponse>
                        ) {
                            if (response.isSuccessful) {
                                saveCredentials(
                                    binding.usernameInputField.text.toString(),
                                    binding.passwordInputField.text.toString()
                                )
                                findNavController().navigate(R.id.action_loginScreen_to_focustronicSelectWidgetScreen)
                            } else {
                                Toast.showSnackbar(binding.root, "Failed to add user")
                            }
                        }

                        override fun onFailure(call: Call<AddFocustronicUserResponse>, t: Throwable) {
                            Toast.showSnackbar(binding.root, "Failed to add user")
                        }
                    })
                }
            }
        }

        return binding.root
    }

    private fun saveCredentials(username: String, password: String) {
        SharedPreferences.write(widgetType.toString() + "username", username)
        SharedPreferences.write(widgetType.toString() + "password", password)
    }
}