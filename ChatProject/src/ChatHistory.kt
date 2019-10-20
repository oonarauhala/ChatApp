//ChatHistory keeps a log on all messages sent

object ChatHistory: ChatObservable{

    private var history = mutableListOf<ChatMessage>()
    private var observers = mutableSetOf<ChatObserver>()

    fun insert(message: ChatMessage){
        history.add(message)
        notifyObserver(message)
    }

    override fun toString(): String{

        var historyString = ""

        for (i in history){
            var iString = i.toString()
            historyString += "\n\r$iString"
        }
        return historyString
    }

    override fun registerObserver(who: ChatObserver) {
        observers.add(who)
    }

    override fun deregisterObserver(who: ChatObserver) {
        observers.remove(who)
    }

    override fun notifyObserver(message: ChatMessage) {
        for (i in observers)
            i.newMessage(message)
    }
}