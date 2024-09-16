package com.example.laptoppricethanachai

import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Field

interface ApiService {
    @FormUrlEncoded
    @POST("api/laptop")
    fun getLaptopPrice(
        @Field("processor_speed") processorSpeed: Double,
        @Field("ram_size") ramSize: Int,
        @Field("storage_capacity") storageCapacity: Int,
        @Field("screen_size") screenSize: Double,
        @Field("weight") weight: Double
    ): Call<PriceResponse>
}
