package com.ufrj.models

import org.jetbrains.exposed.sql.Table

object Bairro: Table() {
    val id = integer("ID_Bairro").primaryKey()
    val nome = varchar("Nome_Bairro", 40)
}