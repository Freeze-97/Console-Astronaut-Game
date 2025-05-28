# 🧑‍🚀 Console-Astronaut-Game

A Java-based console game framework inspired by *Among Us*. This application simulates a game environment with different types of astronauts (players), allowing for gameplay where crewmates complete tasks and impostors attempt sabotage.

## 🧑‍🚀 Overview

This project demonstrates core object-oriented programming principles using Java. The game logic is implemented using inheritance, interfaces, and a shared player structure. It is designed to be run in a console environment.

## 🎮 Game Concept

- **Blue Astronauts**: Represent the crewmates. Their mission is to complete tasks and identify impostors.
- **Red Astronauts**: Represent the impostors. Their role is to sabotage progress and freeze other crewmates.

Both astronaut types extend from the abstract `Player` class, which contains common functionality and maintains a static array of all players in the game.

## 🧩 Structure

### Key Classes:

- `Player.java`: Abstract base class for all astronauts. Includes shared attributes and stores all players in a `Player[]` array.
- `BlueAstronaut.java`: A specific implementation of a crewmate.
- `RedAstronaut.java`: A specific implementation of an impostor.
- `Crewmate.java`: Interface defining behaviors for crewmates (e.g., doTask).
- `Impostor.java`: Interface defining behaviors for impostors (e.g., freezePlayer, sabotage).
- `Gameplay.java`: Contains the `main` method and sets up the game simulation.

## 🛠️ How to Run

1. Clone or download the repository.
2. Compile the Java files:
```bash
   javac *.java
```
Run the game:
```bash
java Gameplay
```
