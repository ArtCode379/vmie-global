package shop.vmieglobal.app.data.model

import androidx.annotation.DrawableRes

data class VMGLOProduct(
    val id: Int,
    val title: String,
    val description: String,
    val category: VMGLOProductCategory,
    val price: Double,
    @field:DrawableRes val imageRes: Int,
)