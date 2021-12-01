package utilities;

import org.bson.types.ObjectId;

public class Utils {

    public ObjectId getId(String id) {

        if (id == null || id.isBlank()) {
            return new ObjectId();
        } else {
            return new ObjectId(id);
        }
    }
}