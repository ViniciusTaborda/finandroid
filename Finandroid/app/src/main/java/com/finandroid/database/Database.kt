package com.finandroid.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.finandroid.models.Transaction
import com.finandroid.models.TransactionResponse

object TransactionEntry : BaseColumns {
    const val TABLE_NAME = "transactions"
    const val COLUMN_NAME_VALUE = "value"
    const val COLUMN_NAME_BANK = "bank"
    const val COLUMN_NAME_CATEGORY = "category"
    const val COLUMN_NAME_DESCRIPTION = "description"
    const val COLUMN_NAME_FLOW = "flow"
}

private val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${TransactionEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${TransactionEntry.COLUMN_NAME_VALUE} INTEGER," +
            "${TransactionEntry.COLUMN_NAME_BANK} TEXT," +
            "${TransactionEntry.COLUMN_NAME_CATEGORY} TEXT," +
            "${TransactionEntry.COLUMN_NAME_DESCRIPTION} TEXT," +
            "${TransactionEntry.COLUMN_NAME_FLOW} INTEGER)"

private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TransactionEntry.TABLE_NAME}"

class TransactionDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Transactions.db"
    }

    fun insertTransaction(transaction: Transaction): Long? {
        // Gets the data repository in write mode
        val db = this.writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(TransactionEntry.COLUMN_NAME_VALUE, transaction.value)
            put(TransactionEntry.COLUMN_NAME_BANK, transaction.bank)
            put(TransactionEntry.COLUMN_NAME_CATEGORY, transaction.category)
            put(TransactionEntry.COLUMN_NAME_DESCRIPTION, transaction.description)
            put(TransactionEntry.COLUMN_NAME_FLOW, transaction.flow)
        }

        // Insert the new row, returning the primary key value of the new row
        return db?.insert(TransactionEntry.TABLE_NAME, null, values);
    }

    fun readTransactions(): MutableList<TransactionResponse> {
        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM " + TransactionEntry.TABLE_NAME, null)

        val values = mutableListOf<TransactionResponse>();
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val value = getDouble(getColumnIndexOrThrow(TransactionEntry.COLUMN_NAME_VALUE))
                val bank = getString(getColumnIndexOrThrow(TransactionEntry.COLUMN_NAME_BANK))
                val category =
                    getString(getColumnIndexOrThrow(TransactionEntry.COLUMN_NAME_CATEGORY))
                val description =
                    getString(getColumnIndexOrThrow(TransactionEntry.COLUMN_NAME_DESCRIPTION))
                val flow = getInt(getColumnIndexOrThrow(TransactionEntry.COLUMN_NAME_FLOW))

                val transaction = TransactionResponse(id, value, bank, category, description, flow)
                values.add(transaction)
            }
        }
        cursor.close()

        return values
    }

    fun readIncomes(): MutableList<Double> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM " + TransactionEntry.TABLE_NAME + " WHERE flow = 0 ORDER BY " + BaseColumns._ID + " ASC", null)

        val values = mutableListOf<Double>();
        with(cursor) {
            while (moveToNext()) {
                val value = getDouble(getColumnIndexOrThrow(TransactionEntry.COLUMN_NAME_VALUE))
                values.add(value)
            }
        }
        cursor.close()

        return values
    }

    fun readExpenses(): MutableList<Double> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM " + TransactionEntry.TABLE_NAME + " WHERE flow = 1 ORDER BY " + BaseColumns._ID + " ASC", null)

        val values = mutableListOf<Double>();
        with(cursor) {
            while (moveToNext()) {
                val value = getDouble(getColumnIndexOrThrow(TransactionEntry.COLUMN_NAME_VALUE))
                values.add(value)
            }
        }
        cursor.close()

        return values
    }
}