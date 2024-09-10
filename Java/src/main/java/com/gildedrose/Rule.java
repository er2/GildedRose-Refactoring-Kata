package com.gildedrose;

public interface Rule extends ItemFunction, ItemPredicate {

    class Composition implements Rule {

        private final Rule rule1, rule2;

        public Composition(Rule rule1, Rule rule2) {
            this.rule1 = rule1;
            this.rule2 = rule2;
        }

        @Override
        public boolean appliesTo(Item item) {
            return rule1.appliesTo(item) || rule2.appliesTo(item);
        }

        @Override
        public Item apply(Item item) {
            if (rule1.appliesTo(item)) {
                return rule1.apply(item);
            } else if (rule2.appliesTo(item)) {
                return rule2.apply(item);
            }
            return item;
        }

    }

}
