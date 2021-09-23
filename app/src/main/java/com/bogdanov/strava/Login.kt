package com.bogdanov.strava

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.get
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class LoginPage : Fragment(R.layout.fragment_login) {

    private val viewModel : LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginButton = view.findViewById<Button>(R.id.login)

        val emailTextView = view.findViewById<TextInputLayout>(R.id.email)

        val passwordTextView = view.findViewById<TextInputLayout>(R.id.password)

        viewModel.email.observe(viewLifecycleOwner,{ email ->
            if (viewModel.editEmail(emailTextView.editText?.text.toString()))
                emailTextView.editText?.setText(email)
        })

        viewModel.password.observe(viewLifecycleOwner,{ password ->
            if (viewModel.editPassword(passwordTextView.editText?.text.toString()))
                passwordTextView.editText?.setText(password)
        })

        viewModel.loginVisible(emailTextView.editText?.text.toString(),passwordTextView.editText?.text.toString())


        passwordTextView.editText?.doOnTextChanged{ passwordText, _, _, _ ->
            viewModel.savePassword(passwordText.toString())
            viewModel.loginVisible(emailTextView.editText?.text.toString(),passwordText.toString())

            if (passwordText!!.length < 8)
                passwordTextView.error = getString(R.string.passwordTextView_error)
            else
                passwordTextView.error = null

        }


        emailTextView.editText?.doOnTextChanged { emailText, _, _, _ ->
            viewModel.saveEmail(emailText.toString())
            viewModel.loginVisible(emailText.toString(),passwordTextView.editText?.text.toString())

        }

        viewModel.login.observe(viewLifecycleOwner,{
            loginButton.isEnabled = it
        })


        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginPage_to_mainPage)
        }
    }
}