package data.di

import org.koin.core.module.Module
import org.koin.dsl.module
import utils.DatastoreFactory
import kotlin.math.sin

actual val platformModule = module {
    single { DatastoreFactory().createDatastore() }

}