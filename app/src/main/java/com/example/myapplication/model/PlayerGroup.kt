package com.example.myapplication.model

//ovako importujute klase/fajlove u svoje activitije
//import com.example.myapplication.model.Player
//import com.example.myapplication.model.winsComparator
//import com.example.myapplication.model.answersComparator
import kotlinx.serialization.Serializable

@Serializable
class PlayerGroup(private var name: String) {
    private var numOfPlayers : Int = 0
    private var plGroup = mutableListOf<Player>()
    var listOfPairs = mutableListOf<Pair<Player, Player>>()

    //kontrola plGroup-a
    fun add(player: Player){
        plGroup.add(player)
        numOfPlayers++
    }
    //brisanje clana iz grupe mi nema smisla posto kao jednom dodat clan ce uvek ostati u grupi

    //geteri
    fun getPGName(): String{
        return name
    }
    fun getPGList(): List<Player>{
        var players = mutableListOf<Player>()
        for(player in plGroup)
            players.add(player)
        return players
    }

    //seteri
    fun setPGName(pgName: String){
        name = pgName
    }
    fun setPGList(pgList: List<Player>){
        plGroup = mutableListOf()
        for(player in pgList)
            plGroup.add(player)
    }

    //fje za rad sa igracima
    fun makeRandomPairs(){
        for(player in plGroup) {
            while (!player.isPaired()) {
                val random = (0 until plGroup.size).random()
                if (!plGroup[random].isPaired()) {
                    listOfPairs.add(Pair(player, plGroup[random]))
                    player.setPaired(true)
                    plGroup[random].setPaired(true)
                }
            }
        }
    }
    fun makeNonRandomPairs() {
        //ako bude moglo, dodacemo (mnogo ubijam glavu sta da budu parametri i kako da ne brisem listOfPairs)
    }
    fun sortByWins() : List<Player>{
        var sortedPLGroup = mutableListOf<Player>()
        for(player in plGroup)
            sortedPLGroup.add(player)
        return sortedPLGroup.sortedWith(winsComparator)
    }
    fun sortByAnswers() : List<Pair<Player, Player>>{
        var sortedPLGroup = mutableListOf<Pair<Player, Player>>()
        for(pair in listOfPairs)
            sortedPLGroup.add(pair)
        return sortedPLGroup.sortedWith(answersComparator)
    }
}

//main za testiranje
fun main(){
    val pg1 = PlayerGroup("PG1")

}