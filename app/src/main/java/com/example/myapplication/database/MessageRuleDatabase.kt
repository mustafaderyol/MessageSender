package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [MessageRuleItem::class])
abstract class MessageRuleDatabase : RoomDatabase() {

    abstract fun messageRuleItemDao(): MessageRuleItemDao
}
