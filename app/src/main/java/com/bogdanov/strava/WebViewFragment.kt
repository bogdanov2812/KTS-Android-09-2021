package com.bogdanov.strava

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bogdanov.strava.databinding.FragmentWebViewBinding
import timber.log.Timber

class WebViewFragment : Fragment(R.layout.fragment_web_view) {

    private val args: WebViewFragmentArgs by navArgs()

    private val binding: FragmentWebViewBinding by viewBinding()

    @SuppressLint("SetJavaScriptEnabled", "ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webview.loadUrl("https://www.strava.com/athletes/" + args.userId)
        binding.webview.settings.javaScriptEnabled = true

    }

}