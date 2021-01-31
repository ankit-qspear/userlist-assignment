package com.example.assignmentuserlist.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignmentuserlist.DataChangedListener
import com.example.assignmentuserlist.R
import com.example.assignmentuserlist.databinding.SingleRowBinding
import com.example.assignmentuserlist.models.User
import com.example.assignmentuserlist.sharedprefrences.MySharedPrefrences


class UserListAdapter(
    private val context: Context,
    private val list: ArrayList<User>,
    private val mySharedPrefrences: MySharedPrefrences,
    private val dataChangedListener: DataChangedListener
) :
    RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {



    inner class MyViewHolder(val binding: SingleRowBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.dailyWage.setBackgroundColor(Color.TRANSPARENT)
        }
        var isEdit = false

        fun bind(user: User) {
            binding.personName.text = user.name.substring(0,1) + ". " + user.lastName
            binding.dailyWage.setText(user.dailyWage.toString())
            binding.txtDays.text = "${user.noOfDays} Days"
            Glide.with(binding.root.context).load(user.picture).placeholder(R.drawable.person).error(
                R.drawable.person
            ).circleCrop().into(binding.imgPerson);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleRowBinding>(
            inflater,
            R.layout.single_row,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
        val btn = holder.binding.btnEdit
        val edt = holder.binding.dailyWage
        btn.setOnClickListener {
            if (!holder.isEdit) {
                edt.setBackgroundResource(R.drawable.edit_text_background)
                edt.isEnabled = true
                edt.requestFocus()
                val imm: InputMethodManager? =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm!!.showSoftInput(edt, InputMethodManager.SHOW_IMPLICIT)
                holder.isEdit = true
                btn.setImageDrawable(context.getDrawable(R.drawable.save))
            }
            else{
                if (!edt.text.equals(""))
                {
                    mySharedPrefrences.addString(list[position].id, edt.text.toString())
                    dataChangedListener.onWageChanged(edt.text.toString().toInt(), position)
                    edt.setBackgroundColor(Color.TRANSPARENT)
                    edt.isEnabled = false
                    holder.isEdit = false
                    btn.setImageDrawable(context.getDrawable(R.drawable.edit))
                }else
                    Toast.makeText(
                        holder.binding.root.context,
                        "Please Enter A Value",
                        Toast.LENGTH_SHORT
                    ).show()

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}