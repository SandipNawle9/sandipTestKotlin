package com.mvvm.sandipteskotlin

import android.content.DialogInterface
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.sandipteskotlin.databinding.ActivityMainBinding
import com.mvvm.sandiptest.adapter.UserAdapter
import com.mvvm.sandiptest.helper.isInternetAvail
import com.mvvm.sandiptest.helper.toast
import com.mvvm.sandiptest.repository.database.entity.User
import com.mvvm.sandiptest.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() , UserAdapter.DeleteUserClick {
    var userViewModel: UserViewModel? = null
    private var userAdapter: UserAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        val recyclerView: RecyclerView = binding.viewUsers
        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter()
        userAdapter!!.setClickListner(this)
        recyclerView.adapter = userAdapter
        getAllUser()
    }

    private fun getAllUser() {
        userViewModel!!.getUsers().observe(this, Observer {
            if(it != null) {
                userAdapter?.setUserList(it)
            }else{
                getAllUser()
            }
        })
    }

    override fun onDeleteUserClick(user: User) {
        if(!isInternetAvail()){
            showDeleteDialog(user)
        }else{
            toast(getString(R.string.internet_message))
        }
    }

    private fun showDeleteDialog(user: User) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val inflater = layoutInflater

        builder.setMessage(R.string.delete_toast)
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
            userViewModel?.deleteUser(user)
            toast("User deleted")
            getAllUser()
        })

        builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()

        })
        builder.create().show()
    }
}
