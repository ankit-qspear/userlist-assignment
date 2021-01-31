package com.example.assignmentuserlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentuserlist.adapter.UserListAdapter
import com.example.assignmentuserlist.models.User
import com.example.assignmentuserlist.sharedprefrences.MySharedPrefrences
import com.example.assignmentuserlist.viewmodel.UserViewModel
import com.example.assignmentuserlist.viewmodel.UserViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , DataChangedListener {

    @Inject
    lateinit var viewModelFactory: UserViewModelFactory

    @Inject
    lateinit var mySharedPrefrences: MySharedPrefrences

    private lateinit var viewModel: UserViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerViewAdapter: UserListAdapter
    private val listUserData = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerMain)
        progressBar = findViewById(R.id.progressMain)

        progressBar.visibility = View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = UserListAdapter(this,listUserData,mySharedPrefrences,this)
        recyclerView.adapter = recyclerViewAdapter
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
        viewModel.userList.observe(this, Observer {
            Log.i("MyTag", it.toString())
            listUserData.clear()
            listUserData.addAll(processRemoteList(ArrayList(it)))
            recyclerViewAdapter.notifyDataSetChanged()
            progressBar.visibility = View.GONE
        })
    }

    private fun processRemoteList(list : ArrayList<User>) : ArrayList<User>{

        for (i in 0 until list.size) {
            val dailyWage =
                if (mySharedPrefrences.getString(list[i].id) != "") mySharedPrefrences.getString(
                    list[i].id
                ).toInt() else list[i].dailyWage
            val noOfDays = 10000 / dailyWage
            list[i].dailyWage = dailyWage
            list[i].noOfDays = noOfDays
        }
        list.sortBy {user ->
            user.dailyWage
        }
        list.reverse()
        return list
    }

    override fun onWageChanged(value: Int, index: Int) {
        listUserData[index].dailyWage = value
        listUserData[index].noOfDays = 10000/value
        listUserData.sortBy {user ->
            user.dailyWage
        }
        listUserData.reverse()
        recyclerViewAdapter.notifyDataSetChanged()
    }


}