package com.bogdanov.strava

import com.bogdanov.strava.auth.AuthRepository
import com.bogdanov.strava.auth.AuthViewModel
import com.bogdanov.strava.db.UserRepository
import com.bogdanov.strava.db.WorkoutRepository
import com.bogdanov.strava.network.NetworkRepository
import com.bogdanov.strava.user_info.UserInfoViewModel
import com.bogdanov.strava.user_workouts.UserWorkoutListViewModel
import net.openid.appauth.AuthorizationService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module {
    single { AuthRepository() }
    single { AuthorizationService(androidContext()) }
    viewModel { AuthViewModel(get(), get()) }

    single { NetworkRepository() }
    single { UserRepository() }
    viewModel { UserInfoViewModel(get(), get()) }

    single { WorkoutRepository() }
    viewModel { UserWorkoutListViewModel(get(), get()) }

}