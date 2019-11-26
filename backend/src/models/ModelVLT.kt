package com.ufrj.models

import org.jetbrains.exposed.sql.Table

object Estacao_VLT: Table() {
    val id = integer("ID_Est_VLT").primaryKey()
    val nome = varchar("Nome_Est_VLT", 50)
    val flagAtivo = varchar("Flag_VLT_Ativo", 3)
    val integraMetro = varchar("VLT_Integra_Metro", 3)
    val integraBarca = varchar("VLT_Integra_Barca", 3)
    val integraOnibus = varchar("VLT_Integra_Onibus", 3)
    val integraTrem = varchar("VLT_Integra_Trem", 3)
    val integraAeroporto = varchar("VLT_Integra_Aeroporto", 3)
    val integraTeleferico = varchar("VLT_Integra_Teleferico", 3)
    val idBairro = integer("ID_Bairro").references(Bairro.id)
}

object Linha_VLT: Table() {
    val id = integer("ID_Linha_VLT").primaryKey()
    val nome = varchar("Nome_Linha_VLT", 20)
    val cor = varchar("Cor_Linha_VLT", 10)
}

object Estacao_Linha_VLT: Table() {
    val idEstacao = integer("ID_Est_Metro").references(Estacao_VLT.id)
    val idLinha = integer("ID_Linha_Metro").references(Linha_VLT.id)
}
