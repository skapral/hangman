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

import hangman.console.Console;
import hangman.round.Round;
import hangman.winningcondition.GameOverCondition;

/**
 *
 * @author Kapralov Sergey
 */
public class TurnBasedGame implements Game {
    private final Round round;
    private final GameOverCondition gameOverCondition;
    private final Console console;

    public TurnBasedGame(Round round, GameOverCondition gameOverCondition, Console console) {
        this.round = round;
        this.gameOverCondition = gameOverCondition;
        this.console = console;
    }

    @Override
    public final void start() {
        while(gameOverCondition.gameOver() == GameOverCondition.State.IN_PROGRESS) {
            round.play();
        }
        GameOverCondition.State state = gameOverCondition.gameOver();
        switch(state) {
            case VICTORY: console.printMessage("You won!\n"); return;
            case DEFEAT: console.printMessage("You lost\n"); return;
            default: throw new RuntimeException("Game ended up in unexpected state --- " + state);
        }
    }
}
