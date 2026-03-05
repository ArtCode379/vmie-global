package shop.vmieglobal.app.data.repository

import shop.vmieglobal.app.R
import shop.vmieglobal.app.data.model.VMGLOProduct
import shop.vmieglobal.app.data.model.VMGLOProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products: List<VMGLOProduct> = listOf(
        // Sports
        VMGLOProduct(
            id = 1,
            title = "Premium Yoga Mat",
            description = "Non-slip premium yoga mat, 6mm thickness, ideal for yoga, pilates and fitness workouts. Eco-friendly TPE material.",
            category = VMGLOProductCategory.SPORTS,
            price = 34.99,
            imageRes = R.drawable.product_sports,
        ),
        VMGLOProduct(
            id = 2,
            title = "Adjustable Dumbbell Set",
            description = "Set of adjustable dumbbells ranging from 2kg to 20kg. Perfect for home gym workouts and strength training.",
            category = VMGLOProductCategory.SPORTS,
            price = 89.99,
            imageRes = R.drawable.product_sports,
        ),
        VMGLOProduct(
            id = 3,
            title = "Resistance Band Set",
            description = "5-piece resistance band set with varying tensions. Great for stretching, mobility, and strength training.",
            category = VMGLOProductCategory.SPORTS,
            price = 19.99,
            imageRes = R.drawable.product_sports,
        ),
        // Fishing
        VMGLOProduct(
            id = 4,
            title = "Carbon Spinning Rod",
            description = "Lightweight 2.4m carbon fibre spinning rod. Suitable for freshwater and light sea fishing. Ultra-sensitive tip.",
            category = VMGLOProductCategory.FISHING,
            price = 59.99,
            imageRes = R.drawable.product_fishing,
        ),
        VMGLOProduct(
            id = 5,
            title = "Fishing Tackle Box Kit",
            description = "Complete tackle box with 200+ pieces: hooks, lures, floats, swivels and weights. Suitable for all fishing styles.",
            category = VMGLOProductCategory.FISHING,
            price = 44.99,
            imageRes = R.drawable.product_fishing,
        ),
        VMGLOProduct(
            id = 6,
            title = "Waterproof Fishing Jacket",
            description = "Breathable waterproof jacket with multiple pockets, adjustable cuffs and hood. Ideal for all-weather fishing.",
            category = VMGLOProductCategory.FISHING,
            price = 74.99,
            imageRes = R.drawable.product_fishing,
        ),
        // Camping
        VMGLOProduct(
            id = 7,
            title = "3-Season Dome Tent",
            description = "2-person dome tent with 3000mm waterproof rating. Quick pitch design, includes carry bag and pegs.",
            category = VMGLOProductCategory.CAMPING,
            price = 119.99,
            imageRes = R.drawable.product_camping,
        ),
        VMGLOProduct(
            id = 8,
            title = "Sleeping Bag -5°C",
            description = "Mummy-style sleeping bag rated to -5°C. Lightweight 1.2kg, compressible, with carry sack.",
            category = VMGLOProductCategory.CAMPING,
            price = 54.99,
            imageRes = R.drawable.product_camping,
        ),
        VMGLOProduct(
            id = 9,
            title = "Portable Camp Stove",
            description = "Compact gas stove with windshield. 2200W output, boils 1L in under 3 minutes. Compatible with standard butane cartridges.",
            category = VMGLOProductCategory.CAMPING,
            price = 39.99,
            imageRes = R.drawable.product_camping,
        ),
        // Boats
        VMGLOProduct(
            id = 10,
            title = "Inflatable Fishing Boat",
            description = "2-person inflatable boat with aluminium floor. Max load 200kg, includes oars and pump. Perfect for lake and river fishing.",
            category = VMGLOProductCategory.BOATS,
            price = 249.99,
            imageRes = R.drawable.product_boat,
        ),
        VMGLOProduct(
            id = 11,
            title = "Inflatable Kayak",
            description = "Single-seat touring kayak. Lightweight at 8kg, includes paddle and carry bag. Suitable for calm waters.",
            category = VMGLOProductCategory.BOATS,
            price = 189.99,
            imageRes = R.drawable.product_boat,
        ),
        // Bikes
        VMGLOProduct(
            id = 12,
            title = "Mountain Bike 29\"",
            description = "Hardtail mountain bike with 21-speed Shimano gears, hydraulic disc brakes and 29\" alloy wheels. Great for trails.",
            category = VMGLOProductCategory.BIKES,
            price = 549.99,
            imageRes = R.drawable.product_bike,
        ),
        VMGLOProduct(
            id = 13,
            title = "City Hybrid Bike",
            description = "Lightweight hybrid bike suitable for city commuting and leisure rides. 700c wheels, 7-speed Shimano gears.",
            category = VMGLOProductCategory.BIKES,
            price = 329.99,
            imageRes = R.drawable.product_bike,
        ),
    )

    fun observeById(id: Int): Flow<VMGLOProduct?> {
        val item = products.find { it.id == id }
        return flowOf(item)
    }

    fun getById(id: Int): VMGLOProduct? {
        return products.find { it.id == id }
    }

    fun observeAll(): Flow<List<VMGLOProduct>> {
        return flowOf(products)
    }
}
