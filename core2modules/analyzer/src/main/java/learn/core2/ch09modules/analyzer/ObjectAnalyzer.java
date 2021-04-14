package learn.core2.ch09modules.analyzer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ObjectAnalyzer {
    /**
     * Keeps track of objects that were already visited in order to avoid infinite cycles of references.
     */
    private final ArrayList<Object> visited = new ArrayList<>();

    /**
     * Converts an object to a string representation that lists all fields.
     *
     * @param obj an object
     * @return a string with the object's class name and all field names and values
     */
    public String analyze(Object obj) {
        if (obj == null) return "null";
        if (visited.contains(obj)) return "...";    // avoiding infinite recursion
        visited.add(obj);
        Class<?> cl = obj.getClass();
        if (cl == String.class) return (String) obj;
        StringBuilder r = new StringBuilder();
        if (cl.isArray()) {
            r.append(cl.getComponentType());
            r.append("[]{");
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) {
                    r.append(",");
                }
                Object val = Array.get(obj, i);
                if (cl.getComponentType().isPrimitive()) {
                    r.append(val);
                } else {
                    r.append(analyze(val));
                }
            }
            r.append("}");
            return r.toString();
        }

        r.append(cl.getName());
        // inspect the fields of this class and all superclasses
        try {
            do {
                r.append("[");
                Field[] fields = cl.getDeclaredFields();
                AccessibleObject.setAccessible(fields, true);

                for (Field f : fields) {
                    if (!Modifier.isStatic(f.getModifiers())) {
                        if (r.charAt(r.length() - 1) != '[') {
                            r.append(",");
                        }
                        r.append(f.getName());
                        r.append("=");
                        Object val = f.get(obj);
                        if (f.getType().isPrimitive()) {
                            r.append(val);
                        } else {
                            r.append(analyze(val));
                        }
                    }
                }
                r.append("]");
                cl = cl.getSuperclass();
            } while (cl != Object.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return r.toString();
    }
}
