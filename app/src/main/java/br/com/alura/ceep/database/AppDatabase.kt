package br.com.alura.ceep.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.alura.ceep.database.dao.NotaDao
import br.com.alura.ceep.model.Nota

@Database(
    version = 4,
    entities = [Nota::class],
    exportSchema = true,

    autoMigrations = [
        AutoMigration(from = 2, to = 4)
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun notaDao(): NotaDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null

        fun instancia(context: Context): AppDatabase {
            return db ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "ceep.db"
            ).build()
        }
    }

}