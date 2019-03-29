/*
 * Copyright (c) 2013 Denis Solonenko.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package ru.orangesoftware.financisto.activity;

import org.junit.Test;

import ru.orangesoftware.financisto.db.AbstractDbTest;
import ru.orangesoftware.financisto.model.Currency;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created with IntelliJ IDEA.
 * User: dsolonenko
 * Date: 2/14/13
 * Time: 12:26 AM
 */
public class CurrencySelectorTest extends AbstractDbTest {

    CurrencySelector selector;
    long currencyId;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        selector = new CurrencySelector(context, db, currencyId -> CurrencySelectorTest.this.currencyId = currencyId);
    }

    @Test
    public void test_should_add_selected_currency_as_default_if_it_is_the_very_first_currency_added() {
        //given
        givenNoCurrenciesYetExist();
        //when
        selector.addSelectedCurrency(1);
        //then
        Currency currency1 = db.load(Currency.class, currencyId);
        assertTrue(currency1.isDefault);

        //when
        selector.addSelectedCurrency(2);
        //then
        Currency currency2 = db.load(Currency.class, currencyId);
        assertTrue(currency1.isDefault);
        assertFalse(currency2.isDefault);
    }

    private void givenNoCurrenciesYetExist() {
        assertTrue(db.getAllCurrenciesList().isEmpty());
    }

}
