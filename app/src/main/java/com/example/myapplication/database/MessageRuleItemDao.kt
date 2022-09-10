package com.example.myapplication.database

import androidx.room.*

@Dao
interface MessageRuleItemDao {

    @Query("SELECT * FROM message_rule_items")
    fun getAll(): List<MessageRuleItem>

    @Query("SELECT * FROM message_rule_items WHERE `from` = :sender")
    fun getBySender(sender: String): List<MessageRuleItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(item: MessageRuleItem)

    @Delete
    fun deleteItem(item: MessageRuleItem)
}
