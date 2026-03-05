package shop.vmieglobal.app

import android.app.Application
import shop.vmieglobal.app.di.dataModule
import shop.vmieglobal.app.di.dispatcherModule
import shop.vmieglobal.app.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class VMGLOApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@VMGLOApp)
            modules(appModules)
        }
    }
}