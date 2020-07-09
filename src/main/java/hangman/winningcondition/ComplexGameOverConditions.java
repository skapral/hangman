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
package hangman.winningcondition;

import io.vavr.collection.List;

/**
 *
 * @author Kapralov Sergey
 */
public class ComplexGameOverConditions implements GameOverCondition {
    private final List<GameOverCondition> conditions;

    public ComplexGameOverConditions(List<GameOverCondition> conditions) {
        this.conditions = conditions;
    }
    
    public ComplexGameOverConditions(GameOverCondition... conditions) {
        this(List.of(conditions));
    }

    @Override
    public final State gameOver() {
        return conditions
                .map(GameOverCondition::gameOver)
                .find(state -> state != State.IN_PROGRESS)
                .getOrElse(State.IN_PROGRESS);
    }
}
