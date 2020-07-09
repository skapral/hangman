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
package hangman.game;

import hangman.console.IOConsole;
import hangman.maskedword.MaskedWordInMemory;
import hangman.round.PlayerGuessing;
import hangman.winningcondition.ComplexGameOverConditions;
import hangman.winningcondition.PlayingUntilAttemptsAreOver;
import hangman.winningcondition.PlayingUntilWordGuessed;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import player.mistakes.MistakesRAM;

/**
 *
 * @author Kapralov Sergey
 */
public class HangmanGame extends TurnBasedGame {
    private HangmanGame(AtomicInteger mistakesCount, Set<Character> attemptsStorage, InputStream input, OutputStream output, int maxMistakes) {
        super(
            new PlayerGuessing(
                new MaskedWordInMemory("abc", attemptsStorage),
                new MistakesRAM(mistakesCount, maxMistakes),
                new IOConsole(input, output)
            ),
            new ComplexGameOverConditions(
                new PlayingUntilWordGuessed(new MaskedWordInMemory("abc", attemptsStorage)),
                new PlayingUntilAttemptsAreOver(new MistakesRAM(mistakesCount, maxMistakes))
            ),
            new IOConsole(input, output)
        );
    }
    
    public HangmanGame(InputStream input, OutputStream output, int maxMistakes) {
        this(new AtomicInteger(), new HashSet<>(), input, output, maxMistakes);
    }
}
