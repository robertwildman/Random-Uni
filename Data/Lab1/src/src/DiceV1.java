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
public class DiceV1 implements IDice {

    private int die1,die2;
    public int spin() {
    die1 = randInt();
    die2 = randInt();
    return die1 + die2;
    }

    public int die1() {
    return die1;
    }

    public int die2() {
    return die2;
    }

    private int randInt() {
        return (int) Math.round(Math.random() * 10);
    }

}

