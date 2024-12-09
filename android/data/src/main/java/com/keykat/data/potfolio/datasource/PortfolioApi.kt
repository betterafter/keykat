package com.keykat.data.potfolio.datasource

import com.keykat.data.potfolio.dto.PortfolioDto
import retrofit2.http.GET

interface PortfolioApi {
    @GET("/api/v1/portfolio")
    suspend fun getPortfolio(): retrofit2.Response<List<PortfolioDto>>
}