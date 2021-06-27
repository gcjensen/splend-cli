package uk.gcjensen.splend;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

@Command(
    name = "outgoing",
    description = "Commands for creating and listing outgoings."
)
public class Outgoing implements Runnable {
    @Override
    public void run() {}

    @Command(
        name = "list",
        description = "List outgoings."
    )
    public void list(
        @Option(names = { "-d", "--description" }, description = "outgoing description to filter on") String description,
        @Option(names = { "-m", "--months" }, description = "the number of months of outgoings to return") Integer months
    ) {
        Map<String, Object> params = new HashMap<>();
        params.computeIfAbsent("description", val -> description);
        params.computeIfAbsent("months", val -> months);

        try {
            SplendAPI api = new SplendAPI(new Config());
            System.out.println(api.getOutgoings(params));
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find ~/.splend/config.yaml");
        }
    }
}
