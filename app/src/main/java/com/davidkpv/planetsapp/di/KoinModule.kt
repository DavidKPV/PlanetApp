package com.davidkpv.planetsapp.di

import androidx.room.Room
import com.davidkpv.planetsapp.data.apis.provideGetPlanetApi
import com.davidkpv.planetsapp.data.apis.provideRetrofit
import com.davidkpv.planetsapp.data.localdb.AppDatabase
import com.davidkpv.planetsapp.presentation.viewModels.CatalogViewModel
import com.davidkpv.planetsapp.presentation.viewModels.RegisterViewModel
import com.davidkpv.planetsapp.presentation.viewModels.SplashViewModel
import com.davidkpv.planetsapp.repository.PlanetRepository
import com.davidkpv.planetsapp.useCases.GetPlanetsUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule =
    module {
        single { provideRetrofit() }
        single { provideGetPlanetApi(get()) }
        single {
            Room
                .databaseBuilder(androidContext(), AppDatabase::class.java, "localDb")
                .fallbackToDestructiveMigration()
                .build()
        }
        single { get<AppDatabase>().userDao() }
        single { PlanetRepository(get()) }
        single { GetPlanetsUseCase(get()) }

        viewModel {
            SplashViewModel(
                userDao = get(),
            )
        }
        viewModel {
            RegisterViewModel(
                userDao = get(),
            )
        }
        viewModel {
            CatalogViewModel(
                getPlanetsUseCase = get(),
            )
        }
    }
