package JavaRush.MiniProjects.LogParser.query;

import java.util.Set;

public interface QLQuery {
    Set<Object> execute(String query);
}