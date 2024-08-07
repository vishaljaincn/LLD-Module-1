package org.example.java8practicequestions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Concat2Streams {

    public static void main(String[] args) {
        List<Integer> integerList1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> integerList2 = Arrays.asList(5, 6, 7, 8);
        List<Integer> stream = Stream.concat(integerList1.stream(), integerList2.stream()).toList();
        List<Integer> stream1 = Stream.of(integerList1, integerList2).
                flatMap(Collection::stream).
                toList();
//		You cannot print a stream inside sysout
//		System.out.println(stream);
        stream.forEach(System.out::print);
        System.out.println();
        stream1.forEach(System.out::print);


    }

}
