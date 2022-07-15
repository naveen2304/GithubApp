package com.example.githubapp

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface Gitservice{
    @GET("orgs/{organisation}/repos")
    suspend fun getrepos(@Path("organisation") organisation: String): Response<List<Repodetails>>
}
