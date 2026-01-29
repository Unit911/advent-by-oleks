package org.demchuko.y2015.day7;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public enum Operation {
    AND("AND"),
    OR("OR"),
    NOT("NOT"),
    LSHIFT("LSHIFT"),
    RSHIFT("RSHIFT"),
    NONE("NONE");
    ;

    final String text;
}
