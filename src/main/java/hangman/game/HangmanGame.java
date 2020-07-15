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

import com.pragmaticobjects.oo.memoized.chm.MemoryCHM;
import com.pragmaticobjects.oo.memoized.core.Memory;
import hangman.console.Console;
import hangman.console.IOConsole;
import hangman.maskedword.MaskedWordFromDictionary;
import hangman.maskedword.MaskedWordInMemory;
import hangman.round.PlayerGuessing;
import hangman.winningcondition.ComplexGameOverConditions;
import hangman.winningcondition.PlayingUntilAttemptsAreOver;
import hangman.winningcondition.PlayingUntilWordGuessed;
import io.vavr.collection.List;
import player.mistakes.MistakesRAM;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Kapralov Sergey
 */
public class HangmanGame extends TurnBasedGame {
    private static final String[] WORDS = {
            "simplicity", "equality", "grandmother",
            "neighborhood", "relationship", "mathematics",
            "university", "explanation"
    };

    public HangmanGame(List<String> dictionary, int maxMistakes, AtomicInteger mistakesCount, Set<Character> attemptsStorage, Console console, Random random, Memory memory) {
        super(
            new PlayerGuessing(
                new MaskedWordFromDictionary(dictionary, random, memory),
                new MistakesRAM(mistakesCount, maxMistakes),
                console
            ),
            new ComplexGameOverConditions(
                new PlayingUntilAttemptsAreOver(new MistakesRAM(mistakesCount, maxMistakes)),
                new PlayingUntilWordGuessed(new MaskedWordInMemory("abc", attemptsStorage))
            ),
            console
        );
    }

    public static HangmanGame newGame(InputStream is, OutputStream os, int mistakes) {
        return new HangmanGame(
            List.of(WORDS),
            mistakes,
            new AtomicInteger(),
            new HashSet<>(),
            new IOConsole(is, os),
            new Random(),
            new MemoryCHM()
        );
    }
}
