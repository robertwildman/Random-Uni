/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author rober
 */
public class Lab1 {
    
    public static void main(String[] args) {
    DiceV1 dice = new DiceV1(); // instantiate the dice
    int total = dice.spin(); // spin the dice
    int die1 = dice.die1(); // get die1 value
    int die2 = dice.die2(); // get die2 value
    System.out.println("first die: " + die1);
    System.out.println("second die: " + die2);
    System.out.println();
    System.out.println("total for spin is: " + total);
   }
    
}
