package square;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MorseCode {
    public static void main(String[] args) {
        System.out.println(SolutionOne.encode(
                new int[]{1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1}));
        System.out.println(SolutionTwo.decode(
                new int[]{0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1,
                        0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0,
                        0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0,
                        1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0}));
    }
}

/*
Part 1
Morse Code is a way of encoding text into a binary signal of "on" and "off" efficiently. It's sort of equivalent to a Huffman-compressed ASCII, except it predates both of those by a hundred years.
Suppose you have binary data representing a signal that contains a valid Morse code message. It's an array of the numbers 1 and 0. Symbols, like letters and numbers, are made up of "dits" and "dahs", or short and long runs of 1's. The time between pulses (sequences of "0"s in our input) signifies different things depending on its length: a short pause separates dits and dahs, a slightly longer pause separates letters, and a longer pause separates words. Morse code is often represented in a sort of "intermediate" representation of dots and dashes, so "square" would read "••• --•- ••- •- •-• •".
Let's write a function that translates from the raw signal (1's and 0's) to the morse alphabet (e.g. "--•-"). In the interest of simplicity, let's use periods instead of bullet characters. For example:
signal = [1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1]
assert "... --.-" == parse_morse(signal)

Specifics
The input is a bounded array of a reasonable length of integers, consisting entirely of 1's and 0's
A "." is 1 or 2 time units long (1 or 1,1)
A "-" is 3, 4, or 5 time units long (1,1,1 or 1,1,1,1 or 1,1,1,1,1)
The space between dits (".") and dahs ("-") is 1 or 2 time units long (0 or 0,0)
The space between letters (represented as a " ") in your result is 3, 4, or 5 time units long

CoderPad Paste
# Input: array of a reasonable length of integers, consisting entirely of 1's and 0's
# Output: Arbitrary-length string made up of the characters ".", "-", and " "
# A "." is one or two 1's in a row (1 or 1,1)
# A "-" is three, four or five 1's in a row (1,1,1 or 1,1,1,1 or 1,1,1,1,1)
# A short pause (represented as the empty string "" in the output) is one or two 0's in a row (0 or 0,0)
# A long pause (represented as a " " in the output) is three, four, or five 0's in a row
# Goal: Transform raw signal (runs of 1's and 0's) into "intermediate" representation of morse code, e.g.
#
# signal = [1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1]
# assert "... --.-" == parse_morse(signal), "\"" + parse_morse(signal) + "\""


 */

