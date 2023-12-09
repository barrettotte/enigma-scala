package com.barrettotte.enigma

@main
def main(): Unit = {
  val msg = "HELLO"
  // val msg = "THIS IS A LONG SENTENCE TO TEST ROTORS. ABCDEFGHIJKLMNOPQRSTUVWXYZ. HELLO WORLD!"
  println(s"message:   $msg")

  val rotorI = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 0, 'Q')
  val rotorII = new Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", 0, 'E')
  val rotorIII = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 0, 'V')
  val reflectorB = new Rotor("YRUHQSLDPXNGOKMIEBFZCWVJAT")
  val plugboardMappings = Map(
    'A' -> 'M', 'F' -> 'O', 'G' -> 'I', 'L' -> 'R',
    'M' -> 'A', 'O' -> 'F', 'I' -> 'G', 'R' -> 'L'
  )
  val enigmaMachine = new EnigmaMachine(rotorI, rotorII, rotorIII, reflectorB, plugboardMappings)

  val encrypted = enigmaMachine.encrypt(msg)
  println(s"Encrypted: $encrypted")
}
