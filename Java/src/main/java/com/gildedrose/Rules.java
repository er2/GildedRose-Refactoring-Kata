package com.gildedrose;

import java.util.List;

public class Rules {
    public static Rule compose(List<Rule> allRules) {
        return allRules.stream()
                .reduce(Rule.Composition::new)
                .orElseGet(() -> new RuleImpl(i -> true, i -> i));
    }
}
