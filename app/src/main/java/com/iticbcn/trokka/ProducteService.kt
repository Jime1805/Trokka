package com.iticbcn.trokka

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProducteService {

    @GET("/trokka/objecte")
    suspend fun getAllObjecte(): Response<List<Producte>>

    @GET("/trokka/objecte/{id}")
    suspend fun getObjecteById(
        @Path("id") id: Int
    ): Response<Producte>

    @POST("/trokka/objecte")
    suspend fun createObject(
        @Body objecte: Producte
    ): Response<String>

    // Post de multipartfile, csv

    // Post de multipartfile, img

    @PUT("/trokka/objecte/{id}")
    suspend fun putObjecte(
        @Path("id") id: Int,
        @Body objecte: Producte
    ): Response<String>

    @DELETE("/trokka/objecte/{id}")
    suspend fun deleteObjecteById(
        @Path("id") id: Int
    ): Response<String>

    @DELETE("/trokka/objecte")
    suspend fun deleteAllObjecte(): Response<String>
}