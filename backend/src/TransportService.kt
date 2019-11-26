package com.ufrj

import com.ufrj.models.Bairro
import com.ufrj.models.Estacao_BRT
import com.ufrj.models.Estacao_Linha_BRT
import com.ufrj.models.Linha_BRT
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class TransportService {

    fun getAllBRTStations() : List<Map<String,Any>> {
        return getBRTStations( Estacao_BRT.innerJoin(Estacao_Linha_BRT).innerJoin(Linha_BRT).innerJoin(Bairro).selectAll().orderBy(Estacao_BRT.id) )
    }

    fun getBRTStationById( id: Int ): List<Map<String, Any>> {
        return getBRTStations( Estacao_BRT.innerJoin(Estacao_Linha_BRT).innerJoin(Linha_BRT).innerJoin(Bairro).select{ Estacao_BRT.id eq id } .orderBy(Estacao_BRT.id) )
    }

    fun getBRTStationsByName( name: String ): List<Map<String, Any>> {
        return getBRTStations( Estacao_BRT.innerJoin(Estacao_Linha_BRT).innerJoin(Linha_BRT).innerJoin(Bairro).select{ Estacao_BRT.nome like "%$name%" or (Bairro.nome like "%$name%") } .orderBy(Estacao_BRT.id) )
    }

    fun getAllBRTLines(): List<Map<String,Any>> {
        return getBRTLines( Estacao_BRT.innerJoin(Estacao_Linha_BRT).innerJoin(Linha_BRT).innerJoin(Bairro).selectAll().orderBy(Linha_BRT.id) )
    }

    fun getBRTLinesById( id: Int ): List<Map<String, Any>> {
        return getBRTLines( Estacao_BRT.innerJoin(Estacao_Linha_BRT).innerJoin(Linha_BRT).innerJoin(Bairro).select{ Linha_BRT.id eq id } .orderBy(Linha_BRT.id) )
    }

    fun getBRTLinesByName( name: String ): List<Map<String, Any>> {
        return getBRTLines( Estacao_BRT.innerJoin(Estacao_Linha_BRT).innerJoin(Linha_BRT).innerJoin(Bairro).select{ Linha_BRT.nome like "%$name%" or (Linha_BRT.cor like "%$name%")} .orderBy(Linha_BRT.id) )
    }


}