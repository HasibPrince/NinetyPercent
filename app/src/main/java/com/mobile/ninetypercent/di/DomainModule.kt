package com.mobile.ninetypercent.di

import com.mobile.ninetypercent.domain.DressUseCase
import com.mobile.ninetypercent.domain.IDressUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface DomainModule {
    @Binds
    fun getDressUseCase(dressUseCase: DressUseCase): IDressUseCase
}