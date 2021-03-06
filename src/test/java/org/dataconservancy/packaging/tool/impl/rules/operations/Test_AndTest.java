/*
 * Copyright 2014 Johns Hopkins University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dataconservancy.packaging.tool.impl.rules.operations;

import java.io.File;

import java.util.Arrays;

import org.dataconservancy.packaging.tool.impl.rules.FileContext;
import org.dataconservancy.packaging.tool.impl.rules.FileContextImpl;
import org.dataconservancy.packaging.tool.impl.rules.TestOperation;
import org.dataconservancy.packaging.tool.model.rules.TestSpec;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Test_AndTest {

    private static final FileContext whatever =
            new FileContextImpl(new File("/"), false);

    @Test
    public void singleOperandTrueTest() {
        Test_And and = new Test_And();
        and.setConstraints(new BooleanOperation(new Boolean[] {true, true, true}));

        assertTrue(Arrays.equals(and.operate(whatever), new Boolean[] {true}));
    }

    @Test
    public void singleOperandFalseTest() {
        Test_And and = new Test_And();
        and.setConstraints(new BooleanOperation(new Boolean[] {true, false,
                true}));

        assertTrue(Arrays.equals(and.operate(whatever), new Boolean[] {false}));
    }

    @Test
    public void multipleOperandTrueTest() {
        Test_And and = new Test_And();
        and.setConstraints(new BooleanOperation[] {
                new BooleanOperation(new Boolean[] {true, true, true}),
                new BooleanOperation(new Boolean[] {true, true, true}),
                new BooleanOperation(new Boolean[] {true, true, true})

        });

        assertTrue(Arrays.equals(and.operate(whatever), new Boolean[] {true}));
    }

    @Test
    public void multipleOperandFalseTest() {
        Test_And and = new Test_And();
        and.setConstraints(new BooleanOperation[] {
                new BooleanOperation(new Boolean[] {true, true, true}),
                new BooleanOperation(new Boolean[] {true, false, true}),
                new BooleanOperation(new Boolean[] {true, true, true})

        });

        assertTrue(Arrays.equals(and.operate(whatever), new Boolean[] {false}));
    }

    private class BooleanOperation
            implements TestOperation<TestOperation<?>> {

        private final Boolean[] results;

        public BooleanOperation(Boolean[] booleans) {
            results = booleans;
        }

        @Override
        public void setConstraints(TestOperation<?>... constraints) {
            /* Nothing */
        }

        @Override
        public Boolean[] operate(FileContext fileContext) {
            return results;
        }

        @Override
        public void setParams(TestSpec params) {
            /* Nothing */
        }

    }
}
