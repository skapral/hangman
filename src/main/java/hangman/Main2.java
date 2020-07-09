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
package hangman;

import com.pragmaticobjects.oo.equivalence.base.EObjectHint;
import hangman.game.HangmanGame;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Kapralov Sergey
 */
@EObjectHint(enabled = false)
public class Main2 {
    private final HangmanGame game;
    private static final String[] WORDS = {
        "simplicity", "equality", "grandmother",
        "neighborhood", "relationship", "mathematics",
        "university", "explanation"
    };

    public Main2(HangmanGame game) {
        this.game = game;
    }

    public Main2(final InputStream in, final OutputStream out, final int m) {
        this(
            new HangmanGame(in, out, m)
        );
    }

    public static void main(final String... args) {
        new Main2(System.in, System.out, 5).start();
    }
    
    public final void start() {
        game.start();
    }
}
