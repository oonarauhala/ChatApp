package com.example.chatclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.net.InetAddress
import java.net.Socket
import java.util.*

private const val TAG = "MyActivity"

class MainActivity : AppCompatActivity() {

    //messages to show on screen with recyclerView (List of strings)
    private val myData: MutableList<String> = mutableListOf()
    private var username = ""

    //lateinit creation for socket
    lateinit var s: Socket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Creating adapter
        val adapter = MyRecyclerViewAdapter(this, myData)

        //Attach layout manager and adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        Thread(Runnable{    //Thread for socket
            s = Socket(InetAddress.getByName("10.0.2.2"), 30001)
            Log.i(TAG, "Socket created")

            val scan = Scanner(s.getInputStream())

            while(true){    //Loop for reading new messages
                val input = scan.nextLine()
                myData.add(input)
                runOnUiThread {     //notify adapter on new messages
                    adapter.notifyDataSetChanged()
                }
            }
        }).start()
    }

    fun sendMessage(view: View){
        val editText = findViewById<EditText>(R.id.editText)
        val message = editText.text.toString()
        if (username!="" && message != "") {     //if username is not empty, write message

            Thread {
                val writer = PrintStream(s.outputStream)
                writer.println(message)
                writer.flush()
                //clear editText box
                editText.text.clear()
            }.start()
        }
        if (username=="") {       //if username is empty, don't write message
            Toast.makeText(this, "Enter your username first", Toast.LENGTH_SHORT).show()
            editText.text.clear()
        }
    }

    fun sendUsername(view: View){ //doesn't open new activity, only sends username to server
        val editText3 = findViewById<EditText>(R.id.editText3)

        if (editText3.text.toString() == username && editText3.text.toString() == ""){ //if entered username is the same
            Toast.makeText(this, "Input a new username", Toast.LENGTH_SHORT).show()
            editText3.text.clear()
        }

        if  (editText3.text.toString()!= username) { //if entered username is new
            username = editText3.text.toString()
            Toast.makeText(this, "Username set!", Toast.LENGTH_SHORT).show()

            Thread {
                val writer = PrintStream(s.outputStream)
                writer.println(":user")     //send :user command to server
                writer.flush()
                writer.println(username)
                writer.flush()
                editText3.text.clear()
            }.start()
        }
    }
}
