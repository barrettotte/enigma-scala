package com.barrettotte.enigma

@main
def main(): Unit = {
  val msg = "HELLO WORLD!" // TODO: get message from args
  // TODO: get plugboard from args?

  val enigma = new EnigmaMachine(
    "EKMFLGDQVZNTOWYHXUSPAIBRCJ", // notch Q ?
    "AJDKSIRUXBLHWTMCQGZNPYFVOE", // notch E ?
    "BDFHJLCPRTXVZNYEIWGAKMUSQO", // notch V ?
    "YRUHQSLDPXNGOKMIEBFZCWVJAT", // reflector B
    Map()
//    Map('A' -> 'M', 'F' -> 'O', 'G' -> 'I', 'L' -> 'R') // TODO: fix
  )
  println(enigma)

  // TODO: encrypt or decrypt dependant on args
  val encrypted = enigma.encrypt(msg)

  println(s"message:   $msg")
  println(s"encrypted: $encrypted")
}
