package shop.vmieglobal.app.data.model

import androidx.annotation.StringRes
import shop.vmieglobal.app.R

enum class VMGLOProductCategory(@field:StringRes val titleRes: Int) {
    SPORTS(R.string.category_sports),
    FISHING(R.string.category_fishing),
    CAMPING(R.string.category_camping),
    BOATS(R.string.category_boats),
    BIKES(R.string.category_bikes),
}
