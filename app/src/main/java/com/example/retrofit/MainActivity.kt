package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    var BASE_URL = "https://jsonplaceholder.typicode.com"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMyData();
    }

    private fun getMyData() {
        val retrofitbuilder = Retrofit.Builder()    //retrofit builder object
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(APIinterface::class.java)
        val retrofitData = retrofitbuilder.getData()
        retrofitData.enqueue(object : Callback<List<MydataItem>?> {
            override fun onResponse(
                call: Call<List<MydataItem>?>,
                response: Response<List<MydataItem>?>
            ) {
                val responseBody = response.body()!!
                val strindbulider = StringBuilder()
                for (myData in responseBody) {
                    strindbulider.append(myData.id)
                    strindbulider.append("\n")


                }
                data_Id.text = strindbulider
            }

            override fun onFailure(call: Call<List<MydataItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure" + t.message)
            }
        })
    }
}