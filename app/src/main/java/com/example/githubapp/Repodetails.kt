package com.example.githubapp

import retrofit2.Call

data class Repodetails (val name: String,val description: String, val html_url: String, val forks_count: Int, val watchers_count: Int, val size: Int, val language: String,val open_issues: Int,val default_branch: String)