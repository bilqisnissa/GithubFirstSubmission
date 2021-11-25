package com.muflihunnisa.githubfirstsubmission

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.muflihunnisa.githubfirstsubmission.data.model.Users
import com.muflihunnisa.githubfirstsubmission.data.repository.DataUsers
import com.muflihunnisa.githubfirstsubmission.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private var list = ArrayList<Users>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        getData()
        showList()
    }

    private fun getData() {
        val jsonFile = getJsonDataFromAssets(applicationContext, "github_user.json")
        val gson = Gson()
        val data : DataUsers = gson.fromJson(jsonFile, DataUsers::class.java)
        list = data.users
    }

    private fun getJsonDataFromAssets(context: Context, dataFile : String): String? {
        val jsonString : String
        try {
            jsonString = context.assets.open(dataFile).bufferedReader().use {
                it.readText()
            }
        }catch (ioException : IOException){
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun showList() {
        mainBinding.rvMain.setHasFixedSize(true)
        val userAdapter = UserAdapter(list)
        mainBinding.rvMain.layoutManager = LinearLayoutManager(this)
        mainBinding.rvMain.adapter = userAdapter

        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(users: Users) {
                setSelectedUser(users)
            }

        })
    }

    private fun setSelectedUser(users: Users) {
        val intentDetail = Intent(this@MainActivity, DetailActivity::class.java)
        intentDetail.putExtra(DetailActivity.EXTRA_USER, users)
        startActivity(intentDetail)
    }

    companion object {
        fun getLaunchService(from: Context) =
            Intent(from, MainActivity::class.java).apply {
                addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                            or Intent.FLAG_ACTIVITY_CLEAR_TASK
                )
            }
    }
}