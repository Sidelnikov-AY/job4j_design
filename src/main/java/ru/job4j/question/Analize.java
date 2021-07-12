package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int count = 0;
        HashMap<Integer, String> prev = new HashMap<>();
        for (User user: previous) {
            prev.put(user.getId(), user.getName());
        }
        for (User user: current) {
            String temp = prev.get(user.getId());
            if(temp == null) {
                added++;
            }
            if((temp != null) && (!temp.equals(user.getName()))) {
                changed++;
            }
            if(prev.containsKey(user.getId())) {
                count++;
            }
        }
        int deleted = prev.size() - count;
        return new Info(added, changed, deleted);
    }

}