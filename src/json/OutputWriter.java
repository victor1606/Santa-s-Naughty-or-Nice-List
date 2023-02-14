package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import objects.Child;
import simulation.Output;
import simulation.RoundData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final  class OutputWriter {

    private OutputWriter() {
    }

    /**
     * This method writes all the data from the Output object to a JSON output file.
     *
     * @param children contains the final list
     * @param outFile contains the name of the output file
     * @param output contains the object used to properly format the JSON output
     */
    public static void writeOutput(final ArrayList<Child> children,
                                   final File outFile, final Output output) throws IOException {
        RoundData newRoundData = new RoundData();
        newRoundData.setChildren(new ArrayList<>(children));

        output.getAnnualChildren().add(newRoundData);

        ObjectMapper objectMapper =
                new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(outFile, output);
    }
}
