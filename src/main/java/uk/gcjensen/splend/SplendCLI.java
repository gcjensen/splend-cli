package uk.gcjensen.splend;

import java.io.FileNotFoundException;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * SplendCLI Main class.
 */
@Command(
    name = "splend",
    mixinStandardHelpOptions = true,
    version = "splend 0.0.1",
    description = "Interact with splend from the command line.",
    showEndOfOptionsDelimiterInUsageHelp = false,
    subcommands = { Outgoing.class }
)
public class SplendCLI implements Callable<Integer> {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new SplendCLI()).execute(args);
        System.exit(exitCode);
    }

    @Command(
        name = "summary",
        description = "Retrieve your Splend summary.",
        mixinStandardHelpOptions = true
    )
    public void summary() {
        try {
            SplendAPI api = new SplendAPI(new Config());
            System.out.println(api.getSummary());
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find ~/.splend/config.yaml");
        }
    }

    @Override
    public Integer call() {
        return null;
    }
}
