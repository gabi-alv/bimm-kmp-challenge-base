package com.bimm.takehomeassignmnent.sakes.di

import com.bimm.takehomeassignmnent.sakes.list.SakesListViewModel
import com.bimm.takehomeassignmnent.sakes.data.LocalResourceSakesRepository
import com.bimm.takehomeassignmnent.sakes.data.SakesRepository
import com.bimm.takehomeassignmnent.sakes.detail.SakesDetailViewModel

import org.koin.dsl.module


fun sakesModule() = module {
    single<SakesRepository> {
        LocalResourceSakesRepository(
            resourceDataSource = get()
        )
    }

    scope<SakesListViewModel> {
        scoped<SakesListViewModel> {
            SakesListViewModel(get())
        }
    }

    scope<SakesDetailViewModel> {
        scoped <SakesDetailViewModel> { SakesDetailViewModel(get()) }
    }
}