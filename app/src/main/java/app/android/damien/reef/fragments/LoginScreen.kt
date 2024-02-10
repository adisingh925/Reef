package app.android.damien.reef.fragments

import android.net.http.NetworkException
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresExtension
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.R
import app.android.damien.reef.databinding.FragmentLoginScreenBinding
import app.android.damien.reef.model.addUserRequestBody
import app.android.damien.reef.model.addUserResponseBody
import app.android.damien.reef.retrofit.ApiClient
import app.android.damien.reef.utils.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.regex.Pattern


class LoginScreen : Fragment() {

    private val binding by lazy {
        FragmentLoginScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding.submit.setOnClickListener {
            var x = 0

            binding.emailLayout.error = null
            binding.nicknameLayout.error = null

            if (binding.emailInputField.text.toString()
                    .isEmpty() || binding.nicknameInputField.text.toString().isEmpty()
            ) {
                x++
                Log.d("LoginScreen", "Email or nickname is empty")
                return@setOnClickListener
            }

            if (Patterns.EMAIL_ADDRESS.matcher(binding.emailInputField.text.toString()).matches()) {
                Log.d("LoginScreen", "Email is valid")
            } else {
                x++
                Log.d("LoginScreen", "Email is invalid")
                binding.emailLayout.error = "Invalid email"
                return@setOnClickListener
            }

            if (x == 0) {
                val call = ApiClient.apiService.addUser(
                    addUserRequestBody(
                        binding.emailInputField.text.toString(),
                        binding.nicknameInputField.text.toString()
                    )
                )

                call.enqueue(object : Callback<addUserResponseBody> {
                    override fun onResponse(
                        call: Call<addUserResponseBody>,
                        response: Response<addUserResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            val post = response.body()
                            if (post != null) {
                                if(post.success){
                                    findNavController().navigate(R.id.action_loginScreen_to_addWidgetScreen)
                                }else{
                                    Toast.showSnackbar(binding.root, "Signup Failed!")
                                }
                            }
                            Log.d("LoginScreen", "Response: $post")
                        } else {
                            Toast.showSnackbar(binding.root, "Signup Failed!")
                            Log.d("LoginScreen", "Error: ${response.code()}")
                        }
                    }

                    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
                    override fun onFailure(call: Call<addUserResponseBody>, t: Throwable) {
                        if (t is java.net.SocketException ||
                            t is java.net.UnknownHostException ||
                            t is java.net.ConnectException ||
                            t is java.io.IOException
                        ) {
                            Toast.showSnackbar(binding.root, "Network Error!")
                        }
                        Log.d("LoginScreen", "Error: ${t.message}")
                    }
                })
            }
        }

        return binding.root
    }
}