package com.ufrj.models

import org.jetbrains.exposed.sql.Table

object Estacao_Metro: Table() {
    val id = integer("ID_Est_Metro").primaryKey()
    val nome = varchar("Nome_Est_Metro", 50)
    val flagAtivo = varchar("Flag_Metro_Ativo", 3)
    val flagMetroSuperficie = varchar("Flag_Metro_Superficie", 3)
    val integraTrem = varchar("Metro_Integra_Trem", 3)
    val integraBRT = varchar("Metro_Integra_BRT", 3)
    val integraOnibus = varchar("Metro_Integra_Onibus", 3)
    val integraVLT = varchar("Metro_Integra_VLT", 3)
    val idBairro = integer("ID_Bairro").references(Bairro.id)
}

object Linha_Metro: Table() {
    val id = integer("ID_Linha_Metro").primaryKey()
    val nome = varchar("Nome_Linha_Metro", 20)
    val cor = varchar("Cor_Linha_Metro", 10)
}

object Estacao_Linha_Metro: Table() {
    val idEstacao = integer("ID_Est_Metro").references(Estacao_Metro.id)
    val idLinha = integer("ID_Linha_Metro").references(Linha_Metro.id)
}

object Saidas_Metro: Table() {
    val idEstacao = integer("ID_Est_Metro")
    val nome = varchar("Nome_Saida", 40)
}