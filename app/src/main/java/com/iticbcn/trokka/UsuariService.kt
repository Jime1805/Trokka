package com.iticbcn.trokka

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UsuariService {
    @POST("/trokka/usuario")
    suspend fun postUsers(
        @Body usuario: UsuariRequest
    ): Response<String>

    @GET("/trokka/usuario")
    suspend fun getAllUsers(): Response<List<UsuariResponse>>

    @GET("/trokka/usuario/id/{id}")
    suspend fun getUserById(
        @Path("id") id: Long
    ): Response<UsuariResponse>

    @GET("/trokka/usuario/nombre/{nombre}")
    suspend fun getUserByName(
        @Path("nombre") nombre: String
    ): Response<UsuariResponse>

    @PUT("/trokka/usuario/canvio_nombre/{nombre_nuevo}/{nombre_viejo}")
    suspend fun putUserName(
        @Path("nombre_nuevo") nombre_nuevo: String,
        @Path("nombre_viejo") nombre_viejo: String
    ):Response<String>

    @PUT("/trokka/usuario/canvio_contrasenya/{nombre}/{contrasenya}")
    suspend fun putUserPass(
        @Path("nombre") nombre: String,
        @Path("contrasenya") contrasenya: String
    ): Response<String>

    @DELETE("/trokka/usuario/id/{id}")
    suspend fun deleteUserById(
        @Path("id") id: Long
    ): Response<String>

    @DELETE("/trokka/usuario")
    suspend fun deleteUser(
        @Body usuario: UsuariRequest
    ): Response<String>
}