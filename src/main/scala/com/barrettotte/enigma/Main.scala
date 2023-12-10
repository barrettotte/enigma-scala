package com.barrettotte.enigma

@main
def main(): Unit = {
  val rotorI = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 'Q')
  val rotorII = new Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", 'E')
  val rotorIII = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V')
  val reflectorB = new Rotor("YRUHQSLDPXNGOKMIEBFZCWVJAT")
  val plugboard = Map(
    'A' -> 'M', 'F' -> 'O', 'G' -> 'I', 'L' -> 'R',
    'M' -> 'A', 'O' -> 'F', 'I' -> 'G', 'R' -> 'L'
  )
  val enigmaMachine = new EnigmaMachine(rotorI, rotorII, rotorIII, reflectorB, plugboard)

  var msg = "HELLO WORLD!"
  var encrypted = enigmaMachine.encrypt(msg)
  println(s"message:   $msg\nencrypted: $encrypted")

  println("-------------------------------------")
  enigmaMachine.resetRotors()

  msg = "HELLO"
  encrypted = enigmaMachine.encrypt(msg)
  println(s"message:   $msg\nencrypted: $encrypted")

  println("-------------------------------------")
  enigmaMachine.resetRotors()

  msg = "THIS IS A LONGER SENTENCE TO TEST IF ROTORS TURNOVER CORRECTLY. I SURE HOPE IT DOES..."
  encrypted = enigmaMachine.encrypt(msg)
  println(s"message:   $msg\nencrypted: $encrypted")
}
