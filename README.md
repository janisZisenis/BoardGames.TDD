# BoardGames.TDD Kata

## Introduction

This project is meant to be a Kata to train my personal skills in **designing high quality software** using the **test-driven development** (TDD) initially described by Kent Beck (TDD - By Example, 2002). In addition to that I forced to apply (or at least tried to apply in every single test) Osherove's aspects of **high quality unit tests** (The Art of Unit Testing, 2014). Most of the patterns (e.g. logstring), principles (e.g. Direct/Indirect Input/Output), prefixes/suffixes (e.g. for Test Doubles) and so on are to be found in the following books:

* [Test Driven Development. By Example; Kent Beck](https://www.amazon.de/s?k=tdd+by+example&hvadid=174418276756&hvdev=c&hvlocphy=9043313&hvnetw=g&hvpos=1t1&hvqmt=e&hvrand=17704527276239390355&hvtargid=kwd-11367315361&tag=googhydr08-21&ref=pd_sl_udxkl5w9f_e)
* [The Art of Unit Testing; Roy Osherove](https://www.amazon.de/Art-Unit-Testing-Roy-Osherove/dp/1617290890/ref=sr_1_1?hvadid=174672266071&hvdev=c&hvlocphy=9043313&hvnetw=g&hvpos=1t1&hvqmt=e&hvrand=2716020097760218298&hvtargid=kwd-10979390115&keywords=the+art+of+unit+testing&qid=1558288735&s=gateway&sr=8-1)
* [xUnit Test Patterns: Refactoring Test Code, Gerard Meszaros](https://www.amazon.de/xUnit-Test-Patterns-Refactoring-Signature/dp/0131495054/ref=sr_1_fkmrnull_1?crid=2ONRXE79AP40N&keywords=xunit+test+patterns&qid=1558288764&s=gateway&sprefix=xunit+test%2Caps%2C165&sr=8-1-fkmrnull)
* [Growing Object-Oriented Software, Guided By Tests; Steve Freeman and Nat Pryce](https://www.amazon.de/s?k=growing+object+oriented+software+guided+by+tests&crid=36Y4UQD5PG64Q&sprefix=Growing+object+orie%2Caps%2C175&ref=nb_sb_ss_i_1_19)

For those who are new to TDD, those are the rules and aspects I strictly obeyed:
 * Rules/Phases of the TDD cycle:
   * Red - Write a failing unit test!
   * Green - Make the test pass in the simplest manner you can think of!
   * Refactoring - Clean up (and generify the production code if possible)!
   * Repeat!
* Aspects of high quality unit tests:
   * Trustworthiness (through consistency and isolation)
   * Readability
   * Maintainability

... nevertheless I won't omit the general tool set of the software craftmanship, the design principles! Especially in the phase Red, SOLID (etc.) support the programmer to discover different way's a specific task/behaviour can be represented in software. (I recognized that those tests applying the principles are the the tests the most readable, maintainable and trustworthy).

Besides training my skills in test-driving a system, this project serves as a personal investigation to which extent the test-centric development and pure following of the TDD rules/instructions leads to high quality systems by itself. To evaluate the quality of the system I use the well-known metrics like cohesion and coupling and McCall's internal quality factors.

If your feel excited about TDD and the questions I try to answer myself, free to enter my code and take your own conclusions. In here I don't describe the evolution of the project, this is rather a "look-what-happens-to-the-outcome-if-your-TDDing".

## The Exercise - BoardGames App

As exercise I decided to implement a system containing the most known board games in java, which are to be executed as gui application (using JavaFX) as well as console application.

The intended board games are (linked games are in progress):

* [TicTacToe]()
* Conway's Game of Life (*coming soon*)
* Four in a Row (*coming soon*)
* BattleShip (*coming soon*)
* Draughts (*coming soon*)
* Chess (*coming soon*)

## *NOTE:*
You might judge the documented systems and the code as overdesigned in comparison to the domain logic (for example TicTacToe), but especially in Katas which are explicitly meant to train your design skills, **not a too high extent of design should considered to be bad**. It's rather **the lack of design which should be considered as a lack of discipline**.

Have fun exploring my work, enjoy!
