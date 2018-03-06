/*
 * Copyright 2000-2014 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.tests.elements.abstracttextfield;

import static org.junit.Assert.assertEquals;

import com.vaadin.testbench.elements.*;
import com.vaadin.tests.elements.ComponentElementGetValue;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.tests.tb3.MultiBrowserTest;

/**
 *
 * @since
 * @author Vaadin Ltd
 */
public class AbstractTextElementSetValueTest extends MultiBrowserTest {
    private static final String TYPED_STRING = "this is typed string";

    @Before
    public void init() {
        openTestURL();
    }

    @Test
    public void textFieldSetValue() {
        checkType($(TextFieldElement.class).get(0),
                $(LabelElement.class).get(1));
    }

    @Test
    public void passwordFieldSetValue() {
        checkType($(PasswordFieldElement.class).get(0),
                $(LabelElement.class).get(2));
    }

    @Test
    public void textAreaSetValue() {
        checkType($(TextAreaElement.class).get(0),
                $(LabelElement.class).get(3));
    }

    @Test
    public void dateFieldSetValue() {
        DateFieldElement elem = $(DateFieldElement.class).get(0);
        LabelElement eventCount = $(LabelElement.class).get(4);
        // we can type any string in date field element
        elem.setValue(TYPED_STRING);
        // invalid values should stay unchanged
        assertEquals(TYPED_STRING, elem.getValue());
    }

    @Test
    public void richTextAreaSetValue(){
        RichTextAreaElement elem=$ (RichTextAreaElement.class).get(0);
        elem.setValue(TYPED_STRING);
        String actual=elem.getValue();
        assertEquals(TYPED_STRING,actual);
    }

    // helper methods
    // checks that setValue method works
    private void checkType(AbstractTextFieldElement elem,
            LabelElement eventCount) {
        // check first that the initial value is set
        assertEquals(AbstractTextElementSetValue.INITIAL_VALUE,
                elem.getValue());
        elem.setValue(TYPED_STRING);

        // check that typed value is the same
        assertEquals(TYPED_STRING, elem.getValue());

        // checks that there was only one change value event
        assertEquals("1", eventCount.getText());

    }
}
