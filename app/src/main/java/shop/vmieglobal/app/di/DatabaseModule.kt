package shop.vmieglobal.app.di

import androidx.room.Room
import shop.vmieglobal.app.data.database.VMGLODatabase
import org.koin.dsl.module

private const val DB_NAME = "skeleton_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = VMGLODatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<VMGLODatabase>().cartItemDao() }

    single { get<VMGLODatabase>().orderDao() }
}