//Server functionality is implemented here, including server socket and thread creation

import java.net.ServerSocket

class Server {

    fun serve(){
        val serverSocket = ServerSocket(30001, 3)

        ChatHistory.registerObserver(ChatConsole)       //Register ChatConsole as history observer

        println("We have port " + serverSocket.localPort)
        try {
            while (true) {

                val socket = serverSocket.accept()
                println("Accepted")
                val ci = CommandInterpreter(socket.getInputStream(), socket.getOutputStream())
                val thread1 = Thread(ci)
                thread1.start()
            }

        } catch (e: Exception) {
            println("Got exception: ${e.message}")
        } finally {
            println("All serving done.")
        }
    }
}