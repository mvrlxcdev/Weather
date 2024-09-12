package data.di

import org.koin.core.module.Module
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import utils.DatastoreFactory

actual val platformModule: Module = module {
    single { DatastoreFactory(context = get() ).createDatastore() }
}


