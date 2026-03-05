package shop.vmieglobal.app.di

import shop.vmieglobal.app.ui.viewmodel.VMGLOAppViewModel
import shop.vmieglobal.app.ui.viewmodel.VMGLOCartViewModel
import shop.vmieglobal.app.ui.viewmodel.VMGLOCheckoutViewModel
import shop.vmieglobal.app.ui.viewmodel.VMGLOOnboardingViewModel
import shop.vmieglobal.app.ui.viewmodel.VMGLOOrderViewModel
import shop.vmieglobal.app.ui.viewmodel.VMGLOProductDetailsViewModel
import shop.vmieglobal.app.ui.viewmodel.VMGLOProductViewModel
import shop.vmieglobal.app.ui.viewmodel.VMGLOSplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        VMGLOAppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        VMGLOSplashViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        VMGLOOnboardingViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        VMGLOProductViewModel(
            productRepository = get(),
        )
    }

    viewModel {
        VMGLOProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        VMGLOCheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        VMGLOCartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        VMGLOOrderViewModel(
            orderRepository = get(),
        )
    }
}