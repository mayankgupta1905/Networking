package com.mayank.networking

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GetData.setOnClickListener{
            updateTextView()
        }
        }

    private fun updateTextView() {
        val networkTask = NetworkTask()
        networkTask.execute("https://api.github.com/search/users?q=%22Mayank%20Gupta%22","https://www.github.com/")
    }

    inner class NetworkTask:AsyncTask<String,Int,String>() {
        override fun doInBackground(vararg url: String?): String? {
            val googleurl:URL = URL(url[0])
            val connection = googleurl.openConnection() as HttpURLConnection
            val isr = InputStreamReader(connection.inputStream)
            val bufferReader = BufferedReader(isr)
            val sb = StringBuilder()
            var buffer:String? = ""
            while (buffer!=null){
                sb.append(buffer)
                buffer = bufferReader.readLine()
            }

           return sb.toString()
        }

        override fun onPostExecute(result: String?) {

            val jsonData = JSONObject(result)
            val userArray = jsonData.getJSONArray("items")
            val userList = arrayListOf<GithubUser>()
//            for (i in 0..9) {
//
//                val user = GithubUser(
//                    (userArray[i] as JSONObject).getString("login"),
//                    (userArray[i] as JSONObject).getString("avatar_url"),
//                    (userArray[i] as JSONObject).getInt("id")
//                )
//                userList.add(user)
//            }

            val user = Gson().fromJson(result,Github::class.java)
            userList.addAll(user.items)

            githubrv.layoutManager = LinearLayoutManager(this@MainActivity)
            githubrv.adapter = GithhubAdapter(this@MainActivity,userList)
        }
    }
}
