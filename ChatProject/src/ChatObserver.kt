interface ChatObserver {        //CI & ChatConsole are observers to ChatHistory
    fun newMessage(message: ChatMessage)
}


interface ChatObservable{       //ChatHistory is observable
    fun registerObserver(who: ChatObserver)
    fun deregisterObserver(who: ChatObserver)
    fun notifyObserver(message: ChatMessage)
}