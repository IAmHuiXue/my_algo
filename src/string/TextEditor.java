package string;

/*
A text editor is a type of computer program that edits plain text. It consists of a string with a cursor. In the initial state the string is empty with the cursor at the beginning of the string.

Your task is to simulate the working process of a text editor which can handle four types of operations:

TYPE <text> - insert <text> after the current position of the cursor, where <text> consists of at most 20 characters. The cursor moves to the end of the inserted text.

MOVE_CURSOR <offset> - change the current cursor's position. The offset specifies the direction and the value change - if it's negative the cursor moves to the left, and if it's positive the cursor moves to the right. At all times, the cursor is either located at the beginning of the string (before the first character), between two characters, or at the end of the string (after the last character) - the cursor should always be within the text bounds. If the offset is larger than cursor can move, the cursor will move towards the direction as much, as it can. If the cursor does not change its position after the operation, the operation is considered unsuccessful.

SELECT <start_index> <end_index> - select the substring of the current text [text[start_index] ... text[end_index]] (inclusive indices, 0-based) of length end_index - start_index + 1. It is guaranteed that the indices are valid. The cursor changes its position to the end of the selected area. If the next immediately subsequent operation is not TYPE then the selected area is cleared.

If the next immediately subsequent operation is TYPE then the following updating process is expected during the TYPE operation:

Delete the selected text.
Insert the new text in the place of the deleted text.
The cursor position should move to the end of the typed text.
If this operation is anything other than TYPE, the selected area is cleared (See the examples for details).

UNDO - undo the last successful TYPE or MOVE_CURSOR operation (if there is nothing to undo, this operation does nothing). You cannot undo a SELECT operation. UNDO operation returns to the text and cursor position to the state, they were before the operations is undone. Note that it's not possible to undo the same operation twice: if the operation has been undone, it's undone forever. However, it is possible to undo several operations one by one.

You are given operations, an array of strings where each is an operation of one of the four types above. Your task is to find the resulting text after performing the given operations.

NOTE: An operation is considered successful if the text or the cursor position is changed. For example, moving the cursor to the left when it stands before all characters is not considered a successful operation.

Example

For operations = ["TYPE Code", "TYPE Signal", "MOVE_CURSOR -3", "SELECT 5 8", "TYPE ou", "UNDO", "TYPE nio"], the output should be implementTextEditor(operations) = "CodeSignaniol".

Initially the text is empty,
After the "TYPE Code" operation, the text is "Code|" (where the | symbol represents the cursor position),
After the "TYPE Signal" operation, the text is "CodeSignal|",
After the "MOVE_CURSOR -3", the cursor moves three symbols back, so the text is "CodeSig|nal",
After the "SELECT 5 8" operation, the selected text is "igna", the cursor is moved to the end of selected area "CodeSigna|l",
After the "TYPE ou" operation, since the previous operation was "SELECT", the selected text is deleted and replaced with the text "ou", the cursor stays after the typed text, so the text is "CodeSou|l",
After the "UNDO" operation, the last operation "TYPE" is undone and text and cursor is back as it was before the "TYPE" operation, so the text is "CodeSigna|l",
After the "TYPE nio" operation, the text is "CodeSignanio|l",
So the final string is "CodeSignaniol".
For operations = ["TYPE MyCat", "SELECT 2 3", "MOVE_CURSOR -1", "TYPE he", "SELECT 0 1", "TYPE His"], the output should be implementTextEditor(operations) = "HisCheat".

Initially the text is empty,
After the "TYPE MyCat" operation, the text is "MyCat|",
After the "SELECT 2 3" operation, the selected text is "Ca", the cursor is moved after the selected area "MyCa|t",
After the "MOVE_CURSOR -1", the cursor moves one symbol back, so the text is "MyC|at". Also, the selected area is cleared, as this operation is not "TYPE". No area is selected,
After the "TYPE he" operation, the text is "MyChe|at", the typed text is inserted where the cursor stands.
After the "SELECT 0 1" operation, the selected text is "My",
After the "TYPE His" operation, since the previous operation was "SELECT", the selected text is deleted and replaced with the text "His", the cursor moves to the end of the typed area, so the text is "His|Cheat",
So the final string is "HisCheat".
For operations = ["TYPE Nothing", "TYPE Is", "TYPE Permanent", "UNDO", "UNDO", "UNDO", "UNDO"], the output should be implementTextEditor(operations) = "".

Initially the text is empty,
After the "TYPE Nothing", "TYPE is", and "TYPE Permanent" operations the text is "NothingIsPermanent",
Then, after three consequent "UNDO" operations, the text becomes "", the last "UNDO" operation is ignored since there are no more operations to undo.
Input/Output

[execution time limit] 3 seconds (java)

[input] array.string operations

A sequence of operations. It's guaranteed that all the operations satisfy the format described above.

Guaranteed constraints:
1 ≤ operations.length ≤ 103.

[output] string

The resulting text after performing the given sequence of operations.
 */

