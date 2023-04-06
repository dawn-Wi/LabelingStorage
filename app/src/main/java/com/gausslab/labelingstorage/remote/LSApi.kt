package com.gausslab.labelingstorage.remote

import com.gausslab.labelingstorage.model.Item
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface LSApi {
    @GET
    suspend fun submitItem(@Body toAdd: Item): Response<Item>
}