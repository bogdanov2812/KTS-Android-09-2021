package com.bogdanov.strava.presentation.user_info

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bogdanov.strava.R
import com.bogdanov.strava.presentation.user_info.edit.UserEditInfoDialogFragment
import com.bogdanov.strava.databinding.FragmentUserInfoBinding
import com.bogdanov.strava.datastore.SharedPrefs
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInfoFragment: Fragment(R.layout.fragment_user_info) {

    private val binding: FragmentUserInfoBinding by viewBinding()

    private val viewModel: UserInfoViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserInfo()

        initButtonsControl()
        initToastObserver()
        initUserObserver()

        binding.buttonLogout.setOnClickListener {
            logoutAlertDialog()
        }

        binding.userInfoChange.setOnClickListener {
            showCustomInputDialogFragment()
        }

        setupCustomDialogFragmentListener()

    }

    private fun initUserObserver(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.userInfo.collect {
                with(binding){
                    firstname.text = it?.firstname
                    secondname.text = it?.lastname
                    city.text = it?.city
                    country.text = it?.country
                    weight.text = String.format(getString(R.string.weight), it?.weight.toString())

                    view?.let { it1 ->
                        Glide.with(it1)
                            .load(it?.avatar)
                            .placeholder(R.drawable.ic_baseline_image_24)
                            .into(avatar)
                    }
                }
            }
        }
    }

    private fun initToastObserver(){
        viewModel.actionToast.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    private fun showCustomInputDialogFragment() {
        UserEditInfoDialogFragment.show(
            parentFragmentManager,
            viewModel.userInfo.value?.weight ?: 0F
        )
    }

    private fun setupCustomDialogFragmentListener() {
        UserEditInfoDialogFragment.setupListener(parentFragmentManager, this) {
            viewModel.updateUserInfo(it)
        }
    }

    private fun initButtonsControl(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.buttonsEnable.collect {
                with(binding){
                    buttonLogout.isEnabled = it
                    userInfoChange.isEnabled = it
                }
            }
        }
    }

    private fun logoutAlertDialog(){
        AlertDialog.Builder(requireContext())
            .setTitle("Logout")
            .setMessage("All local data was destroy")
            .setPositiveButton("Ok"){ _, _ ->
                viewModel.deauthorize()
                viewModel.clearLocalData()
                findNavController().navigate(R.id.action_userInfoFragment_to_authFragment)
            }
            .setNegativeButton("Cancel"){ dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

}