class SolutionOne {
    public static String encode(int[] input) {
        StringBuilder sb = new StringBuilder();
        int f = 0;
        while (f < input.length) {
            int s = f++;
            while (f < input.length && input[f] == input[s]) {
                f++;
            }
            int diff = f - s;
            // assume 1 <= diff <= 5
            if (diff <= 2) {
                if (input[s] == 1) {
                    sb.append(".");
                }
                continue;
                // otherwise, short pause <=> append nothing
            }
            if (diff <= 5) {
                if (input[s] == 1) {
                    sb.append("-");
                    continue;
                }
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}

/*
Part 2
Now we want to parse actual strings (e.g. "square"). Given a text file of the Morse alphabet (http://g.wolak.net/morse2), parse it and use it to decode letters from the intermediate representation. We'll go ahead and add words, which are separated by 6 or 0's. For example:
signal = [1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1]
assert "sq rox" == decode_morse(signal)
g.wolak.net/morse2
Feel free to re-use as much or as little of your solution to Part 1. If you're using Java, C++, or some other language where reading files is a pain, feel free to just copy the contents of the file into a string and parse that.
Specifics
The input is a bounded array of a reasonable length of integers, consisting entirely of 1's and 0's
A "dit" is 1 or 2 time units long (1 or 1,1)
A "dah" is 3, 4, or 5 time units long (1,1,1 or 1,1,1,1 or 1,1,1,1,1)
The space between dits and dahs is 1 or 2 time units long (0 or 0,0)
The space between letters (represented as a " ") in your result is 3, 4, or 5 time units long
The space between words is 6 or more time units long
CoderPad Paste
# Input: array of integers, consisting entirely of 1's and 0's
# Output: Alphabetic string of letters and spaces
# A "." is one or two 1's in a row (1 or 1,1)
# A "-" is three, four, or five 1's in a row (1,1,1 or 1,1,1,1 or 1,1,1,1,1)
# A short pause that separates individual tokens is one or two 0's in a row (0 or 0,0)
# A medium pause that separates groups of tokens that correspond to letters is 3, 4, or 5 0's in a row
# A space between words (" " in the final output) is 6 or more 0's in a row
# Goal: Transform raw signal (runs of 1's and 0's) into text, e.g.
# Example:
#
# signal = [0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0]
# morse = ".--- --- .. -.[some representation of a long pause]... --.- ..- .- .-. ."
# assert "join square" == decode_morse(signal)
# Morse alphabet:
# a .-
# b -...
# c -.-.
# d -..
# e .
# f ..-.
# g --.
# h ....
# i ..
# j .---
# k -.-
# l .-..
# m --
# n -.
# o ---
# p .--.
# q --.-
# r .-.
# s ...
# t -
# u ..-
# v ...-
# w .--
# x -..-
# y -.--
# z --..

 */

class SolutionTwo {
    static final String CODE =
            "a .-,b -...,c -.-.,d -..,e .,f ..-.,g --.,h ....,i ..,j .---,k -.-,l .-..,m --,n -.,o ---,p .--.,q --.-,r .-.,s ...,t -,u ..-,v ...-,w .--,x -..-,y -.--,z --..";
    static final Map<String, String> DICT = new HashMap<>();

    static {
        String[] codes = CODE.split(",");
        for (String code : codes) {
            String[] c = code.split(" ");
            DICT.put(c[1], c[0]);
        }
    }

    public static String decode(int[] input) {
        StringBuilder sb = new StringBuilder();
        String codes = encodeHelper(input);
//        System.out.println(codes);
        String[] words = codes.split("#");
        for (String word : words) {
            String[] chars = word.split(" ");
            for (String c : chars) {
                sb.append(DICT.get(c));
            }
            sb.append(" ");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();

    }

    public static String encodeHelper(int[] input) {
        StringBuilder sb = new StringBuilder();
        int f = 0;
        while (f < input.length) {
            int s = f++;
            while (f < input.length && input[f] == input[s]) {
                f++;
            }
            int dif = f - s;
            if (dif <= 2) {
                if (input[s] == 1) {
                    sb.append(".");
                }
                continue;
                // otherwise, append nothing
            }
            if (dif <= 5) {
                if (input[s] == 1) {
                    sb.append("-");
                    continue;
                }
                sb.append(" ");
                continue;
            }
            // otherwise, 6 or more 0
            sb.append("#");
        }
        return sb.toString();
    }
}

/*
Part 3
In signal processing (and often data processing, too) we don't always have the entire input available at once. Re-using as much or as little of your solution to Part 3, write a streaming_decode_morse() that can be repeatedly called with new input and decodes as much as it can up to the end of the most recent input. Again, the input is an array of 1's and 0's, and the output is letters, but this time the input may be cut off anywhere: within a symbol, between letters, between words.
data_1 = [1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0]
data_2 = [1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0]
assert "s" == streaming_decode_morse(data_1)
assert "qu" == streaming_decode_morse(data_2)
CoderPad Paste
# Input: array of integers, consisting entirely of 1's and 0's, cut off at some arbitrary point
# Output: As much of the string as can be parsed so far, including possibly part of the previous buffer
# Goal: Transform raw signal (runs of 1's and 0's) into text, e.g.
#
# signal_1 = [1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0] # NOTE: this is cut off in between tokens within the "q"
# signal_2 = [1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0]
# signal_3 = [0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0]
# signal_4 = [0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1]
# signal_5 = [1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0]
# assert "s" == streaming_decode_morse(signal_1)
# assert "qu" == streaming_decode_morse(signal_2)
# assert "ar" == streaming_decode_morse(signal_3)
# assert "e r" == streaming_decode_morse(signal_4)
# assert "ox" == streaming_decode_morse(signal_5)

 */

class SolutionThree {
    static final String CODE =
            "a .-,b -...,c -.-.,d -..,e .,f ..-.,g --.,h ....,i ..,j .---,k -.-,l .-..,m --,n -.,o ---,p .--.,q --.-,r .-.,s ...,t -,u ..-,v ...-,w .--,x -..-,y -.--,z --..";
    static final Map<String, String> DICT = new HashMap<>();
    private final List<Integer> leftOver = new ArrayList<>();

    static {
        String[] codes = CODE.split(",");
        for (String code : codes) {
            String[] c = code.split(" ");
            DICT.put(c[1], c[0]);
        }
    }

    // todo:
}