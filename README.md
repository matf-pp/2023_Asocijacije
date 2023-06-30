# 2023_Asocijacije

Aplikacija za pomoć u igranju igre "Asocijacije" koja se igra u tri faze.

## Ideja kako bi izgledao projekat

[Slika](Izgled_Aplikacije.jpg)
## Klase i Baza

Backend deo aplikacije:

### Player

Metodi: Svi geteri, SetPaired, SetNumOfWins, EnterWords, ToString, ComparebyWins, CompareByAnswers
Polja: String name, Bool paired, Int numOfWins, Words[] words, int numOfCorrectAnswers

### Word

Metodi: getWord, setWord, changeListOfWords
Polja: String word

### PlayerGroup

Metodi: getName, getPlayers, setPlayers, shufflePairs
Polja: Player[] players, String name

### Baza

Pristupa se preko imena grupe i izbacivaće listu prvih nekoliko igrača sa najviše pobeda
Čuvaće imena igrača kao i pojmove koje su oni upisivali u poslednjoj partiji te grupe