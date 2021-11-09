package com.bogdanov.strava.presentation.user_info.edit

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.bogdanov.strava.R
import com.bogdanov.strava.databinding.FragmentEditUserinfoDialogBinding

class UserEditInfoDialogFragment: DialogFragment() {

    private val weight: Float
        get() = requireArguments().getFloat(ARG_WEIGHT)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = FragmentEditUserinfoDialogBinding.inflate(layoutInflater)

        binding.weightInputText.setText(weight.toString())

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialog_title)
            .setView(binding.root)
            .setPositiveButton(R.string.ok, null)
            .setNegativeButton(R.string.cancel, null)
            .create()

        dialog.setOnShowListener {
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val enteredText = binding.weightInputText.text.toString()
                if (enteredText.isBlank()) {
                    binding.weightInputText.error = getString(R.string.weight_error)
                    return@setOnClickListener
                }
                val weight = enteredText.toFloatOrNull()
                if (weight == null){
                    binding.weightInputText.error = getString(R.string.weight_error)
                    return@setOnClickListener
                }
                parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_WEIGHT_RESPONSE to weight))
                dismiss()
            }
        }

        return dialog
    }

    companion object {
        @JvmStatic private val TAG = UserEditInfoDialogFragment::class.java.simpleName
        @JvmStatic private val KEY_WEIGHT_RESPONSE = "KEY_WEIGHT_RESPONSE"
        @JvmStatic private val ARG_WEIGHT = "ARG_WEIGHT"
        @JvmStatic private val REQUEST_KEY = "$TAG:defaultRequestKey"

        fun show(manager: FragmentManager, weight: Float) {
            val dialogFragment = UserEditInfoDialogFragment()
            dialogFragment.arguments = bundleOf(
                ARG_WEIGHT to weight
            )
            dialogFragment.show(manager, TAG)
        }

        fun setupListener(manager: FragmentManager, lifecycleOwner: LifecycleOwner, listener: (Float) -> Unit) {
            manager.setFragmentResultListener(REQUEST_KEY, lifecycleOwner, { _, result ->
                listener.invoke(result.getFloat(KEY_WEIGHT_RESPONSE))
            })
        }

    }
}