package com.ufrj

import com.ufrj.models.*
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

    fun getAllVLTStations(): List<Map<String,Any>> {
        return getVLTStations( Estacao_VLT.innerJoin(Estacao_Linha_VLT).innerJoin(Linha_VLT).innerJoin(Bairro).selectAll().orderBy(Estacao_VLT.id) )
    }

    fun getVLTStationById( id: Int ): List<Map<String,Any>> {
        return getVLTStations( Estacao_VLT.innerJoin(Estacao_Linha_VLT).innerJoin(Linha_VLT).innerJoin(Bairro).select{ Estacao_VLT.id eq id }.orderBy(Estacao_VLT.id) )
    }

    fun getVLTStationsByName( name: String ): List<Map<String,Any>> {
        return getVLTStations( Estacao_VLT.innerJoin(Estacao_Linha_VLT).innerJoin(Linha_VLT).innerJoin(Bairro).select{ Estacao_VLT.nome like "%$name%" or(Bairro.nome like "%$name%") }.orderBy(Estacao_VLT.id) )
    }

    fun getAllVLTLines(): List<Map<String,Any>> {
        return getVLTLines( Estacao_VLT.innerJoin(Estacao_Linha_VLT).innerJoin(Linha_VLT).innerJoin(Bairro).selectAll().orderBy(Linha_VLT.id) )
    }

    fun getVLTLinesById( id: Int ): List<Map<String, Any>> {
        return getVLTLines( Estacao_VLT.innerJoin(Estacao_Linha_VLT).innerJoin(Linha_VLT).innerJoin(Bairro).select{ Linha_VLT.id eq id } .orderBy(Linha_VLT.id) )
    }

    fun getVLTLinesByName( name: String ): List<Map<String, Any>> {
        return getVLTLines( Estacao_VLT.innerJoin(Estacao_Linha_VLT).innerJoin(Linha_VLT).innerJoin(Bairro).select{ Linha_VLT.nome like "%$name%" or (Linha_VLT.cor like "%$name%")} .orderBy(Linha_VLT.id) )
    }

    fun getAllMetroStations(): List<Map<String,Any>> {
        return getMetroStations( Estacao_Metro.innerJoin(Estacao_Linha_Metro).innerJoin(Linha_Metro).innerJoin(Bairro).innerJoin(Saidas_Metro).selectAll().orderBy(Estacao_Metro.id) )
    }

    fun getMetroStationById( id: Int ): List<Map<String,Any>> {
        return getMetroStations( Estacao_Metro.innerJoin(Estacao_Linha_Metro).innerJoin(Linha_Metro).innerJoin(Bairro).innerJoin(Saidas_Metro).select{ Estacao_Metro.id eq id }.orderBy(Estacao_Metro.id) )
    }

    fun getMetroStationsByName( name: String ): List<Map<String,Any>> {
        return getMetroStations( Estacao_Metro.innerJoin(Estacao_Linha_Metro).innerJoin(Linha_Metro).innerJoin(Bairro).innerJoin(Saidas_Metro).select{ Estacao_Metro.nome like "%$name%" or(Bairro.nome like "%$name%") }.orderBy(Estacao_Metro.id) )
    }

    fun getAllMetroLines(): List<Map<String,Any>> {
        return getMetroLines( Estacao_Metro.innerJoin(Estacao_Linha_Metro).innerJoin(Linha_Metro).innerJoin(Bairro).selectAll().orderBy(Linha_Metro.id) )
    }

    fun getMetroLinesById( id: Int ): List<Map<String, Any>> {
        return getMetroLines( Estacao_Metro.innerJoin(Estacao_Linha_Metro).innerJoin(Linha_Metro).innerJoin(Bairro).select{ Linha_Metro.id eq id } .orderBy(Linha_Metro.id) )
    }

    fun getMetroLinesByName( name: String ): List<Map<String, Any>> {
        return getMetroLines( Estacao_Metro.innerJoin(Estacao_Linha_Metro).innerJoin(Linha_Metro).innerJoin(Bairro).select{ Linha_Metro.nome like "%$name%" or (Linha_Metro.cor like "%$name%")} .orderBy(Linha_Metro.id) )
    }


}