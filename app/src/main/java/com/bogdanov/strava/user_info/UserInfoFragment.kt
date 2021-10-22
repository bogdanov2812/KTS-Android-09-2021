package com.bogdanov.strava.user_info

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bogdanov.strava.R
import com.bogdanov.strava.databinding.FragmentUserInfoBinding
import com.bogdanov.strava.datastore.SharedPrefs
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInfoFragment: Fragment(R.layout.fragment_user_info) {

    private val binding: FragmentUserInfoBinding by viewBinding()

    private val viewModel: UserInfoViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserInfo()

        viewModel.failToast.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        viewModel.userInfo.observe(viewLifecycleOwner,{
            with(binding){
                firstname.text = it?.firstname
                secondname.text = it?.lastname
                city.text = it?.city
                country.text = it?.country
                weight.text = String.format(getString(R.string.weight), it?.weight.toString())

                Glide.with(view)
                    .load(it?.avatar)
                    .placeholder(R.drawable.ic_baseline_image_24)
                    .into(avatar)
            }
        })

        binding.buttonLogout.setOnClickListener {
            with(SharedPrefs){
                authToken = null
                refreshToken = null
                currentUserId = null
                findNavController().navigate(R.id.action_userInfoFragment_to_authFragment)
            }

        }

    }

}