package com.example.myapplication.model
import kotlinx.serialization.*

@Serializable
class Player(private val name: String) {
    private var numOfWins: Int = 0
    private var paired: Boolean = false
    private var numOfCorrectAnswers: Int = 0
    private var words =  mutableListOf<String>()

    //geteri
    fun getName(): String{
        return name
    }
    fun getNumOfWins(): Int{
        return numOfWins
    }
    fun getNumOfCorrectAnswers(): Int{
        return numOfCorrectAnswers
    }
    fun getWords() : List<String>{
        return words
    }

    //seteri
    fun setWords(newWords: List<String>){
        resetWords()
        for(word in newWords){
            words.add(word)
        }
    }

    fun addWord(word : String) {
        words.add(word)
    }

    fun setPaired(b: Boolean){
        paired = b
    }
    //random korisne fje
    fun winsIncrease(){
        numOfWins++
    }
    fun correctAnswersIncrease() {
        numOfCorrectAnswers++
    }
    private fun resetWords(){
        words = mutableListOf()
    }
    override fun toString(): String {
        return this.getName()
    }
    fun isPaired() : Boolean{
        return paired
    }

}

//main metod za testiranje fja
fun main(){
    val p1 = Player("P1")
    val p2 = Player("P2")
    val lista = mutableListOf<Player>()
    lista.add(p1)
    lista.add(p2)
    p1.winsIncrease()
    p2.correctAnswersIncrease()
    println(p1.getNumOfWins())
    println(p2.getNumOfCorrectAnswers())
}













