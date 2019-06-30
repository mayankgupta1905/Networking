package com.mayank.networking

data class GithubUser(
    val login:String,
    val avatar_url:String,
    val id:Int)

data class Github(
    val total_count:Int,
    val imcomplete_results:Boolean,
    val items:ArrayList<GithubUser>
)