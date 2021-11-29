package com.bogdanov.strava.presentation.user_workouts.add_workout

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bogdanov.strava.R
import com.bogdanov.strava.databinding.FragmentWorkoutAddBinding
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class WorkoutAddFragment: Fragment(R.layout.fragment_workout_add) {

    private val binding: FragmentWorkoutAddBinding by viewBinding()

    private val viewModel: WorkoutAddViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initDropDownMenu()
        initDateTimePicker()
        bindViewModel()
    }

    private fun initToolbar() = with(binding.appBar) {
        toolbar.setTitle(R.string.user_add_title)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun initDropDownMenu(){
        val workoutTypes = resources.getStringArray(R.array.workout_type)

        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, workoutTypes)

        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }

    private fun bindViewModel(){
        binding.saveButton.setOnClickListener { saveWorkout() }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted{
            viewModel.saveErrorFlow.collect {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.saveSuccessFlow.collect {
                findNavController().popBackStack()
            }
        }
    }

    private fun initDateTimePicker(): String {

        val calendar = Calendar.getInstance()

        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        var hour = calendar.get(Calendar.HOUR)
        var minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { _, mHour, mMinute ->
            hour = mHour
            minute = mMinute

            binding.button.text = "" + day + "/"+ month+"/"+year+" "+hour+":"+minute
        }, hour,minute, true)

        val datePickerDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { _, mYear, mMonth, mDay ->
            year = mYear
            month = mMonth
            day = mDay

            timePickerDialog.show()
        }, year, month, day)


        binding.button.setOnClickListener {
            datePickerDialog.show()
        }

        calendar.set(year, month, day, hour, minute)

        val formater = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)

        val dateTime = formater.format(calendar.time)

        return "$dateTime:Z"
    }

    private fun saveWorkout(){
        with(binding){
            viewModel.saveWorkout(
                name = workoutName.editText?.text.toString(),
                type = autoCompleteTextView.text.toString(),
                date = initDateTimePicker(),
                duration = workoutDuration.editText?.text.toString(),
                description = workoutDescription.editText?.text.toString(),
                distance = workoutDistance.editText?.text.toString()
            )
        }
    }

}