package com.gildedrose;

import java.util.Arrays;

public enum ItemFunctions {
    ;

    static ItemFunction compose(ItemFunction... functions) {
        return Arrays.stream(functions)
            .reduce((f1, f2) -> i -> f2.apply(f1.apply(i)))
            .orElse(i -> i);
    }
}
