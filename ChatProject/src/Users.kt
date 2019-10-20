//Users are stored in a hash set that can be manipulated with functions

object Users{
    var allNames = HashSet<String>()

    fun addUsername(name: String):Boolean{
        allNames.add(name)
        return (allNames.add(name))
    }

    fun removeUsername(name: String){
        allNames.remove(name)
    }

    fun checkDuplicate(name: String):Boolean{
        if(allNames.contains(name)){
            return true
        }
        else{return (false)}
    }

    override fun toString():String{
        var nameString = "Users are:"

        for (i in allNames){
            nameString += "\n\r$i"
        }
        return nameString
    }
}