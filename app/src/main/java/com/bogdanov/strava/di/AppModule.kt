package com.bogdanov.strava.di

import com.bogdanov.strava.auth.AuthRepository
import com.bogdanov.strava.data.repository.UserRepositoryImpl
import com.bogdanov.strava.presentation.auth.AuthViewModel
import com.bogdanov.strava.data.local.Database
import com.bogdanov.strava.data.repository.WorkoutRepositoryImpl
import com.bogdanov.strava.datastore.SharedPrefs
import com.bogdanov.strava.domain.repository.UserRepository
import com.bogdanov.strava.domain.repository.WorkoutRepository
import com.bogdanov.strava.domain.use_case.GetUserInfoUseCase
import com.bogdanov.strava.domain.use_case.UpdateUserInfoUserCase
import com.bogdanov.strava.network.Networking
import com.bogdanov.strava.presentation.user_info.UserInfoViewModel
import com.bogdanov.strava.presentation.user_workouts.UserWorkoutListViewModel
import com.bogdanov.strava.presentation.user_workouts.add_workout.WorkoutAddViewModel
import net.openid.appauth.AuthorizationService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { AuthRepository() }
    single { AuthorizationService(androidContext()) }
    single<WorkoutRepository> { WorkoutRepositoryImpl(Database.instance.workoutDao(), Networking.stravaApi, get()) }
    single { SharedPrefs }

    single<UserRepository> { UserRepositoryImpl(Networking.stravaApi, Database.instance.userDao()) }

    factory<GetUserInfoUseCase> {
        GetUserInfoUseCase(get())
    }

    factory<UpdateUserInfoUserCase> {
        UpdateUserInfoUserCase(get())
    }

    viewModel { AuthViewModel(get(), get(), get()) }

    viewModel { UserInfoViewModel(get(), get(), get(), get(), get()) }

    viewModel { UserWorkoutListViewModel(get(), get()) }

    viewModel { WorkoutAddViewModel(get()) }

}