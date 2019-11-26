package com.ufrj

import com.ufrj.models.Bairro
import com.ufrj.models.Estacao_VLT
import com.ufrj.models.Linha_VLT
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.transactions.transaction

fun getVLTStations( query: Query): List<Map<String,Any>> {
    val estacoes = mutableListOf<MutableMap<String, Any>>()

    Database.connect("jdbc:mysql://localhost:3306/bd", driver = "com.mysql.jdbc.Driver", user = "root")
    transaction {

        for (estacao in query) {
            val existeEstacao = estacoes.find {
                it["id"] == estacao[Estacao_VLT.id]
            }
            if (existeEstacao != null) {
                val linha = mutableMapOf<String, Any>()
                linha["id"] = estacao[Linha_VLT.id]
                linha["cor"] = estacao[Linha_VLT.cor]
                linha["nome"] = estacao[Linha_VLT.nome]
                (existeEstacao["linhas"] as MutableList<Any>).add(linha)
            }
            else {

                val linhas = mutableListOf<Any>()
                val integracoes = mutableListOf<String>()
                val map = mutableMapOf<String, Any>()
                if (estacao[Estacao_VLT.integraAeroporto] == "Sim") integracoes.add("Aeroporto")
                if (estacao[Estacao_VLT.integraMetro] == "Sim") integracoes.add("Metrô")
                if (estacao[Estacao_VLT.integraTrem] == "Sim") integracoes.add("Trem")
                if (estacao[Estacao_VLT.integraOnibus] == "Sim") integracoes.add("Ônibus")
                if (estacao[Estacao_VLT.integraBarca] == "Sim") integracoes.add("Barca")
                if (estacao[Estacao_VLT.integraTeleferico] == "Sim") integracoes.add("Teleférico")
                val linha = mutableMapOf<String, Any>()
                linha["id"] = estacao[Linha_VLT.id]
                linha["cor"] = estacao[Linha_VLT.cor]
                linha["nome"] = estacao[Linha_VLT.nome]
                linhas.add(linha)
                map["nome"] = estacao[Estacao_VLT.nome]
                map["ativo"] = estacao[Estacao_VLT.flagAtivo] == "Sim"
                map["id"] = estacao[Estacao_VLT.id]
                map["bairro"] = estacao[Bairro.nome]
                map["integracoes"] = integracoes
                map["linhas"] = linhas

                estacoes.add(map)
            }
        }
    }
    return estacoes
}

fun getVLTLines( query: Query ): List<Map<String,Any>> {
    val linhas = mutableListOf<MutableMap<String, Any>>()

    Database.connect("jdbc:mysql://localhost:3306/bd", driver = "com.mysql.jdbc.Driver", user = "root")
    transaction {

        for (linha in query) {
            val existeLinha = linhas.find {
                it["id"] == linha[Linha_VLT.id]
            }
            if (existeLinha != null) {
                val estacao = mutableMapOf<String, Any>()
                estacao["id"] = linha[Estacao_VLT.id]
                estacao["nome"] = linha[Estacao_VLT.nome]
                estacao["bairro"] = linha[Bairro.nome]
                (existeLinha["estacoes"] as MutableList<Any>).add(estacao)
            }
            else {

                val estacoes = mutableListOf<Any>()
                val map = mutableMapOf<String, Any>()
                val estacao = mutableMapOf<String, Any>()
                estacao["id"] = linha[Estacao_VLT.id]
                estacao["nome"] = linha[Estacao_VLT.nome]
                estacao["bairro"] = linha[Bairro.nome]
                estacoes.add(estacao)
                map["id"] = linha[Linha_VLT.id]
                map["nome"] = linha[Linha_VLT.nome]
                map["cor"] = linha[Linha_VLT.cor]
                map["estacoes"] = estacoes

                linhas.add(map)
            }
        }
    }
    return linhas
}
