import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.util.*
//Command interpreter reads user input

class CommandInterpreter(input: InputStream, output: OutputStream): ChatObserver, Runnable{
    private val scan = Scanner(input)
    private var out = PrintStream(output)
    private var nimi = ""
    private var nimi2 = ""      //old name if name is changed

    override fun newMessage(message: ChatMessage) {
        out.println(message)
    }

    override fun run(){
        var inp = scan.nextLine()

        fun ask(){
            inp = scan.nextLine()
        }

        ChatHistory.registerObserver(this)      //register this commandInterpreter as char history observer

        while(true) {                            //set username loop
            if(inp ==":quit"){
                break
            }
            if (inp == ":user") {
                nimi = scan.nextLine()
                if(!Users.checkDuplicate(inp)){
                    Users.addUsername(nimi)
                    ask()
                } else{
                    ask()
                }
            } else {
                ask()
            }

            while (nimi!="") {       //loop after username is set

                    if (inp == ":quit") {
                        break
                    }
                    if (inp == ":history") {
                        out.println(ChatHistory.toString())
                        ask()
                    }
                    if (inp == ":users") {
                        out.println(Users.toString())
                        ask()
                    }
                    if (inp == ":user") {
                        nimi2 = scan.nextLine()
                        if (!Users.allNames.contains(nimi2)) {
                            Users.addUsername(nimi2)
                            Users.removeUsername(nimi)
                            nimi = nimi2
                            ask()
                        } else {
                            ask()
                        }
                    }
                    if ((inp == "") or (inp == " ")) {    //empty input/ input only whitespace
                        ask()
                    }
                    if (!inp.startsWith(":")){   //input is a message

                    val message = ChatMessage(inp, nimi)
                    ChatHistory.insert(message)
                    ask()
                    }
                    else{
                    ask()
                    }
            }
        }
    }
}
