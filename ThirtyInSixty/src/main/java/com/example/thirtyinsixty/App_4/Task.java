package com.example.thirtyinsixty.App_4;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Kayvon on 8/18/13.
 */

@ParseClassName("Task")
public class Task extends ParseObject {

    public Task() {}

    public boolean isCompleted() {
        return getBoolean("completed");
    }

    public void setCompleted(boolean completed) {
        put("completed", completed);
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }
}
