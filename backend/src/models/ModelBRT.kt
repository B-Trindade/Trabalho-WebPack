package com.ufrj.models

import org.jetbrains.exposed.sql.Table

object Estacao_BRT: Table() {
    val id = integer("ID_Est_BRT").primaryKey()
    val nome = varchar("Nome_Est_BRT", 50)
    val flagAtivo = varchar("Flag_BRT_Ativo", 3)
    val integraTrem = varchar("BRT_Integra_Trem", 3)
    val integraAeroporto = varchar("BRT_Integra_Aeroporto", 3)
    val integraMetro = varchar("BRT_Integra_Metro", 3)
    val idBairro = integer("ID_Bairro").references(Bairro.id)
}

object Linha_BRT: Table() {
    val id = integer("ID_Linha_BRT").primaryKey()
    val nome = varchar("Nome_Linha_BRT", 20)
    val cor = varchar("Cor_Linha_BRT", 10)
}

object Estacao_Linha_BRT: Table() {
    val idEstacao = integer("ID_Est_BRT").references(Estacao_BRT.id)
    val idLinha = integer("ID_Linha_BRT").references(Linha_BRT.id)
}
