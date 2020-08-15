import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String res;
        List<Task> data = new ArrayList<>();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        while (true) {
            try {
                res = sc.nextLine();
                if (res.equals("bye")) {
                    break;
                } else if (res.equals("list")) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < data.size(); i++) {
                        System.out.printf("     %d.%s\n", i + 1, data.get(i).toString());
                    }
                    System.out.println("    ____________________________________________________________");
                } else if (res.startsWith("done")) {
                    if (res.length() <= 5) {
                        // Exception: eg. done
                        throw new DukeException("     ☹ OOPS!!! The description of done is incomplete.");
                    }
                    int n = Integer.parseInt(res.substring(5)) - 1;
                    if (n > data.size() || n <= 0) {
                        //Exception: eg. done 999
                        throw new DukeException("     ☹ OOPS!!! The index is out of bounds.");
                    }
                    data.get(n).done();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Nice! I've marked this task as done: ");
                    System.out.printf("     [%s] %s\n", data.get(n).getStatusIcon(), data.get(n).getDescription());
                    System.out.println("    ____________________________________________________________");
                } else if (res.startsWith("todo")) {
                    if (res.length() <= 5) {
                        // Exception: eg. todo
                        throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Todo t = new Todo(res.substring(5));
                    data.add(t);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task: ");
                    System.out.printf("       %s\n", t.toString());
                    System.out.printf("     Now you have %d tasks in the list.\n", data.size());
                    System.out.println("    ____________________________________________________________");
                } else if (res.startsWith("deadline")) {
                    if (res.length() <= 9) {
                        // Exception: eg. deadline
                        throw new DukeException("     ☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] ss = res.split("/");
                    if (ss.length != 2) {
                        // Exception: eg. deadline do homework
                        throw new DukeException("     ☹ OOPS!!! A deadline should contain a description and a time, separated by /.");
                    }
                    if (!ss[1].startsWith("by ")) {
                        // Exception: eg. deadline do homework /Mon
                        throw new DukeException("     ☹ OOPS!!! Please enter the time following the format: by XXX");
                    }
                    Deadline t = new Deadline(ss[0].substring(9), ss[1].substring(3));
                    data.add(t);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task: ");
                    System.out.printf("       %s\n", t.toString());
                    System.out.printf("     Now you have %d tasks in the list.\n", data.size());
                    System.out.println("    ____________________________________________________________");
                } else if (res.startsWith("event")) {
                    if (res.length() <= 9) {
                        // Exception: eg. event
                        throw new DukeException("     ☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] ss = res.split("/");
                    if (ss.length != 2) {
                        // Exception: eg. event meeting
                        throw new DukeException("     ☹ OOPS!!! An event should contain a description and a time, separated by /.");
                    }
                    if (!ss[1].startsWith("at ")) {
                        // Exception: eg. event meeting /Mon
                        throw new DukeException("     ☹ OOPS!!! Please enter the time following the format: at XXX");
                    }
                    Event t = new Event(ss[0].substring(6), ss[1].substring(3));
                    data.add(t);
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task: ");
                    System.out.printf("       %s\n", t.toString());
                    System.out.printf("     Now you have %d tasks in the list.\n", data.size());
                    System.out.println("    ____________________________________________________________");
                } else {
                    // Exception: eg. ???
                    throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (NumberFormatException e) {
                // Exception: eg. done some words
                // Exception: eg. delete some words
                System.out.println("    ____________________________________________________________");
                System.out.println("     ☹ OOPS!!! Please enter a number");
                System.out.println("    ____________________________________________________________");
            } catch (DukeException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println(e.toString().substring(14));
                System.out.println("    ____________________________________________________________");
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

}
