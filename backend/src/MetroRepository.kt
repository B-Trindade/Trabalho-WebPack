package com.ufrj

import com.ufrj.models.Bairro
import com.ufrj.models.Estacao_Metro
import com.ufrj.models.Linha_Metro
import com.ufrj.models.Saidas_Metro
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.transactions.transaction

fun getMetroStations( query: Query): List<Map<String,Any>> {
    val estacoes = mutableListOf<MutableMap<String, Any>>()

    Database.connect("jdbc:mysql://localhost:3306/bd", driver = "com.mysql.jdbc.Driver", user = "root")
    transaction {

        for (estacao in query) {
            val existeEstacao = estacoes.find {
                it["id"] == estacao[Estacao_Metro.id]
            }
            if (existeEstacao != null) {
                val linha = mutableMapOf<String, Any>()
                linha["id"] = estacao[Linha_Metro.id]
                linha["cor"] = estacao[Linha_Metro.cor]
                linha["nome"] = estacao[Linha_Metro.nome]
                if (!(existeEstacao["linhas"] as MutableList<Any>).contains(linha)) (existeEstacao["linhas"] as MutableList<Any>).add(linha)
                val saida = estacao[Saidas_Metro.nome]
                if (!(existeEstacao["saidas"] as MutableList<String>).contains(saida)) (existeEstacao["saidas"] as MutableList<Any>).add(saida)
            }
            else {

                val linhas = mutableListOf<Any>()
                val integracoes = mutableListOf<String>()
                val saidas = mutableListOf<String>()
                val map = mutableMapOf<String, Any>()
                if (estacao[Estacao_Metro.integraVLT] == "Sim") integracoes.add("VLT")
                if (estacao[Estacao_Metro.integraOnibus] == "Sim") integracoes.add("Ônibus")
                if (estacao[Estacao_Metro.integraBRT] == "Sim") integracoes.add("BRT")
                if (estacao[Estacao_Metro.integraTrem] == "Sim") integracoes.add("Trem")
                val linha = mutableMapOf<String, Any>()
                linha["id"] = estacao[Linha_Metro.id]
                linha["cor"] = estacao[Linha_Metro.cor]
                linha["nome"] = estacao[Linha_Metro.nome]
                linhas.add(linha)
                saidas.add(estacao[Saidas_Metro.nome])
                map["nome"] = estacao[Estacao_Metro.nome]
                map["ativo"] = estacao[Estacao_Metro.flagAtivo] == "Sim"
                map["metro na superficie"] = estacao[Estacao_Metro.flagMetroSuperficie] == "Sim"
                map["id"] = estacao[Estacao_Metro.id]
                map["bairro"] = estacao[Bairro.nome]
                map["integracoes"] = integracoes
                map["saidas"] = saidas
                map["linhas"] = linhas

                estacoes.add(map)
            }
        }
    }
    return estacoes
}

fun getMetroLines( query: Query ): List<Map<String,Any>> {
    val linhas = mutableListOf<MutableMap<String, Any>>()

    Database.connect("jdbc:mysql://localhost:3306/bd", driver = "com.mysql.jdbc.Driver", user = "root")
    transaction {

        for (linha in query) {
            val existeLinha = linhas.find {
                it["id"] == linha[Linha_Metro.id]
            }
            if (existeLinha != null) {
                val estacao = mutableMapOf<String, Any>()
                estacao["id"] = linha[Estacao_Metro.id]
                estacao["nome"] = linha[Estacao_Metro.nome]
                estacao["bairro"] = linha[Bairro.nome]
                (existeLinha["estacoes"] as MutableList<Any>).add(estacao)
            }
            else {

                val estacoes = mutableListOf<Any>()
                val map = mutableMapOf<String, Any>()
                val estacao = mutableMapOf<String, Any>()
                estacao["id"] = linha[Estacao_Metro.id]
                estacao["nome"] = linha[Estacao_Metro.nome]
                estacao["bairro"] = linha[Bairro.nome]
                estacoes.add(estacao)
                map["id"] = linha[Linha_Metro.id]
                map["nome"] = linha[Linha_Metro.nome]
                map["cor"] = linha[Linha_Metro.cor]
                map["estacoes"] = estacoes

                linhas.add(map)
            }
        }
    }
    return linhas
}
