object ChatConsole: ChatObserver {

    override fun newMessage(message: ChatMessage) {
        println(message)
    }
}