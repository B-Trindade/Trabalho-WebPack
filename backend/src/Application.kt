package com.ufrj

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*
import io.ktor.features.ContentNegotiation
import io.ktor.http.content.*
import io.ktor.jackson.jackson
import kotlin.reflect.typeOf

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation){
        jackson{
        }
    }
    routing {
        route("/") {
            route("brt") {
                get("estacoes") {
                    val array = TransportService().getAllBRTStations()
                    call.respond(array)
                }
                get("estacoes/{param}") {
                    val param = call.parameters["param"]!!
                    val type = if (param.toIntOrNull() == null) "string" else "integer"
                    val response = if(type == "integer") TransportService().getBRTStationById( param.toInt() ) else TransportService().getBRTStationsByName(param)
                    call.respond(response)
                }
                get("linhas") {
                    val array = TransportService().getAllBRTLines()
                    call.respond(array)
                }
                get("linhas/{param}") {
                    val param = call.parameters["param"]!!
                    val type = if (param.toIntOrNull() == null) "string" else "integer"
                    val response = if(type == "integer") TransportService().getBRTLinesById( param.toInt() ) else TransportService().getBRTLinesByName(param)
                    call.respond(response)
                }
            }
            route("vlt") {
                get("estacoes") {
                    val array = TransportService().getAllVLTStations()
                    call.respond(array)
                }
                get("estacoes/{param}") {
                    val param = call.parameters["param"]!!
                    val type = if (param.toIntOrNull() == null) "string" else "integer"
                    val response = if(type == "integer") TransportService().getVLTStationById( param.toInt() ) else TransportService().getVLTStationsByName(param)
                    call.respond(response)
                }
                get("linhas") {
                    val array = TransportService().getAllVLTLines()
                    call.respond(array)
                }
                get("linhas/{param}") {
                    val param = call.parameters["param"]!!
                    val type = if (param.toIntOrNull() == null) "string" else "integer"
                    val response = if(type == "integer") TransportService().getVLTLinesById( param.toInt() ) else TransportService().getVLTLinesByName(param)
                    call.respond(response)
                }
            }
            route("metro") {
                get("estacoes") {
                    val array = TransportService().getAllMetroStations()
                    call.respond(array)
                }
                get("estacoes/{param}") {
                    val param = call.parameters["param"]!!
                    val type = if (param.toIntOrNull() == null) "string" else "integer"
                    val response = if(type == "integer") TransportService().getMetroStationById( param.toInt() ) else TransportService().getMetroStationsByName(param)
                    call.respond(response)
                }
                get("linhas") {
                    val array = TransportService().getAllMetroLines()
                    call.respond(array)
                }
                get("linhas/{param}") {
                    val param = call.parameters["param"]!!
                    val type = if (param.toIntOrNull() == null) "string" else "integer"
                    val response = if(type == "integer") TransportService().getMetroLinesById( param.toInt() ) else TransportService().getMetroLinesByName(param)
                    call.respond(response)
                }
            }
        }

//        get("/brt/{nome_estacao}") {
//            var map = mutableMapOf<String, Any>()
//            map["nome"] = call.parameters["nome_estacao"]!!
//            map["idade"] = 20
//            call.respond(map)
//        }

        // Static feature. Try to access `/static/ktor_logo.svg`
        static("/static") {
            resources("static")
        }
    }
}

