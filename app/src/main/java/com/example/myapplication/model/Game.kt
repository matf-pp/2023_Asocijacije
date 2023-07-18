package com.example.myapplication.model

import java.io.Serializable

class Game : Serializable {
    //ne moze da se zove playerGroup zbog JVM, ima isti naziv za neki modul
    private var plGroup : PlayerGroup = PlayerGroup("")
    private var numOfWordsPerPlayer : Int = 0
    private var wordsBank = mutableListOf<String>()
    private var currentPlayer : Player = Player("")
    private var currentGamePhase : Int = 1
    private var listOfPairs = mutableListOf<Pair<Player, Player>>()

    fun getNumOfWordsPerPlayer() : Int {
        return numOfWordsPerPlayer
    }

    fun getPairs() : MutableList<Pair<Player, Player>> {
        return listOfPairs
    }

    fun setPlayerGroup(pg: PlayerGroup) {
        plGroup = pg
    }

    fun setNumOfWordsPerPlayer(n : Int) {
        numOfWordsPerPlayer = n
    }

    fun setListOfPairs(list : MutableList<Pair<Player, Player>> ) {
        listOfPairs = list
    }

    fun makeRandomPairs(){
        val list = mutableListOf<Pair<Player, Player>>()
        val players : MutableList<Player> = plGroup.getPlayers()
        players.shuffle()
        for(i in players.indices) {
            while (!players[i].isPaired()) {
                val random = (0 until players.size).random()
                if (!players[random].isPaired() && players[i]!=players[random]) {
                    list.add(Pair(players[i], players[random]))
                    players[i].setPaired(true)
                    players[random].setPaired(true)
                }
            }
        }
        listOfPairs = list
        for(player in players)
            player.setPaired(false)
    }

    fun sortedByAnswers() : List<Pair<Player, Player>>{
        return listOfPairs.sortedWith(answersComparator)
    }

    fun listForWordEntering() : MutableList<Player> {
        val pom : MutableList<Player> = mutableListOf()
        for (pair in listOfPairs)
            pom.add(pair.first)
        for (pair in listOfPairs)
            pom.add(pair.second)
        return pom
    }

    fun sendWordsToWordBank(player: Player) {
        for (w in player.getWords())
            wordsBank.add(w)
    }
//ove 2 fje mogu posluziti u BetweenPlayersActivity
    fun pairedWith (player: Player) : Player {
        var theirPair = Player("")
        var pair = listOfPairs.find { it.first == player }
        if (pair == null) {
            pair = listOfPairs.find { it.second == player }
            //nece se desiti da bude null
            theirPair = pair!!.first
        }
        else
            theirPair = pair.second

        return theirPair
    }

    fun numOfCorrectAnswersInPair(player: Player) : Int {
        return player.getNumOfCorrectAnswers() + pairedWith(player).getNumOfCorrectAnswers()
    }

    override fun toString(): String {
        return this.plGroup.toString()
    }
}


fun main() {
    val g1 = Game()
    val pg = PlayerGroup("test")
    val pl1 = Player("tanja")
    val pl2 = Player("anja")
    val pl3 = Player("vaske")
    val pl4 = Player("zile")

    pg.add(pl1)
    pg.add(pl2)
    pg.add(pl3)
    pg.add(pl4)

    g1.setPlayerGroup(pg)
    println("prazna")
    println(g1.getPairs())
    g1.makeRandomPairs()
    println("random")
    println(g1.getPairs())
    print("lista za unos rijeci: ")
    println(g1.listForWordEntering())

    g1.makeRandomPairs()
    println("opet random")
    println(g1.getPairs())
    print("lista za unos rijeci: ")
    println(g1.listForWordEntering())
}