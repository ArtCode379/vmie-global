package shop.vmieglobal.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import shop.vmieglobal.app.ui.composable.approot.AppRoot
import shop.vmieglobal.app.ui.theme.ProductAppSkeletonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductAppSkeletonTheme {
                AppRoot()
            }
        }
    }
}