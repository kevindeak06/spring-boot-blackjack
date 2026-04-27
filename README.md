# Spring Boot Blackjack (Java)

A web-based Blackjack game built with Java and Spring Boot. This project separates the backend game logic from a custom, responsive HTML/CSS frontend.

The game currently features a stable, fully functional local gameplay loop.
* Standard Blackjack rules implemented (hit, stand, dealer logic).
* Dynamic point calculation (including Ace value adjustment).
* Visual card rendering using external assets.
* Basic betting and balance system.

## Tech Stack
* **Backend:** Java, Spring Boot
* **Frontend:** HTML, CSS, Thymeleaf
* **Build Tool:** Maven

## Planned Features
* **Codebase Refactoring:** Translate the entire codebase and variables to English for international readability.
* **Session Management:** Move the game state from the Controller to `HttpSession` to support concurrent players in a web environment.
