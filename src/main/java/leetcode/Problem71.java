package leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class Problem71 {

    private static final Logger log = LoggerFactory.getLogger(Problem71.class);

    public String simplifyPath(String path) {
        String[] directories = path.split("/");
        Stack<String> directoriesStack = new Stack<>();
        for (String dir : directories) {
            if (dir.equals(".") || dir.startsWith("/") || dir.isEmpty())
                continue;
            if (dir.equals("..")) {
                if (!directoriesStack.isEmpty())
                    directoriesStack.pop();
            } else {
                directoriesStack.push(dir);
            }
        }
        if (directoriesStack.isEmpty())
            return "/";
        return directoriesStack
                .stream()
                .collect(StringBuilder::new, (a, b) -> a.append("/" + b), (a, b) -> a.append(b))
                //.reverse()
                .toString();
    }

    public static void main(String[] args) {
        //String path = "/a/./b/../../c/";
        String path = "/a//b////c/d//././/..";
        System.out.println(new Problem71().simplifyPath(path));
        //test();
    }
}
