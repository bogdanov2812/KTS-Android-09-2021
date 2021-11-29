package com.bogdanov.strava.presentation.web_view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bogdanov.strava.R
import com.bogdanov.strava.databinding.FragmentWebViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class WebViewFragment : Fragment(R.layout.fragment_web_view) {

    private val args: WebViewFragmentArgs by navArgs()

    private val binding: FragmentWebViewBinding by viewBinding()

    @SuppressLint("SetJavaScriptEnabled", "ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()

        binding.webview.loadUrl("https://www.strava.com/athletes/" + args.userId)
        binding.webview.settings.javaScriptEnabled = true

    }

    private fun initToolbar() = with(binding.appBar) {
        toolbar.setTitle(R.string.web_view)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

}