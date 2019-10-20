import java.time.LocalDateTime

class ChatMessage(var text: String, var sender: String, var time: LocalDateTime = LocalDateTime.now()){


    override fun toString(): String{

        var formattedTime = time.withNano(0)

        return "$formattedTime, $sender: $text"
    }




}