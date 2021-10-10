package com.bogdanov.strava.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bogdanov.strava.R
import com.bogdanov.strava.databinding.FragmentLoginBinding

class LoginPageFragment : Fragment(R.layout.fragment_login) {

    private val viewModel : LoginPageViewModel by viewModels()
    private val binding : FragmentLoginBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.email.editText?.setText(viewModel.state.value?.email)
        binding.password.editText?.setText(viewModel.state.value?.password)

        binding.email.editText?.doOnTextChanged { emailText, _, _, _ ->
            viewModel.updateStatus(emailText.toString(),binding.password.editText?.text.toString())
        }

        binding.password.editText?.doOnTextChanged { passwordText, _, _, _ ->
            viewModel.updateStatus(binding.email.editText?.text.toString(), passwordText.toString())
            viewModel.setError(passwordText.toString(), getString(R.string.passwordTextView_error))
        }

        viewModel.state.observe(viewLifecycleOwner, { state ->
            binding.login.isEnabled = state.buttonEnableStatus
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {errorMessage ->
            binding.password.error = errorMessage
        })

        binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_loginPage_to_mainPage)
        }
    }
}