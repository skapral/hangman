/*
 * The MIT License
 *
 * Copyright 2020 Kapralov Sergey.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package hangman.round;

import hangman.console.Console;
import hangman.maskedword.MaskedWord;
import player.mistakes.Mistakes;

/**
 *
 * @author Kapralov Sergey
 */
public class PlayerGuessing implements Round {
    private final MaskedWord word;
    private final Mistakes mistakes;
    private final Console console;

    public PlayerGuessing(MaskedWord word, Mistakes mistakes, Console console) {
        this.word = word;
        this.mistakes = mistakes;
        this.console = console;
    }
    
    @Override
    public final void play() {
        String input = console.obtainInput("Guess a letter: ");
        if(word.openCharacter(input.charAt(0))) {
            console.printMessage("Hit!");
        } else {
            mistakes.increase();
            console.printMessage(String.format("Missed, mistake #%s out of %s", mistakes.current(), mistakes.total()));
        }
        console.printMessage("The word: " + word.string());
        console.printMessage("");
    }
}
