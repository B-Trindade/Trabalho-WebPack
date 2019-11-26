package com.ufrj

import com.ufrj.models.Bairro
import com.ufrj.models.Estacao_BRT
import com.ufrj.models.Linha_BRT
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.transactions.transaction

fun getBRTStations( query: Query): List<Map<String,Any>> {
    val estacoes = mutableListOf<MutableMap<String, Any>>()

    Database.connect("jdbc:mysql://localhost:3306/bd", driver = "com.mysql.jdbc.Driver", user = "root")
    transaction {

        for (estacao in query) {
            val existeEstacao = estacoes.find {
                it["id"] == estacao[Estacao_BRT.id]
            }
            if (existeEstacao != null) {
                val linha = mutableMapOf<String, Any>()
                linha["id"] = estacao[Linha_BRT.id]
                linha["cor"] = estacao[Linha_BRT.cor]
                linha["nome"] = estacao[Linha_BRT.nome]
                (existeEstacao["linhas"] as MutableList<Any>).add(linha)
            }
            else {

                val linhas = mutableListOf<Any>()
                val integracoes = mutableListOf<String>()
                val map = mutableMapOf<String, Any>()
                if (estacao[Estacao_BRT.integraAeroporto] == "Sim") integracoes.add("Aeroporto")
                if (estacao[Estacao_BRT.integraMetro] == "Sim") integracoes.add("Metrô")
                if (estacao[Estacao_BRT.integraTrem] == "Sim") integracoes.add("Trem")
                val linha = mutableMapOf<String, Any>()
                linha["id linha"] = estacao[Linha_BRT.id]
                linha["cor linha"] = estacao[Linha_BRT.cor]
                linha["nome linha"] = estacao[Linha_BRT.nome]
                linhas.add(linha)
                map["nome"] = estacao[Estacao_BRT.nome]
                map["id"] = estacao[Estacao_BRT.id]
                map["bairro"] = estacao[Bairro.nome]
                map["integracoes"] = integracoes
                map["linhas"] = linhas

                estacoes.add(map)
            }
        }
    }
    return estacoes
}

fun getBRTLines( query: Query ): List<Map<String,Any>> {
    val linhas = mutableListOf<MutableMap<String, Any>>()

    Database.connect("jdbc:mysql://localhost:3306/bd", driver = "com.mysql.jdbc.Driver", user = "root")
    transaction {

        for (linha in query) {
            val existeLinha = linhas.find {
                it["id"] == linha[Linha_BRT.id]
            }
            if (existeLinha != null) {
                val estacao = mutableMapOf<String, Any>()
                estacao["id"] = linha[Estacao_BRT.id]
                estacao["nome"] = linha[Estacao_BRT.nome]
                estacao["bairro"] = linha[Bairro.nome]
                (existeLinha["estacoes"] as MutableList<Any>).add(estacao)
            }
            else {

                val estacoes = mutableListOf<Any>()
                val map = mutableMapOf<String, Any>()
                val estacao = mutableMapOf<String, Any>()
                estacao["id"] = linha[Estacao_BRT.id]
                estacao["nome"] = linha[Estacao_BRT.nome]
                estacao["bairro"] = linha[Bairro.nome]
                estacoes.add(estacao)
                map["id"] = linha[Linha_BRT.id]
                map["nome"] = linha[Linha_BRT.nome]
                map["cor"] = linha[Linha_BRT.cor]
                map["estacoes"] = estacoes

                linhas.add(map)
            }
        }
    }
    return linhas
}
