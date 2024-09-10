package com.gildedrose;

import java.util.Arrays;

public enum ItemFunctions {
    ;

    static ItemFunction compose(ItemFunction... functions) {
        return Arrays.stream(functions)
            .reduce((f1, f2) -> f1.andThen(f2))
            .orElse(i -> i);
    }
}
