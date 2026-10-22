package deliveryApp.user;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserManager {
    private final Map<String, User> users = new LinkedHashMap<>();

    public User loginOrRegister(String name, String address) {
        String key = name.toLowerCase();
        User existing = users.get(key);
        if (existing != null) {
            existing.setUserAddress(address);
            return existing;
        }
        User user = new User(name, address);
        users.put(key, user);
        return user;
    }
}