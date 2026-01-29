package org.demchuko.y2015.day7;

import lombok.Builder;
import lombok.ToString;
import org.demchuko.core.hex.Int16;

import java.util.List;

@Builder
@ToString
public class Data {
    Int16 val; //holds value if it's found
    List<String> keys;  //hold reference to the keys it needs to calc value
    int shiftVal = 0; //hold value for shift
    Operation operation;

//    public Data(Data oldData) {
//        this.val = oldData.val;
//        this.keys = oldData.keys;
//        this.shiftVal = oldData.shiftVal;
//        this.operation = oldData.operation;
//    }
}
