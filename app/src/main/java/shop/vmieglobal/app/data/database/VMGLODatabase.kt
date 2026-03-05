package shop.vmieglobal.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import shop.vmieglobal.app.data.dao.CartItemDao
import shop.vmieglobal.app.data.dao.OrderDao
import shop.vmieglobal.app.data.database.converter.Converters
import shop.vmieglobal.app.data.entity.CartItemEntity
import shop.vmieglobal.app.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class VMGLODatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}