package com.bogdanov.strava

import android.content.Context
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
import timber.log.Timber


class LoginPage : Fragment(R.layout.fragment_login) {

    private val viewModel : LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginButton = view.findViewById<Button>(R.id.login)

        val emailTextView = view.findViewById<TextInputLayout>(R.id.email)

        val passwordTextView = view.findViewById<TextInputLayout>(R.id.password)


        passwordTextView.editText?.doOnTextChanged{ passwordText, _, _, _ ->
            viewModel.updateStatus(emailTextView.editText?.text.toString(),passwordText.toString())

            if (passwordText!!.length < 8)
                passwordTextView.error = getString(R.string.passwordTextView_error)
            else
                passwordTextView.error = null

        }


        emailTextView.editText?.doOnTextChanged { emailText, _, _, _ ->
            viewModel.updateStatus(emailText.toString(),passwordTextView.editText?.text.toString())

        }

        viewModel.state.observe(viewLifecycleOwner, { state ->

            if (emailTextView.editText?.text.toString()!=state.email){
                emailTextView.editText?.setText(state.email)
            }

            if (passwordTextView.editText?.text.toString()!=state.password){
                passwordTextView.editText?.setText(state.password)
            }

            loginButton.isEnabled = state.buttonEnableStatus

        })

        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginPage_to_mainPage)
        }
    }

}