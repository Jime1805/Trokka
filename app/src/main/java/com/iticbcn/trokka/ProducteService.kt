package com.iticbcn.trokka

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ProducteService {

    @GET("/trokka/objecte/all")
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
    @Multipart
    @POST("/trokka/objecte/batch")
    suspend fun createObjecteBatch(
        @Part batch: MultipartBody.Part
    ): Response<String>

    // Post de multipartfile, img
    @Multipart
    @POST("/trokka/objecte/{id}/image")
    suspend fun updateImgObjecte(
        @Path("id") id: Int,
        @Part image: MultipartBody.Part
    ): Response<String>

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