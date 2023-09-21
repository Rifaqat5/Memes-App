package com.example.memeapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.memeapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {

    lateinit var uri : String
    val binding by lazy {

        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        getMeme()

        binding.nextBtn.setOnClickListener {

            getMeme()
        }

    }

    private fun getMeme() {


        binding.imageView.visibility=View.GONE
        binding.progressBar.visibility=View.VISIBLE
        var apiEndt : Api_Endt
        var retrofit = ClientApi.getClient()

        apiEndt = retrofit.create(Api_Endt::class.java)

        apiEndt.get_all_meme().enqueue(object : Callback<MyResponse>{
            override fun onResponse(call: Call<MyResponse>?, response: Response<MyResponse>?) {

                if(response!!.isSuccessful){
                    binding.imageView.visibility=View.VISIBLE
                    binding.progressBar.visibility=View.GONE
                    uri = response!!.body().url
                    Glide.with(this@MainActivity)
                        .load(uri)
                        .into(binding.imageView)
                }
            }

            override fun onFailure(call: Call<MyResponse>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, " Currently You are Offline", Toast.LENGTH_SHORT).show()
            }

        })
    }
}