import java.util.*;

public class TextEditor {
    public static void main(String[] args) {
        TextEditor t = new TextEditor();
        String[] ops = {"TYPE Code", "TYPE Signal", "COPY", "SELECT 5 8", "COPY", "SELECT 1 2", "PASTE", "TYPE ou", "UNDO"};
        t.solution(ops);
    }
    String solution(String[] operations) {
        int[] cursor = new int[]{0}; // the cursor is on the left side of cur[0] -> "ab|c" -> cursor[0] = 2; "|" -> cursor[0] = 0;
        List<String> clipboard = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int[] selected = new int[]{-1, -1};
        Deque<TextEditor.State> buffer = new ArrayDeque<>();
        for (String op : operations) {
            edit(cursor, clipboard, sb, op, selected, buffer);
            System.out.println(sb);
            System.out.println(clipboard);
        }
        return sb.toString();
    }

    static class State {
        String curText;
        int cursorPo;

        public State(String curText, int cursorPo) {
            this.curText = curText;
            this.cursorPo = cursorPo;
        }
    }

    void edit(int[] cursor, List<String> clipboard, StringBuilder sb, String op, int[] selected, Deque<TextEditor.State> buffer) {
        String[] chunks = op.split(" ", 2);
        if (chunks[0].equals("TYPE")) {
            buffer.offerFirst(new TextEditor.State(sb.toString(), cursor[0]));
            if (selected[0] != -1) {
                sb.replace(selected[0], selected[1] + 1, chunks[1]);
                cursor[0] -= selected[1] - selected[0] + 1;
                selected[0] = -1;
                selected[1] = -1;
            } else {
                sb.insert(cursor[0], chunks[1]);
            }
            int len = chunks[1].length();
            cursor[0] += len;
            return;
        }
        if (chunks[0].equals("SELECT")) {
            String[] segments = chunks[1].split(" ");
            int start = Integer.parseInt(segments[0]);
            int end = Integer.parseInt(segments[1]);
            selected[0] = start;
            selected[1] = end;
            cursor[0] = end + 1;
            return;
        }
        if (chunks[0].equals("MOVE_CURSOR")) {
            selected[0] = -1;
            selected[1] = -1;
            int offset = Integer.parseInt(chunks[1]);
            if (offset < 0) {
                cursor[0] = Math.max(cursor[0] + offset, 0);
            } else {
                cursor[0] = Math.min(cursor[0] + offset, sb.length());
            }
            return;
        }
        if (chunks[0].equals("COPY")) {
            if (selected[0] != -1) {
                clipboard.add(sb.substring(selected[0], selected[1] + 1));
            }
            return;
        }
        if (chunks[0].equals("PASTE")) {
            int num = chunks.length != 1 ? Integer.parseInt(chunks[1]) : 1;
            if (clipboard.size() < num) {
                return;
            }
            buffer.offerFirst(new TextEditor.State(sb.toString(), cursor[0]));
            String toPaste = clipboard.get(clipboard.size() - num);
            if (selected[0] != -1) {
                sb.replace(selected[0], selected[1] + 1, toPaste);
                cursor[0] -= selected[1] - selected[0] + 1;
                selected[0] = -1;
                selected[1] = -1;
            } else {
                sb.insert(cursor[0], toPaste);
            }
            int len = toPaste.length();
            cursor[0] += len;
            buffer.offerFirst(new TextEditor.State(sb.toString(), cursor[0]));
        }
        if (chunks[0].equals("UNDO")) {
            if (buffer.isEmpty()) {
                return;
            }
            TextEditor.State cur = buffer.pollFirst();
            sb.replace(0, sb.length(), cur.curText);
            cursor[0] = cur.cursorPo;
        }
    }
}

