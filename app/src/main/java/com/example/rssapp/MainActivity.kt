package com.example.rssapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var mainRV : RecyclerView
    lateinit var questions : ArrayList<Questions>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRV = findViewById(R.id.myRV)
        FetchQuestions().execute()
    }

    private inner class FetchQuestions : AsyncTask<Void, Void, ArrayList<Questions>>() {
        val parser = XMLParser()
        override fun doInBackground(vararg params: Void?): ArrayList<Questions> {
            val url = URL("https://stackoverflow.com/feeds")
            val urlConnection = url.openConnection() as HttpURLConnection
            questions =
                urlConnection.inputStream?.let {
                    parser.parse(it)
                }
                        as ArrayList<Questions>
            return questions
        }

        override fun onPostExecute(result: ArrayList<Questions>?) {
            super.onPostExecute(result)
            mainRV.adapter = result?.let { QuestionsRVAdapter(it) }
            mainRV.layoutManager = LinearLayoutManager(applicationContext)
        }

    }
}





