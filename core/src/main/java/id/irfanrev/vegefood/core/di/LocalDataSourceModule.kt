package id.irfanrev.vegefood.core.di

import androidx.room.Room
import id.irfanrev.vegefood.core.data.source.local.LocalDataSource
import id.irfanrev.vegefood.core.data.source.local.room.MealsDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataSourceModule = module {
    factory { get<MealsDatabase>().mealsDao() }
    single { LocalDataSource(get()) }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("Skills39".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MealsDatabase::class.java, "meals.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}