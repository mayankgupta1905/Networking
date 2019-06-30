package com.mayank.networking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_github.view.*

class GithhubAdapter (val context: Context,val arrayList:ArrayList<GithubUser>): RecyclerView.Adapter<GitHubViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubViewHolder {
        val inflator =LayoutInflater.from(context)
        return GitHubViewHolder(inflator.inflate(R.layout.item_github,parent,false))
    }

    override fun getItemCount(): Int =arrayList.size

    override fun onBindViewHolder(holder: GitHubViewHolder, position: Int) {
        val user = arrayList[position]
        holder.bind(user)
    }

}

class GitHubViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    fun bind(user: GithubUser) {
        with(itemView){
            name.text = user.login
            Picasso.get().load(user.avatar_url).into(Avatar)
            login.text = user.id.toString()
        }
    }
}